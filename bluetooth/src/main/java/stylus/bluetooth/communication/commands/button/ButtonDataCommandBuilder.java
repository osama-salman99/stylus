package stylus.bluetooth.communication.commands.button;

import java.time.Instant;

interface ReceptionTimeStep {
    ButtonNumberStep setReceptionTime(Instant receptionTime);
}

interface ButtonNumberStep {
    ButtonStateStep setButtonNumber(int buttonNumber);

}

interface ButtonStateStep {
    BuildStep setPressed(boolean pressed);
}

interface BuildStep {
    ButtonDataCommand build();
}

public class ButtonDataCommandBuilder implements ReceptionTimeStep, ButtonNumberStep, ButtonStateStep, BuildStep {
    private Instant receptionTime;
    private int buttonNumber;
    private boolean pressed;

    @Override
    public ButtonNumberStep setReceptionTime(Instant receptionTime) {
        this.receptionTime = receptionTime;
        return this;
    }

    @Override
    public ButtonStateStep setButtonNumber(int buttonNumber) {
        this.buttonNumber = buttonNumber;
        return this;
    }

    @Override
    public BuildStep setPressed(boolean pressed) {
        this.pressed = pressed;
        return this;
    }

    @Override
    public ButtonDataCommand build() {
        return new ButtonDataCommand(receptionTime, buttonNumber, pressed);
    }
}
