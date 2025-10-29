package seedu.address.logic.parser;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ListCommand;

/**
 * Parses input arguments and creates a new ListCommand object.
 * Allows extra arguments but generates a warning.
 */
public class ListCommandParser implements Parser<ListCommand> {
    private static final Logger logger = LogsCenter.getLogger(ListCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     */
    public ListCommand parse(String args) {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            // No arguments provided, return standard ListCommand
            return new ListCommand();
        } else {
            // Arguments provided, return ListCommand with different constructor
            logger.info(ListCommand.MESSAGE_USAGE);
            return new ListCommand(trimmedArgs);
        }
    }
}
