package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentNotes;
import seedu.address.model.appointment.AppointmentTime;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_TIME, PREFIX_APPOINTMENT_NOTE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            if (argMultimap.getValue(PREFIX_APPOINTMENT_TIME).isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddAppointmentCommand.MESSAGE_USAGE));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE),
                    pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPOINTMENT_TIME, PREFIX_APPOINTMENT_NOTE);
        AppointmentTime appointmentTime = ParserUtil.parseAppointmentTime(argMultimap.getValue(PREFIX_APPOINTMENT_TIME)
                .get());

        AppointmentNotes appointmentNotes = ParserUtil.parseAppointmentNotes(
                argMultimap.getValue(PREFIX_APPOINTMENT_NOTE).isPresent()
                ? argMultimap.getValue(PREFIX_APPOINTMENT_NOTE).get()
                : null
        );

        return new AddAppointmentCommand(index, appointmentTime, appointmentNotes);
    }
}
