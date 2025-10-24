package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment APPT_ALICE = new AppointmentBuilder()
            .withNotes("Routine checkup")
            .withTime("01-12-2023 09:00")
            .withPatientId(TypicalPersons.ALICE.getIdentityNumber())
            .build();

    public static final Appointment APPT_BENSON = new AppointmentBuilder()
            .withNotes("Dental cleaning")
            .withTime("02-12-2023 10:30")
            .withPatientId(TypicalPersons.BENSON.getIdentityNumber())
            .build();

    public static final Appointment APPT_CARL = new AppointmentBuilder()
            .withNotes("Consultation for flu symptoms")
            .withTime("03-12-2023 11:15")
            .withPatientId(TypicalPersons.CARL.getIdentityNumber())
            .build();

    public static final Appointment APPT_DANIEL = new AppointmentBuilder()
            .withNotes("Follow-up appointment")
            .withTime("04-12-2023 14:00")
            .withPatientId(TypicalPersons.DANIEL.getIdentityNumber())
            .build();

    public static final Appointment APPT_ELLE = new AppointmentBuilder()
            .withNotes("Vaccination")
            .withTime("05-12-2023 15:45")
            .withPatientId(TypicalPersons.ELLE.getIdentityNumber())
            .build();

    public static final Appointment APPT_FIONA = new AppointmentBuilder()
            .withNotes("Blood test")
            .withTime("06-12-2023 08:30")
            .withPatientId(TypicalPersons.FIONA.getIdentityNumber())
            .build();

    public static final Appointment APPT_GEORGE = new AppointmentBuilder()
            .withNotes("Annual physical exam")
            .withTime("07-12-2023 13:00")
            .withPatientId(TypicalPersons.GEORGE.getIdentityNumber())
            .build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns a list of typical appointments.
     */
    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(
                APPT_ALICE, APPT_BENSON, APPT_CARL, APPT_DANIEL, APPT_ELLE, APPT_FIONA, APPT_GEORGE
        ));
    }
}

