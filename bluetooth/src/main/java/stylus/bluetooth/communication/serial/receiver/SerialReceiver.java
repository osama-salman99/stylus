package stylus.bluetooth.communication.serial.receiver;

import com.fazecast.jSerialComm.SerialPort;
import lombok.Builder;
import stylus.bluetooth.communication.serial.processor.DataLineProcessor;
import stylus.bluetooth.exceptions.PortNotOpenedException;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Builder
public class SerialReceiver {
    private final String PORT_DESCRIPTOR;
    private final int BAUD_RATE;
    private final int SAFETY_SLEEP_TIME; // Normally 1000
    private final DataLineProcessor dataLineProcessor;
    private ExecutorService executorService;
    private SerialPort port;

    public OpenSerialReceiver open() {
        port = SerialPort.getCommPort(PORT_DESCRIPTOR);
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        port.setComPortParameters(BAUD_RATE, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        port.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        if (!port.openPort(SAFETY_SLEEP_TIME)) {
            throw new PortNotOpenedException();
        }
        return new OpenSerialReceiver();
    }

    public class OpenSerialReceiver {

        public void startAsyncReception() {
            if (!(Objects.isNull(executorService) | executorService.isTerminated() | executorService.isShutdown())) {
                return;
            }
            executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                ExecutorService processingExecutors = Executors.newFixedThreadPool(6);
                Scanner scanner = new Scanner(port.getInputStream());
                while (port.isOpen()) {
                    while (scanner.hasNextLine()) {
                        processingExecutors.submit(() -> dataLineProcessor.processLine(scanner.nextLine()));
                    }
                }
            });
        }
    }
}
