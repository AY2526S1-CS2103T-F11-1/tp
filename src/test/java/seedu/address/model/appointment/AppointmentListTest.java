package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAppointments.APPT_ALICE;
import static seedu.address.testutil.TypicalAppointments.APPT_BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.testutil.AppointmentBuilder;

public class AppointmentListTest {

    private final AppointmentList appointmentList = new AppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(appointmentList.contains(APPT_ALICE));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        appointmentList.add(APPT_ALICE);
        assertTrue(appointmentList.contains(APPT_ALICE));
    }

    @Test
    public void contains_appointmentWithSameIdentityFieldsInList_returnsTrue() {
        appointmentList.add(APPT_ALICE);
        Appointment editedAlice = new AppointmentBuilder(APPT_ALICE)
                .withNotes("Changed notes")
                .build();
        assertTrue(appointmentList.contains(editedAlice));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        appointmentList.add(APPT_ALICE);
        assertThrows(DuplicateAppointmentException.class, () -> appointmentList.add(APPT_ALICE));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.setAppointment(null, APPT_ALICE));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.setAppointment(APPT_ALICE, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> appointmentList.setAppointment(APPT_ALICE, APPT_ALICE));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        appointmentList.add(APPT_ALICE);
        appointmentList.setAppointment(APPT_ALICE, APPT_ALICE);
        AppointmentList expectedList = new AppointmentList();
        expectedList.add(APPT_ALICE);
        assertEquals(expectedList, appointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        appointmentList.add(APPT_ALICE);
        Appointment editedAlice = new AppointmentBuilder(APPT_ALICE)
                .withNotes("Changed notes")
                .build();
        appointmentList.setAppointment(APPT_ALICE, editedAlice);
        AppointmentList expectedList = new AppointmentList();
        expectedList.add(editedAlice);
        assertEquals(expectedList, appointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        appointmentList.add(APPT_ALICE);
        appointmentList.setAppointment(APPT_ALICE, APPT_BENSON);
        AppointmentList expectedList = new AppointmentList();
        expectedList.add(APPT_BENSON);
        assertEquals(expectedList, appointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        appointmentList.add(APPT_ALICE);
        appointmentList.add(APPT_BENSON);
        assertThrows(DuplicateAppointmentException.class, () ->
                appointmentList.setAppointment(APPT_ALICE, APPT_BENSON));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> appointmentList.remove(APPT_ALICE));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        appointmentList.add(APPT_ALICE);
        appointmentList.remove(APPT_ALICE);
        AppointmentList expectedList = new AppointmentList();
        assertEquals(expectedList, appointmentList);
    }

    @Test
    public void setAppointments_nullAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.setAppointments((AppointmentList) null));
    }

    @Test
    public void setAppointments_appointmentList_replacesOwnListWithProvidedAppointmentList() {
        appointmentList.add(APPT_ALICE);
        AppointmentList expectedList = new AppointmentList();
        expectedList.add(APPT_BENSON);
        appointmentList.setAppointments(expectedList);
        assertEquals(expectedList, appointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        appointmentList.add(APPT_ALICE);
        List<Appointment> apptList = Collections.singletonList(APPT_BENSON);
        appointmentList.setAppointments(apptList);
        AppointmentList expectedList = new AppointmentList();
        expectedList.add(APPT_BENSON);
        assertEquals(expectedList, appointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(APPT_ALICE, APPT_ALICE);
        assertThrows(DuplicateAppointmentException.class, () ->
                appointmentList.setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> appointmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(appointmentList.asUnmodifiableObservableList().toString(), appointmentList.toString());
    }

    @Test
    public void removeAll_existingAppointments_removesAppointments() {
        appointmentList.add(APPT_ALICE);
        appointmentList.add(APPT_BENSON);
        appointmentList.removeAll(APPT_ALICE, APPT_BENSON);
        AppointmentList expectedList = new AppointmentList();
        assertEquals(expectedList, appointmentList);
    }

    @Test
    public void removeAll_appointmentsDoNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> appointmentList.removeAll(APPT_ALICE, APPT_BENSON));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        assertTrue(appointmentList.equals(appointmentList));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        assertFalse(appointmentList.equals("not an appointment list"));
    }

    @Test
    public void equals_null_returnsFalse() {
        assertFalse(appointmentList.equals(null));
    }

    @Test
    public void equals_listsWithSameAppointments_returnsTrue() {
        AppointmentList list1 = new AppointmentList();
        AppointmentList list2 = new AppointmentList();
        list1.add(APPT_ALICE);
        list2.add(APPT_ALICE);
        assertTrue(list1.equals(list2));
    }

    @Test
    public void equals_listsWithDifferentAppointments_returnsFalse() {
        AppointmentList list1 = new AppointmentList();
        AppointmentList list2 = new AppointmentList();
        list1.add(APPT_ALICE);
        list2.add(APPT_BENSON);
        assertFalse(list1.equals(list2));
    }

    @Test
    public void hashCode_listsWithSameAppointments_sameHashCode() {
        AppointmentList list1 = new AppointmentList();
        AppointmentList list2 = new AppointmentList();
        list1.add(APPT_ALICE);
        list2.add(APPT_ALICE);
        assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void hashCode_listsWithDifferentAppointments_differentHashCode() {
        AppointmentList list1 = new AppointmentList();
        AppointmentList list2 = new AppointmentList();
        list1.add(APPT_ALICE);
        list2.add(APPT_BENSON);
        assertNotEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void iterator_iteratesOverAppointments() {
        appointmentList.add(APPT_ALICE);
        appointmentList.add(APPT_BENSON);
        Iterator<Appointment> iterator = appointmentList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(APPT_ALICE, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(APPT_BENSON, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
