package stylus.bluetooth.configurations;

import lombok.Builder;

@Builder
public class Configurations {
    private final String PORT_DESCRIPTOR;
    private final int BAUD_RATE;
}
