package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentNotes;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.IdentityNumber;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NOTES = "Follow up in 2 weeks.";
    public static final String DEFAULT_TIME = "01-12-2023 14:30";
    public static final Person DEFAULT_PATIENT = new PersonBuilder().build();

    private AppointmentNotes notes;
    private AppointmentTime time;
    private IdentityNumber patientId;

    /**
     * Creates an {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        notes = new AppointmentNotes(DEFAULT_NOTES);
        time = new AppointmentTime(DEFAULT_TIME);
        patientId = DEFAULT_PATIENT.getIdentityNumber();
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        notes = appointmentToCopy.getNotes();
        time = appointmentToCopy.getDateTime();
        patientId = appointmentToCopy.getPatientId();
    }

    /**
     * Sets the {@code AppointmentNotes} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withNotes(String notes) {
        this.notes = new AppointmentNotes(notes);
        return this;
    }

    /**
     * Sets the {@code AppointmentTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTime(String time) {
        this.time = new AppointmentTime(time);
        return this;
    }

    /**
     * Sets the {@code Person} (patient) of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientId(IdentityNumber patientId) {
        this.patientId = patientId;
        return this;
    }

    /**
     * Builds the {@code Appointment} with the specified parameters.
     */
    public Appointment build() {
        return new Appointment(notes, time, patientId);
    }
}

