package stylus.bluetooth.communication.receiver;

import stylus.bluetooth.communication.commands.DataCommand;

public interface DataCommandReceiver {
    void onCommandReceived(DataCommand dataCommand);
}
