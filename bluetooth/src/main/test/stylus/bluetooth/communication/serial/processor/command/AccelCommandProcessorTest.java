package stylus.bluetooth.communication.serial.processor.command;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import stylus.bluetooth.communication.commands.DataCommand;
import stylus.bluetooth.communication.commands.accel.AccelDataCommand;
import stylus.bluetooth.communication.commands.accel.AccelDataCommandBuilder;
import stylus.bluetooth.exceptions.LineToCommandProcessingException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AccelCommandProcessorTest {
    private static Stream<Arguments> invalidLines() {
        return Stream.of(Arguments.of(""), Arguments.of("asdjfsk", "accel:"), Arguments.of("accel: 1 2 "), Arguments.of("accel: 1a 2 3"));
    }

    public static Stream<Arguments> validLines() {
        return Stream.of(
                Arguments.of("accel: 1 2 3", new AccelDataCommandBuilder().setAccelData(1, 2, 3).build()),
                Arguments.of("accel: 12 22 32", new AccelDataCommandBuilder().setAccelData(12, 22, 32).build()),
                Arguments.of("accel: 0 0 0", new AccelDataCommandBuilder().setAccelData(0, 0, 0).build()),
                Arguments.of("accel: -1 2 3", new AccelDataCommandBuilder().setAccelData(-1, 2, 3).build())
        );
    }

    @ParameterizedTest
    @MethodSource("invalidLines")
    public void givenInvalidLineWhenIsMatchingThenReturnFalse(String value) {
        AccelCommandProcessor processor = new AccelCommandProcessor();
        assertFalse(processor.isMatching(value));
    }

    @ParameterizedTest
    @MethodSource("validLines")
    public void givenValidLineWhenIsMatchingThenReturnTrue(String value) {
        AccelCommandProcessor processor = new AccelCommandProcessor();
        assertTrue(processor.isMatching(value));
    }

    @ParameterizedTest
    @MethodSource("invalidLines")
    public void givenInvalidLineWhenProcessThenThrow(String value) {
        AccelCommandProcessor processor = new AccelCommandProcessor();
        assertThrows(LineToCommandProcessingException.class, () -> processor.process(value));
    }

    @ParameterizedTest
    @MethodSource("validLines")
    public void givenInvalidLineWhenProcessThenThrow(String value, AccelDataCommand dataCommand) {
        AccelCommandProcessor processor = new AccelCommandProcessor();
        assertSame(dataCommand, processor.process(value));
    }

    private void assertSame(AccelDataCommand expected, DataCommand actual) {
        assertInstanceOf(AccelDataCommand.class, actual);
        AccelDataCommand casted = (AccelDataCommand) actual;
        assertEquals(expected.getAx(), casted.getAx());
        assertEquals(expected.getAy(), casted.getAy());
        assertEquals(expected.getAz(), casted.getAz());
    }
}