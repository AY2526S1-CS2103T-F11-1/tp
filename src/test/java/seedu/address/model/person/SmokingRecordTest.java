package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SmokingRecordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SmokingRecord(null));
    }

    @Test
    public void constructor_invalidSmokingRecord_throwsIllegalArgumentException() {
        String invalidRecord = " ";
        assertThrows(IllegalArgumentException.class,
                SmokingRecord.MESSAGE_CONSTRAINTS, () -> new SmokingRecord(invalidRecord));
    }

    @Test
    public void constructor_validSmokingRecord_accepted() {
        // boundary case: single character
        new SmokingRecord("a");

        // normal cases
        new SmokingRecord("Non-smoker");
        new SmokingRecord("Heavy smoker");
        new SmokingRecord("Occasional smoker");
        new SmokingRecord("Former smoker");

        // special characters
        new SmokingRecord("Quit 2 years ago");
        new SmokingRecord("20 cigarettes/day");
    }

    @Test
    public void isValidSmokingRecord() {
        // null smoking record
        assertThrows(NullPointerException.class, () -> SmokingRecord.isValidSmokingRecord(null));

        // invalid smoking records
        assertFalse(SmokingRecord.isValidSmokingRecord("")); // empty string
        assertFalse(SmokingRecord.isValidSmokingRecord(" ")); // spaces only
        assertFalse(SmokingRecord.isValidSmokingRecord("   \t\n")); // multiple whitespace
        assertFalse(SmokingRecord.isValidSmokingRecord(" Non-smoker")); // leading space
        assertFalse(SmokingRecord.isValidSmokingRecord("   Quitter")); // leading spaces

        // valid smoking records (any non-null, non-empty string)
        assertTrue(SmokingRecord.isValidSmokingRecord("Non-smoker"));
        assertTrue(SmokingRecord.isValidSmokingRecord("Heavy smoker"));
        assertTrue(SmokingRecord.isValidSmokingRecord("Occasional"));
        assertTrue(SmokingRecord.isValidSmokingRecord("Quitter"));
        assertTrue(SmokingRecord.isValidSmokingRecord("Former smoker"));
        assertTrue(SmokingRecord.isValidSmokingRecord("20 cigarettes/day"));
        assertTrue(SmokingRecord.isValidSmokingRecord("Quit 2 years ago"));

        // single characters
        assertTrue(SmokingRecord.isValidSmokingRecord("Y"));
        assertTrue(SmokingRecord.isValidSmokingRecord("N"));

        // case sensitive variations
        assertTrue(SmokingRecord.isValidSmokingRecord("Non-Smoker"));
        assertTrue(SmokingRecord.isValidSmokingRecord("heavy SMOKER"));
    }

    @Test
    public void equals() {
        SmokingRecord nonSmoker = new SmokingRecord("Non-smoker");
        SmokingRecord heavySmoker = new SmokingRecord("Heavy smoker");
        SmokingRecord occasional = new SmokingRecord("Occasional smoker");

        // same values -> returns true
        assertTrue(nonSmoker.equals(new SmokingRecord("Non-smoker")));

        // CASE-INSENSITIVE: returns true
        assertTrue(nonSmoker.equals(new SmokingRecord("non-smoker")));
        assertTrue(heavySmoker.equals(new SmokingRecord("heavy smoker")));

        // same object -> returns true
        assertTrue(nonSmoker.equals(nonSmoker));

        // null -> returns false
        assertFalse(nonSmoker.equals(null));

        // different types -> returns false
        assertFalse(nonSmoker.equals(5.0f));

        // different values -> returns false
        assertFalse(nonSmoker.equals(heavySmoker));
        assertFalse(nonSmoker.equals(occasional));

        // trailing spaces -> returns true (equalsIgnoreCase handles)
        assertTrue(new SmokingRecord("Non-smoker ").equals(new SmokingRecord("non-smoker ")));
        assertTrue(new SmokingRecord("Heavy smoker ").equals(new SmokingRecord("heavy smoker ")));
    }
}
