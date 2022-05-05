package stylus.bluetooth.communication.serial.processor;

import org.junit.jupiter.api.Test;
import stylus.bluetooth.exceptions.UnknownDataCommandException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LineToCommandProcessorTest {
    @Test
    public void givenInvalidDataLineWhenProcessThenThrowException() {
        LineToCommandProcessor lineToCommandProcessor = new LineToCommandProcessor(dataCommand -> {
        }, List.of());
        assertThrows(UnknownDataCommandException.class, () -> lineToCommandProcessor.processLine("hello"));
    }
}