package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.MESSAGE_WARNING;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_withExtraArgs_appendsWarning() {
        // any non-empty string marks hasExtraArgs = true
        HelpCommand commandWithArgs = new HelpCommand("anything");
        CommandResult expected =
                new CommandResult(SHOWING_HELP_MESSAGE + MESSAGE_WARNING, true, false);

        assertCommandSuccess(commandWithArgs, model, expected, expectedModel);
    }

    @Test
    public void equals() {
        HelpCommand noArgsA = new HelpCommand();
        HelpCommand noArgsB = new HelpCommand();
        HelpCommand withArgsA = new HelpCommand("topic");
        HelpCommand withArgsB = new HelpCommand("another topic");

        // same flags → equal
        assertTrue(noArgsA.equals(noArgsB));
        assertTrue(withArgsA.equals(withArgsB)); // both have hasExtraArgs = true

        // different flags → not equal
        assertFalse(noArgsA.equals(withArgsA));

        // self, null, different type
        assertTrue(noArgsA.equals(noArgsA));
        assertFalse(noArgsA.equals(null));
        assertFalse(noArgsA.equals("not a command"));
    }

}
