package stylus.bluetooth.communication.commands.button;

import java.time.Instant;

public class ButtonDataCommandBuilder {
    private Instant receptionTime;
    private int buttonNumber;
    private boolean pressed;

    public ButtonDataCommandBuilder setReceptionTime(Instant receptionTime) {
        this.receptionTime = receptionTime;
        return this;
    }

    public ButtonDataCommandBuilder setButtonNumber(int buttonNumber) {
        this.buttonNumber = buttonNumber;
        return this;
    }

    public ButtonDataCommandBuilder setPressed(boolean pressed) {
        this.pressed = pressed;
        return this;
    }

    public ButtonDataCommand build() {
        return new ButtonDataCommand(receptionTime, buttonNumber, pressed);
    }
}
