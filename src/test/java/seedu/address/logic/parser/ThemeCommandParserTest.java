package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;

public class ThemeCommandParserTest {

    private final ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_validLightTheme_success() {
        String input = "light";
        ThemeCommand expected = new ThemeCommand("light");
        assertParseSuccess(parser, input, expected);
    }

    @Test
    public void parse_validDarkTheme_success() {
        assertParseSuccess(parser, "dark", new ThemeCommand("dark"));
    }

    @Test
    public void parse_validPinkTheme_success() {
        assertParseSuccess(parser, "pink", new ThemeCommand("pink"));
    }

    @Test
    public void parse_validBlueTheme_success() {
        assertParseSuccess(parser, "blue", new ThemeCommand("blue"));
    }

    @Test
    public void parse_caseInsensitive_success() {
        assertParseSuccess(parser, "Light", new ThemeCommand("light"));
        assertParseSuccess(parser, "DARK", new ThemeCommand("dark"));
        assertParseSuccess(parser, "PiNk", new ThemeCommand("pink"));
    }

    @Test
    public void parse_extraWhitespace_success() {
        assertParseSuccess(parser, "   light   ", new ThemeCommand("light"));
        assertParseSuccess(parser, "\tblue\n", new ThemeCommand("blue"));
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expectedMessage);
        assertParseFailure(parser, "   ", expectedMessage);
    }

    @Test
    public void parse_invalidTheme_throwsCommandException() {
        ThemeCommandParser parser = new ThemeCommandParser();

        assertParseSuccess(parser, "light", new ThemeCommand("light"));
        assertParseSuccess(parser, "dark", new ThemeCommand("dark"));
        assertParseSuccess(parser, "pink", new ThemeCommand("pink"));
        assertParseSuccess(parser, "blue", new ThemeCommand("blue"));

        assertThrows(CommandException.class, ThemeCommand.MESSAGE_UNKNOWN_THEME, () -> {
            ThemeCommand cmd = parser.parse("purple");
            cmd.execute(new ModelManager());
        });
    }
}
