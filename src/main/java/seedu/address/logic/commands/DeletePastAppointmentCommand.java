package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Deletes an appointment identified using it's displayed index from the address book.
 */
public class DeletePastAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "forget";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the past appointment identified by the index number "
            + "used in the displayed upcoming appointment list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";

    private final Index targetIndex;

    public DeletePastAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> pastAppointmentsList = model.getPastAppointmentList();

        if (targetIndex.getZeroBased() >= pastAppointmentsList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX
                    + ". Please refer to the past appointments list.");
        }

        Appointment appointmentToDelete = pastAppointmentsList.get(targetIndex.getZeroBased());
        model.deleteAppointment(appointmentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                Messages.format(appointmentToDelete)));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePastAppointmentCommand otherDeletePastAppointmentCommand)) {
            return false;
        }

        return targetIndex.equals(otherDeletePastAppointmentCommand.targetIndex);
    }

}
