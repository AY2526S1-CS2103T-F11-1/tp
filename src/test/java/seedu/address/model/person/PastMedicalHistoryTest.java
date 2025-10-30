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
    public void constructor_validString_setsValueCorrectly() {
        String validMedicalHistory = "Diabetes, Hypertension";
        PastMedicalHistory pd = new PastMedicalHistory(validMedicalHistory);
        assertTrue(pd.value.equals(validMedicalHistory));
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
