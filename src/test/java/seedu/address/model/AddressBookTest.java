package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPT_ALICE;
import static seedu.address.testutil.TypicalAppointments.APPT_BENSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + ", "
                + "appointments=" + addressBook.getAppointmentList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    public void constructor_appointments() {
        assertEquals(Collections.emptyList(), addressBook.getAppointmentList());
    }

    @Test
    public void resetData_withDuplicateAppointments_throwsDuplicateAppointmentException() {
        // Two appointments with the same identity fields
        Appointment editedApptAlice = new seedu.address.testutil.AppointmentBuilder(APPT_ALICE)
                .withNotes("Changed notes")
                .build();
        List<Appointment> newAppointments = Arrays.asList(APPT_ALICE, editedApptAlice);
        AddressBookStub newData = new AddressBookStub(Collections.emptyList(), newAppointments);

        assertThrows(DuplicateAppointmentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasAppointment(null));
    }

    @Test
    public void hasAppointment_appointmentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasAppointment(APPT_ALICE));
    }

    @Test
    public void hasAppointment_appointmentInAddressBook_returnsTrue() {
        addressBook.addAppointment(APPT_ALICE);
        assertTrue(addressBook.hasAppointment(APPT_ALICE));
    }

    @Test
    public void hasAppointment_appointmentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addAppointment(APPT_ALICE);
        Appointment editedApptAlice = new seedu.address.testutil.AppointmentBuilder(APPT_ALICE)
                .withNotes("Changed notes")
                .build();
        assertTrue(addressBook.hasAppointment(editedApptAlice));
    }

    @Test
    public void getAppointmentList_modifyList_throwsUnsupportedOperationException() {
        addressBook.addAppointment(APPT_ALICE);
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getAppointmentList().remove(0));
    }

    @Test
    public void addAppointment_duplicateAppointment_throwsDuplicateAppointmentException() {
        addressBook.addAppointment(APPT_ALICE);
        assertThrows(DuplicateAppointmentException.class, () -> addressBook.addAppointment(APPT_ALICE));
    }

    @Test
    public void removeAppointment_existingAppointment_removesAppointment() {
        addressBook.addAppointment(APPT_ALICE);
        addressBook.removeAppointment(APPT_ALICE);
        assertFalse(addressBook.hasAppointment(APPT_ALICE));
    }

    @Test
    public void removeAppointment_appointmentDoesNotExist_throwsException() {
        assertThrows(seedu.address.model.appointment.exceptions.AppointmentNotFoundException.class, () ->
                addressBook.removeAppointment(APPT_ALICE));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        addressBook.addAppointment(APPT_ALICE);
        addressBook.setAppointment(APPT_ALICE, APPT_ALICE);
        assertTrue(addressBook.hasAppointment(APPT_ALICE));
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        addressBook.addAppointment(APPT_ALICE);
        Appointment editedApptAlice = new seedu.address.testutil.AppointmentBuilder(APPT_ALICE)
                .withNotes("Changed notes")
                .build();
        addressBook.setAppointment(APPT_ALICE, editedApptAlice);
        assertTrue(addressBook.hasAppointment(editedApptAlice));
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        addressBook.addAppointment(APPT_ALICE);
        addressBook.setAppointment(APPT_ALICE, APPT_BENSON);
        assertTrue(addressBook.hasAppointment(APPT_BENSON));
        assertFalse(addressBook.hasAppointment(APPT_ALICE));
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        addressBook.addAppointment(APPT_ALICE);
        addressBook.addAppointment(APPT_BENSON);
        assertThrows(DuplicateAppointmentException.class, () -> addressBook.setAppointment(APPT_ALICE, APPT_BENSON));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons and appointments list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons;
        private final ObservableList<Appointment> appointments;

        AddressBookStub(Collection<Person> persons) {
            this(persons, Collections.emptyList());
        }

        AddressBookStub(Collection<Person> persons, Collection<Appointment> appointments) {
            this.persons = FXCollections.observableArrayList();
            this.appointments = FXCollections.observableArrayList();
            this.persons.setAll(persons);
            this.appointments.setAll(appointments);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }
    }

}
