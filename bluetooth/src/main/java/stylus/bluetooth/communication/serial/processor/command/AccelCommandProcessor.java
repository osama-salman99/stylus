package stylus.bluetooth.communication.serial.processor.command;

import stylus.bluetooth.communication.commands.DataCommand;
import stylus.bluetooth.communication.commands.accel.AccelDataCommandBuilder;
import stylus.bluetooth.exceptions.LineToCommandProcessingException;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccelCommandProcessor implements CommandProcessor {
    private static final Pattern pattern = Pattern.compile("^accel:\\s(-?\\d+)\\s(-?\\d+)\\s(-?\\d+)$");

    @Override
    public boolean isMatching(String dataLine) {
        return pattern.matcher(dataLine).matches();
    }

    @Override
    public DataCommand process(String dataLine) {
        Matcher matcher = pattern.matcher(dataLine);
        if (!matcher.matches()) {
            throw new LineToCommandProcessingException();
        }
        return new AccelDataCommandBuilder()
                .setReceptionTime(Instant.now())
                .setAccelData(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)))
                .build();
    }
}
