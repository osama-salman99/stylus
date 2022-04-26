package stylus.bluetooth.communication.receiver;

import stylus.bluetooth.communication.commands.DataCommand;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueDataCommandReceiver implements DataCommandReceiver {
    private final Queue<DataCommand> commandsQueue;

    public QueueDataCommandReceiver() {
        this.commandsQueue = new ConcurrentLinkedQueue<>();
    }


    public DataCommand getNextCommand() {
        return commandsQueue.remove();
    }

    public boolean hasNextCommand() {
        return !commandsQueue.isEmpty();
    }

    @Override
    public void onCommandReceived(DataCommand dataCommand) {
        commandsQueue.add(dataCommand);
    }
}
