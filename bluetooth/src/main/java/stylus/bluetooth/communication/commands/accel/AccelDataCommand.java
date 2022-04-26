package stylus.bluetooth.communication.commands.accel;

import lombok.Getter;
import stylus.bluetooth.communication.commands.DataCommand;

import java.time.Instant;

@Getter
public class AccelDataCommand extends DataCommand {
    private final int ax;
    private final int ay;
    private final int az;


    protected AccelDataCommand(Instant receptionTime, int ax, int ay, int az) {
        super(receptionTime);
        this.ax = ax;
        this.ay = ay;
        this.az = az;
    }
}
