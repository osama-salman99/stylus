package stylus.bluetooth.communication.commands.accel;

import java.time.Instant;

public class AccelDataCommandBuilder {
    private Instant receptionTime;
    private int ax;
    private int ay;
    private int az;

    public AccelDataCommandBuilder setAccelData(int ax, int ay, int az) {
        this.ax = ax;
        this.ay = ay;
        this.az = az;
        return this;
    }

    public AccelDataCommandBuilder setReceptionTime(Instant receptionTime) {
        this.receptionTime = receptionTime;
        return this;
    }

    public AccelDataCommand build() {
        return new AccelDataCommand(receptionTime, ax, ay, az);
    }
}