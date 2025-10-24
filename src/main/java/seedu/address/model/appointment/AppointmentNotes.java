package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents notes associated with an appointment.
 * The notes can contain any characters and must not be blank.
 * This class is immutable.
 */
public class AppointmentNotes {

    public static final String MESSAGE_CONSTRAINTS =
            "Notes can contain any characters, and it can be blank";

    public final String fullNotes;

    /**
     * Constructs a {@code AppointmentNotes}.
     *
     * @param notes A valid notes string.
     */
    public AppointmentNotes(String notes) {
        notes = notes == null ? "" : notes;
        checkArgument(isValidNotes(notes), MESSAGE_CONSTRAINTS);
        fullNotes = notes;
    }

    /**
     * Always returns true.
     */
    public static boolean isValidNotes(String test) {
        return true;
    }

    @Override
    public String toString() {
        return fullNotes;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentNotes)) {
            return false;
        }

        AppointmentNotes otherNotes = (AppointmentNotes) other;
        return fullNotes.equals(otherNotes.fullNotes);
    }

    @Override
    public int hashCode() {
        return fullNotes.hashCode();
    }
}
