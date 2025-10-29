package seedu.address.logic.parser;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.HelpCommand;

/**
 * Parses input arguments and creates a new HelpCommand object.
 * Allows extra arguments but logs info of the example command.
 */
public class HelpCommandParser implements Parser<HelpCommand> {
    private static final Logger logger = LogsCenter.getLogger(HelpCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     */
    public HelpCommand parse(String args) {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            // No arguments provided, return standard HelpCommand
            return new HelpCommand();
        } else {
            // Arguments provided, return HelpCommand with different constructor
            logger.info(HelpCommand.MESSAGE_USAGE);
            return new HelpCommand(trimmedArgs);
        }
    }
}
