package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateOfBirthTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsIllegalArgumentException() {
        String invalidDob = "not-a-date";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDob));
    }

    @Test
    public void isValidDateOfBirth() {
        // null date of birth
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDateOfBirth(null));

        // invalid date of birth
        assertFalse(DateOfBirth.isValidDateOfBirth("")); // empty string
        assertFalse(DateOfBirth.isValidDateOfBirth(" ")); // spaces only
        assertFalse(DateOfBirth.isValidDateOfBirth("not-a-date"));
        assertFalse(DateOfBirth.isValidDateOfBirth("2020-02-30")); // invalid date
        assertFalse(DateOfBirth.isValidDateOfBirth("31/14/1999")); // wrong month

        // valid date of birth
        assertTrue(DateOfBirth.isValidDateOfBirth("31-12-1999"));
        assertTrue(DateOfBirth.isValidDateOfBirth("01-01-2000"));
    }

    @Test
    public void equals() {
        DateOfBirth dob = new DateOfBirth("01-01-2000");

        // same values -> returns true
        assertTrue(dob.equals(new DateOfBirth("01-01-2000")));

        // same object -> returns true
        assertTrue(dob.equals(dob));

        // null -> returns false
        assertFalse(dob.equals(null));

        // different types -> returns false
        assertFalse(dob.equals(5.0f));

        // different values -> returns false
        assertFalse(dob.equals(new DateOfBirth("31-12-1999")));
    }

    @Test
    public void hashCode_sameValue_equals() {
        DateOfBirth dob1 = new DateOfBirth("01-01-2000");
        DateOfBirth dob2 = new DateOfBirth("01-01-2000");
        assertEquals(dob1.hashCode(), dob2.hashCode());
    }
}
