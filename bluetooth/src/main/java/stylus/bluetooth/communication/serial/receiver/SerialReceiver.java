package stylus.bluetooth.communication.serial.receiver;

import com.fazecast.jSerialComm.SerialPort;
import lombok.AllArgsConstructor;
import stylus.bluetooth.communication.serial.processor.DataLineProcessor;

import java.util.Scanner;

@AllArgsConstructor
public class SerialReceiver {
    private final String PORT_DESCRIPTOR;
    private final int BAUD_RATE;
    private final int SAFETY_SLEEP_TIME; // Normally 1000
    private final DataLineProcessor dataLineProcessor;
    private SerialPort port;

    public void open() {
        port = SerialPort.getCommPort(PORT_DESCRIPTOR);
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        port.setComPortParameters(BAUD_RATE, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        port.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        if (!port.openPort(SAFETY_SLEEP_TIME)) {
            throw new RuntimeException("Could not open port");  // TODO
        }
        startAsyncReception();
    }

    private void startAsyncReception() {
        // TODO: Make async
        Scanner scanner = new Scanner(port.getInputStream());
        while (port.isOpen()) {
            while (scanner.hasNextLine()) {
                dataLineProcessor.processLine(scanner.nextLine());
            }
        }
    }
}
