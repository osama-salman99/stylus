package stylus.bluetooth.communication.serial.processor;

import lombok.RequiredArgsConstructor;
import stylus.bluetooth.communication.receiver.DataCommandReceiver;
import stylus.bluetooth.communication.serial.processor.command.CommandProcessor;
import stylus.bluetooth.exceptions.UnknownDataCommandException;

import java.util.List;

@RequiredArgsConstructor
public class LineToCommandProcessor implements DataLineProcessor {
    private final DataCommandReceiver commandReceiver;
    private final List<CommandProcessor> commandProcessors;


    @Override
    public void processLine(String dataLine) {
        commandReceiver.onCommandReceived(commandProcessors.stream().filter(commandProcessor -> commandProcessor.isMatching(dataLine)).findFirst().orElseThrow(() -> new UnknownDataCommandException(String.format("The data line '%s' is of unknown/invalid format", dataLine))).process(dataLine));
    }
}
