package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.TypicalPersons;

/**
 * Tests for DeleteUpcomingAppointmentCommand.
 */
public class DeleteUpcomingAppointmentCommandTest {

    @Test
    public void equals_sameValues_returnsTrue() {
        DeleteUpcomingAppointmentCommand c1 = new DeleteUpcomingAppointmentCommand(Index.fromOneBased(1));
        DeleteUpcomingAppointmentCommand c2 = new DeleteUpcomingAppointmentCommand(Index.fromOneBased(1));
        assertEquals(c1, c2);
    }

    @Test
    public void toString_containsTargetIndex() {
        DeleteUpcomingAppointmentCommand cmd = new DeleteUpcomingAppointmentCommand(Index.fromOneBased(3));
        String s = cmd.toString();
        assertTrue(s.contains("targetIndex"));
    }

    @Test
    public void execute_deleteSuccessful_appointmentRemoved() throws Exception {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Appointment a = model.getUpcomingAppointmentList().get(0);

        DeleteUpcomingAppointmentCommand cmd = new DeleteUpcomingAppointmentCommand(Index.fromOneBased(1));
        CommandResult result = cmd.execute(model);

        // appointment should no longer exist in the model
        assertFalse(model.hasAppointment(a));
        assertEquals(String.format(DeleteUpcomingAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                seedu.address.logic.Messages.format(a)), result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

        DeleteUpcomingAppointmentCommand cmd = new DeleteUpcomingAppointmentCommand(Index.fromOneBased(200));
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }
}
