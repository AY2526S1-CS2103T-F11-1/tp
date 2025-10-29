package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PastMedicalHistoryTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PastMedicalHistory(null));
    }

    @Test
    public void constructor_emptyString_setsValueToNone() {
        PastMedicalHistory pd = new PastMedicalHistory("");
        assertTrue(pd.value.equals("None"));
    }

    @Test
    public void constructor_validString_setsValueCorrectly() {
        String validMedicalHistory = "Diabetes, Hypertension";
        PastMedicalHistory pd = new PastMedicalHistory(validMedicalHistory);
        assertTrue(pd.value.equals(validMedicalHistory));
    }

    @Test
    public void isValidPastMedicalHistory() {
        // valid past medical histories
        assertTrue(seedu.address.model.person.PastMedicalHistory.isValidPastMedicalHistory("Diabetes"));
        assertTrue(seedu.address.model.person.PastMedicalHistory.isValidPastMedicalHistory("Hypertension, Asthma"));
        assertTrue(seedu.address.model.person.PastMedicalHistory.isValidPastMedicalHistory("None"));

        // empty string is valid
        assertTrue(seedu.address.model.person.PastMedicalHistory.isValidPastMedicalHistory(""));

        // invalid past medical histories
        assertFalse(seedu.address.model.person.PastMedicalHistory.isValidPastMedicalHistory(" ")); // only whitespace
        assertFalse(seedu.address.model.person.PastMedicalHistory.isValidPastMedicalHistory("   "));
    }

    @Test
    public void equals() {
        PastMedicalHistory pd1 = new PastMedicalHistory("Diabetes");
        PastMedicalHistory pd2 = new PastMedicalHistory("Diabetes");
        PastMedicalHistory pd3 = new PastMedicalHistory("Hypertension");

        // same object -> returns true
        assertTrue(pd1.equals(pd1));

        // same values -> returns true
        assertTrue(pd1.equals(pd2));

        // different values -> returns false
        assertFalse(pd1.equals(pd3));

        // different types -> returns false
        assertFalse(pd1.equals(5));

        // null -> returns false
        assertFalse(pd1.equals(null));
    }
}
