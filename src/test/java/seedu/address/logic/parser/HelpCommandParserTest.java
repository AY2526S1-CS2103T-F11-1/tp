package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {

    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_noArgs_returnsDefaultHelpCommand() {
        HelpCommand result1 = parser.parse("");
        HelpCommand result2 = parser.parse("   \t  \n");

        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(new HelpCommand(), result1);
        assertEquals(new HelpCommand(), result2);
    }

    @Test
    public void parse_singleArg_returnsAlternateHelpCommand() {
        HelpCommand result = parser.parse("add");
        assertEquals(new HelpCommand("add"), result);

        // with surrounding whitespace
        HelpCommand resultSpaced = parser.parse("   list   ");
        assertEquals(new HelpCommand("list"), resultSpaced);
    }
}
