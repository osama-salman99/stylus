package stylus.bluetooth.communication.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public abstract class DataCommand {
    private final Instant receptionTime;
}
