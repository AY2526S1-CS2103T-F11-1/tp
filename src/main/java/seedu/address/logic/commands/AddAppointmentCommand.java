package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentNotes;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Person;

/**
 * Represents a command to add an appointment for a specific patient identified by their index in the displayed
 * patient list.
 * <p>
 * The appointment consists of an appointment time and optional appointment notes. The command ensures that no
 * duplicate appointments are added for the same patient and throws an exception if the index provided is invalid
 * or if the appointment already exists.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "schedule";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the appointment for the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_APPOINTMENT_TIME + "APPOINTMENT_TIME "
            + "[" + PREFIX_APPOINTMENT_NOTE + "APPOINTMENT_NOTE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT_TIME + "13-10-2025 10:00 "
            + PREFIX_APPOINTMENT_NOTE + "Needs IV Drip";

    public static final String MESSAGE_SUCCESS = "New appointment added for patient: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists for this patient";

    private final Index index;
    private final AppointmentTime appointmentTime;
    private final AppointmentNotes appointmentNotes;

    /**
     * @param index of the person in the filtered person list to edit
     * @param appointmentTime Date Time of the appointment with the person
     * @param appointmentNotes Notes of the appointment with the person
     */
    public AddAppointmentCommand(Index index, AppointmentTime appointmentTime, AppointmentNotes appointmentNotes) {
        requireNonNull(index);
        requireNonNull(appointmentTime);
        requireNonNull(appointmentNotes);

        this.index = index;
        this.appointmentTime = appointmentTime;
        this.appointmentNotes = appointmentNotes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personWithAppointment = lastShownList.get(index.getZeroBased());
        Appointment appointment = new Appointment(appointmentNotes, appointmentTime,
                personWithAppointment.getIdentityNumber());

        if (model.hasAppointment(appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }
        model.addAppointment(appointment);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(appointment)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppointmentCommand otherAddAppointmentCommand)) {
            return false;
        }

        return index.equals(otherAddAppointmentCommand.index)
                && appointmentTime.equals(otherAddAppointmentCommand.appointmentTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("appointmentTime", appointmentTime)
                .add("appointmentNotes", appointmentNotes)
                .toString();
    }
}
