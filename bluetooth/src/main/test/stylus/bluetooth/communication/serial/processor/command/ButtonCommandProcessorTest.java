package stylus.bluetooth.communication.serial.processor.command;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import stylus.bluetooth.communication.commands.DataCommand;
import stylus.bluetooth.communication.commands.button.ButtonDataCommand;
import stylus.bluetooth.communication.commands.button.ButtonDataCommandBuilder;
import stylus.bluetooth.exceptions.LineToCommandProcessingException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ButtonCommandProcessorTest {
    private static Stream<Arguments> invalidLines() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("asdjfsk", "button:"),
                Arguments.of("button: 1 tru "),
                Arguments.of("button: false")
        );
    }

    public static Stream<Arguments> validLines() {
        return Stream.of(
                Arguments.of("button: 1 true", new ButtonDataCommandBuilder().setButtonNumber(1).setPressed(true).build()),
                Arguments.of("button: 2 false", new ButtonDataCommandBuilder().setButtonNumber(2).setPressed(false).build()),
                Arguments.of("button: 0 true", new ButtonDataCommandBuilder().setButtonNumber(0).setPressed(true).build()),
                Arguments.of("button: 154 false", new ButtonDataCommandBuilder().setButtonNumber(154).setPressed(false).build())
        );
    }

    @ParameterizedTest
    @MethodSource("invalidLines")
    public void givenInvalidLineWhenIsMatchingThenReturnFalse(String value) {
        ButtonCommandProcessor processor = new ButtonCommandProcessor();
        assertFalse(processor.isMatching(value));
    }

    @ParameterizedTest
    @MethodSource("validLines")
    public void givenValidLineWhenIsMatchingThenReturnTrue(String value) {
        ButtonCommandProcessor processor = new ButtonCommandProcessor();
        assertTrue(processor.isMatching(value));
    }

    @ParameterizedTest
    @MethodSource("invalidLines")
    public void givenInvalidLineWhenProcessThenThrow(String value) {
        ButtonCommandProcessor processor = new ButtonCommandProcessor();
        assertThrows(LineToCommandProcessingException.class, () -> processor.process(value));
    }

    @ParameterizedTest
    @MethodSource("validLines")
    public void givenInvalidLineWhenProcessThenThrow(String value, ButtonDataCommand expected) {
        ButtonCommandProcessor processor = new ButtonCommandProcessor();
        assertSame(expected, processor.process(value));
    }

    private void assertSame(ButtonDataCommand expected, DataCommand actual) {
        assertInstanceOf(ButtonDataCommand.class, actual);
        ButtonDataCommand casted = (ButtonDataCommand) actual;
        assertEquals(expected.getButtonNumber(), casted.getButtonNumber());
        assertEquals(expected.isPressed(), casted.isPressed());
    }
}