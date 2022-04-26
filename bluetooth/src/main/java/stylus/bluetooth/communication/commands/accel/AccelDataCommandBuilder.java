package stylus.bluetooth.communication.commands.accel;

import java.time.Instant;

interface ReceptionTimeStep {
    AccelDataStep setReceptionTime(Instant receptionTime);
}

interface AccelDataStep {
    BuildStep setAccelData(int ax, int ay, int az);

}

interface BuildStep {
    AccelDataCommand build();
}

public class AccelDataCommandBuilder implements ReceptionTimeStep, AccelDataStep, BuildStep {
    private Instant receptionTime;
    private int ax;
    private int ay;
    private int az;


    @Override
    public AccelDataStep setReceptionTime(Instant receptionTime) {
        this.receptionTime = receptionTime;
        return this;
    }

    @Override
    public BuildStep setAccelData(int ax, int ay, int az) {
        this.ax = ax;
        this.ay = ay;
        this.az = az;
        return this;
    }

    @Override
    public AccelDataCommand build() {
        return new AccelDataCommand(receptionTime, ax, ay, az);
    }
}