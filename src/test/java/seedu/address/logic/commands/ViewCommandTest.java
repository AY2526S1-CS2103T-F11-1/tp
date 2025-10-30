package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;



public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ViewCommand firstViewCommand = new ViewCommand(Index.fromZeroBased(1));
        ViewCommand secondViewCommand = new ViewCommand(Index.fromZeroBased(2));

        // same object -> returns true
        assertTrue(firstViewCommand.equals(firstViewCommand));

        // same values -> returns true
        ViewCommand firstViewCommandCopy = new ViewCommand(Index.fromZeroBased(1));
        assertTrue(firstViewCommand.equals(firstViewCommandCopy));

        // different types -> returns false
        assertFalse(firstViewCommand.equals(1));

        // null -> returns false
        assertFalse(firstViewCommand.equals(null));

        // different index -> returns false
        assertFalse(firstViewCommand.equals(secondViewCommand));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromZeroBased(1);
        ViewCommand viewCommand = new ViewCommand(index);
        String expected = ViewCommand.class.getCanonicalName() + "{targetIndex=" + index + "}";
        assertEquals(expected, viewCommand.toString());
    }

    @Test
    public void execute_validIndex_success() {
        Index index = Index.fromZeroBased(1);
        Person personToView = model.getFilteredPersonList().get(index.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(index);

        CommandResult expectedResult = new CommandResult(
                String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS, Messages.format(personToView)),
                false,
                false,
                personToView,
                null);

        expectedModel.getFilteredAppointmentList(personToView.getIdentityNumber());

        assertCommandSuccess(viewCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index index = Index.fromZeroBased(100);
        ViewCommand command = new ViewCommand(index);
        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
