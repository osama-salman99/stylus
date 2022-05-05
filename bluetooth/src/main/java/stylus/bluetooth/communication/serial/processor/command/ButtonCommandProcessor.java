package stylus.bluetooth.communication.serial.processor.command;

import stylus.bluetooth.communication.commands.DataCommand;
import stylus.bluetooth.communication.commands.button.ButtonDataCommandBuilder;
import stylus.bluetooth.exceptions.LineToCommandProcessingException;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ButtonCommandProcessor implements CommandProcessor {
    private static final Pattern pattern = Pattern.compile("^button:\\s(\\d+)\\s(true|false)$");

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
        return new ButtonDataCommandBuilder()
                .setReceptionTime(Instant.now())
                .setButtonNumber(Integer.parseInt(matcher.group(1)))
                .setPressed(Boolean.parseBoolean(matcher.group(2)))
                .build();
    }
}
