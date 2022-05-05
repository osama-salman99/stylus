package stylus.bluetooth.communication.serial.processor.command;

import stylus.bluetooth.communication.commands.DataCommand;

public interface CommandProcessor {
    boolean isMatching(String dataLine);

    DataCommand process(String dataLine);
}
