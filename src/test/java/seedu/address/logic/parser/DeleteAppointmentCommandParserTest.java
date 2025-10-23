package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/*
 * Tests for DeleteAppointmentCommandParser.
 */
public class DeleteAppointmentCommandParserTest {

    private final DeleteAppointmentCommandParser parser = new DeleteAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsCommand() throws Exception {
        String input = "1";
        DeleteAppointmentCommand cmd = parser.parse(input);
        assertEquals(new DeleteAppointmentCommand(Index.fromOneBased(1)), cmd);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String input = "one";
        assertThrows(ParseException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_empty_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }
}

