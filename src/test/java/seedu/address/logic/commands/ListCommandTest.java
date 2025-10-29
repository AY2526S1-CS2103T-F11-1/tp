package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.ListCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.ListCommand.MESSAGE_WARNING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_withExtraArgs_appendsWarning() {
        // any non-empty string marks hasExtraArgs = true
        ListCommand commandWithArgs = new ListCommand("anything");
        CommandResult expected =
                new CommandResult(MESSAGE_SUCCESS + MESSAGE_WARNING);

        assertCommandSuccess(commandWithArgs, model, expected, expectedModel);
    }

    @Test
    public void equals() {
        ListCommand noArgsA = new ListCommand();
        ListCommand noArgsB = new ListCommand();
        ListCommand withArgsA = new ListCommand("topic");
        ListCommand withArgsB = new ListCommand("another topic");

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
