package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AppointmentNotesTest {

    @Test
    void constructor_validNotes_success() {
        AppointmentNotes notes = new AppointmentNotes("Follow up in 2 weeks.");
        assertEquals("Follow up in 2 weeks.", notes.fullNotes);
    }

    @Test
    void constructor_nullNotes_success() {
        AppointmentNotes notes = new AppointmentNotes(null);
        assertEquals("", notes.fullNotes);
    }

    @Test
    void isValidNotes_annyNotes_returnsTrue() {
        assertTrue(AppointmentNotes.isValidNotes("Some notes here."));
        assertTrue(AppointmentNotes.isValidNotes("123456"));
        assertTrue(AppointmentNotes.isValidNotes("!@#$%^&*()"));
        assertTrue(AppointmentNotes.isValidNotes("    "));
        assertTrue(AppointmentNotes.isValidNotes(""));
    }

    @Test
    void equals_and_hashCode() {
        AppointmentNotes notes1 = new AppointmentNotes("abc");
        AppointmentNotes notes2 = new AppointmentNotes("abc");
        AppointmentNotes notes3 = new AppointmentNotes("def");
        assertEquals(notes1, notes2);
        assertNotEquals(notes1, notes3);
        assertEquals(notes1.hashCode(), notes2.hashCode());
    }

    @Test
    void toString_returnsNotes() {
        AppointmentNotes notes = new AppointmentNotes("Test notes");
        assertEquals("Test notes", notes.toString());
    }
}

