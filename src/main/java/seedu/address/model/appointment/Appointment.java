package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.IdentityNumber;

/**
 * Represents an appointment with a specific patient at a specific date and time, along with associated notes.
 * Each appointment encapsulates the following details:
 * - The date and time of the appointment.
 * - The patient associated with the appointment.
 * - Any additional notes pertaining to the appointment.
 * <p>
 * The class provides methods to retrieve the appointment details, compare appointments for equality,
 * and generate a string representation of the appointment.
 */
public class Appointment {

    private final AppointmentTime dateTime;
    private final IdentityNumber patientId;
    private final AppointmentNotes notes;

    /**
     * Constructs an {@code Appointment} object with the specified notes, date and time, and patient.
     *
     * @param notes The notes associated with the appointment. May be null.
     * @param dateTime The date and time of the appointment. Must not be null.
     * @param patientId The identity number of the person involved in the appointment. Must not be null.
     * @throws NullPointerException If {@code dateTime} or {@code patient} is null.
     */
    public Appointment(AppointmentNotes notes, AppointmentTime dateTime, IdentityNumber patientId) {
        requireAllNonNull(dateTime, patientId);
        this.notes = notes;
        this.dateTime = dateTime;
        this.patientId = patientId;
    }

    public AppointmentNotes getNotes() {
        return notes;
    }

    public AppointmentTime getDateTime() {
        return dateTime;
    }

    public IdentityNumber getPatientId() {
        return patientId;
    }

    /**
     * Checks if the given appointment is the same as the current appointment.
     * Two appointments are considered the same if they have the same date and time and involve the same patient.
     *
     * @param otherAppointment The appointment to compare with this appointment. May be null.
     * @return true if the provided appointment is the same as this appointment based on date, time, and patientId;
     *         false otherwise, or if the given appointment is null.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }
        return otherAppointment != null
                && otherAppointment.getDateTime().equals(getDateTime())
                && otherAppointment.getPatientId().equals(getPatientId());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Appointment otherAppointment)) {
            return false;
        }
        return otherAppointment.getDateTime().equals(getDateTime())
                && otherAppointment.getPatientId().equals(getPatientId())
                && otherAppointment.getNotes().equals(getNotes());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Date: ")
                .append(getDateTime().toString())
                .append(" Patient ID: ")
                .append(getPatientId().toString())
                .append(" Notes: ")
                .append(getNotes().toString());
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, patientId, notes);
    }

}
