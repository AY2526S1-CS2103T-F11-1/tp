package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteUpcomingAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteUpcomingAppointmentCommandParser object
 */
public class DeleteUpcomingAppointmentCommandParser implements Parser<DeleteUpcomingAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteUpcomingAppointmentCommandParser
     * and returns a DeleteUpcomingAppointmentCommandParser object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteUpcomingAppointmentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteUpcomingAppointmentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteUpcomingAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }
}
