package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_noArgs_returnsDefaultListCommand() {
        ListCommand result1 = parser.parse("");
        ListCommand result2 = parser.parse("   \t  \n");

        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(new ListCommand(), result1);
        assertEquals(new ListCommand(), result2);
    }

    @Test
    public void parse_singleArg_returnsAlternateListCommand() {
        ListCommand result = parser.parse("add");
        assertEquals(new ListCommand("add"), result);

        // with surrounding whitespace
        ListCommand resultSpaced = parser.parse("   list   ");
        assertEquals(new ListCommand("list"), resultSpaced);
    }
}
