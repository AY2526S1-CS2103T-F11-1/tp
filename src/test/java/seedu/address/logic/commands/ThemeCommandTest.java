package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ThemeCommandTest {

    private final Model model = new ModelManager();

    @Test
    public void execute_validLightTheme_success() {
        ThemeCommand command = new ThemeCommand("light");
        String expectedMessage = String.format(ThemeCommand.MESSAGE_SUCCESS, "light");

        CommandResult expectedResult = new CommandResult(
                expectedMessage, false, false, null, "/view/light.css");

        assertCommandSuccess(command, model, expectedResult, model);
    }

    @Test
    public void execute_validDarkTheme_success() {
        ThemeCommand command = new ThemeCommand("dark");
        String expectedMessage = String.format(ThemeCommand.MESSAGE_SUCCESS, "dark");

        CommandResult expectedResult = new CommandResult(
                expectedMessage,
                false,
                false,
                null,
                "/view/dark.css"
        );

        assertCommandSuccess(command, model, expectedResult, model);
    }

    @Test
    public void execute_validPinkTheme_success() {
        ThemeCommand command = new ThemeCommand("pink");
        String expectedMessage = String.format(ThemeCommand.MESSAGE_SUCCESS, "pink");

        CommandResult expectedResult = new CommandResult(
                expectedMessage,
                false,
                false,
                null,
                "/view/pink.css"
        );

        assertCommandSuccess(command, model, expectedResult, model);
    }

    @Test
    public void execute_validBlueTheme_success() {
        ThemeCommand command = new ThemeCommand("blue");
        String expectedMessage = String.format(ThemeCommand.MESSAGE_SUCCESS, "blue");

        CommandResult expectedResult = new CommandResult(
                expectedMessage,
                false,
                false,
                null,
                "/view/blue.css"
        );

        assertCommandSuccess(command, model, expectedResult, model);
    }

    @Test
    public void execute_invalidTheme_throwsCommandException() {
        ThemeCommand command = new ThemeCommand("purple");
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void equals() {
        ThemeCommand light = new ThemeCommand("light");
        ThemeCommand lightCopy = new ThemeCommand("light");
        ThemeCommand dark = new ThemeCommand("dark");

        // same object → true
        assertTrue(light.equals(light));

        // same values → true
        assertTrue(light.equals(lightCopy));

        // different theme → false
        assertFalse(light.equals(dark));

        // different type → false
        assertFalse(light.equals(1));

        // null → false
        assertFalse(light.equals(null));
    }
}
