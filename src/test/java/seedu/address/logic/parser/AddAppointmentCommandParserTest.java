package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentNotes;
import seedu.address.model.appointment.AppointmentTime;

/*
 * Tests for AddAppointmentCommandParser.
 */
public class AddAppointmentCommandParserTest {

    private final AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsCommand() throws Exception {
        String input = "1 " + CliSyntax.PREFIX_APPOINTMENT_TIME + "01-01-2025 10:00 "
                + CliSyntax.PREFIX_APPOINTMENT_NOTE + "bring documents";
        AddAppointmentCommand cmd = parser.parse(input);
        AddAppointmentCommand expected = new AddAppointmentCommand(Index.fromOneBased(1),
                new AppointmentTime("01-01-2025 10:00"), new AppointmentNotes("bring documents"));
        assertEquals(expected, cmd);
    }

    @Test
    public void parse_missingTime_throwsParseException() {
        String input = "1 " + CliSyntax.PREFIX_APPOINTMENT_NOTE + "note only";
        assertThrows(ParseException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String input = "a " + CliSyntax.PREFIX_APPOINTMENT_TIME + "01-01-2025 10:00";
        assertThrows(ParseException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_missingAll_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }
}

