package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SmokingRecordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SmokingRecord(null));
    }

    @Test
    public void constructor_validString_setsValueCorrectly() {
        String validSmokingRecord = "Heavy smoker";
        SmokingRecord smokingRecord = new SmokingRecord(validSmokingRecord);
        assertTrue(smokingRecord.value.equals(validSmokingRecord));
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
