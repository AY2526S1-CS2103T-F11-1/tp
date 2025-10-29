package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class AppointmentTimeTest {

    @Test
    void constructor_validFormats_success() {
        AppointmentTime t1 = new AppointmentTime("01-12-2023 14:30");
        AppointmentTime t2 = new AppointmentTime("01/12/2023 14:30");
        AppointmentTime t3 = new AppointmentTime("01.12.2023 14:30");
        assertEquals(LocalDateTime.of(2023, 12, 1, 14, 30), t1.getDateTime());
        assertEquals(LocalDateTime.of(2023, 12, 1, 14, 30), t2.getDateTime());
        assertEquals(LocalDateTime.of(2023, 12, 1, 14, 30), t3.getDateTime());
    }

    @Test
    void constructor_invalidFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new AppointmentTime("2023-12-01 14:30"));
        assertThrows(IllegalArgumentException.class, () -> new AppointmentTime("01-12-2023 25:00"));
        assertThrows(IllegalArgumentException.class, () -> new AppointmentTime("01-13-2023 14:30"));
        assertThrows(IllegalArgumentException.class, () -> new AppointmentTime("01-12-2023 14:60"));
        assertThrows(IllegalArgumentException.class, () -> new AppointmentTime("01-12-2023"));
    }

    @Test
    void isValidDateTimeFormat_validAndInvalidCasesFormat() {
        assertTrue(AppointmentTime.isValidDateTimeFormat("01-12-2023 14:30"));
        assertTrue(AppointmentTime.isValidDateTimeFormat("01/12/2023 14:30"));
        assertTrue(AppointmentTime.isValidDateTimeFormat("01.12.2023 14:30"));
        assertFalse(AppointmentTime.isValidDateTimeFormat("2023-12-01 14:30"));
        assertFalse(AppointmentTime.isValidDateTimeFormat("01-12-2023 25:00"));
        assertFalse(AppointmentTime.isValidDateTimeFormat("01-12-2023 14:60"));
        assertFalse(AppointmentTime.isValidDateTimeFormat(""));
    }

    @Test
    void isValidDateTime_invalidCases() {
        assertFalse(AppointmentTime.isValidDateTime("29-02-2025 14:30")); // leap day
        assertFalse(AppointmentTime.isValidDateTime("31/04/2023 14:30")); // wrong day
    }

    @Test
    void toString_returnsFormattedString() {
        AppointmentTime t = new AppointmentTime("02-01-2024 09:15");
        assertEquals("02-01-2024 09:15", t.toString());
    }

    @Test
    void equals_and_hashCode() {
        AppointmentTime t1 = new AppointmentTime("01-12-2023 14:30");
        AppointmentTime t2 = new AppointmentTime("01/12/2023 14:30");
        AppointmentTime t3 = new AppointmentTime("02-12-2023 14:30");
        assertEquals(t1, t2);
        assertNotEquals(t1, t3);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void isBefore_pastDate_returnsTrue() {
        AppointmentTime t1 = new AppointmentTime("01-12-2023 14:30");
        assertTrue(t1.isBefore(LocalDateTime.of(2023, 12, 2, 14, 30)));
    }

    @Test
    void isAfter_futureDate_returnsTrue() {
        AppointmentTime t1 = new AppointmentTime("01-12-2023 14:30");
        assertTrue(t1.isAfter(LocalDateTime.of(2023, 11, 2, 14, 30)));
    }

    @Test
    void isBefore_equalDateTime_returnsFalse() {
        AppointmentTime t1 = new AppointmentTime("01-12-2023 14:30");
        // equal date-time should not be considered before
        assertFalse(t1.isBefore(LocalDateTime.of(2023, 12, 1, 14, 30)));
    }

    @Test
    void isAfter_equalDateTime_returnsFalse() {
        AppointmentTime t1 = new AppointmentTime("01-12-2023 14:30");
        // equal date-time should not be considered after
        assertFalse(t1.isAfter(LocalDateTime.of(2023, 12, 1, 14, 30)));
    }

    @Test
    void isBefore_adjacentMinuteComparisons() {
        AppointmentTime t = new AppointmentTime("01-12-2023 14:30");
        LocalDateTime oneMinuteLater = LocalDateTime.of(2023, 12, 1, 14, 31);
        LocalDateTime oneMinuteEarlier = LocalDateTime.of(2023, 12, 1, 14, 29);

        assertTrue(t.isBefore(oneMinuteLater));
        assertFalse(t.isBefore(oneMinuteEarlier));
    }

    @Test
    void isAfter_adjacentMinuteComparisons() {
        AppointmentTime t = new AppointmentTime("01-12-2023 14:30");
        LocalDateTime oneMinuteLater = LocalDateTime.of(2023, 12, 1, 14, 31);
        LocalDateTime oneMinuteEarlier = LocalDateTime.of(2023, 12, 1, 14, 29);

        assertFalse(t.isAfter(oneMinuteLater));
        assertTrue(t.isAfter(oneMinuteEarlier));
    }


    @Test
    void isBefore_null_throwsNullPointerException() {
        AppointmentTime t = new AppointmentTime("01-12-2023 14:30");
        assertThrows(NullPointerException.class, () -> t.isBefore(null));
    }

    @Test
    void isAfter_null_throwsNullPointerException() {
        AppointmentTime t = new AppointmentTime("01-12-2023 14:30");
        assertThrows(NullPointerException.class, () -> t.isAfter(null));
    }
}
