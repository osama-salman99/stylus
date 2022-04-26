package stylus.bluetooth.communication.commands.button;

import lombok.Getter;
import stylus.bluetooth.communication.commands.DataCommand;

import java.time.Instant;

@Getter
public class ButtonDataCommand extends DataCommand {
    private final int buttonNumber;
    private final boolean pressed;

    protected ButtonDataCommand(Instant receptionTime, int buttonNumber, boolean pressed) {
        super(receptionTime);
        this.buttonNumber = buttonNumber;
        this.pressed = pressed;
    }
}
