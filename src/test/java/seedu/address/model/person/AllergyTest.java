package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AllergyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Allergy(null));
    }

    @Test
    public void constructor_invalidAllergyName_throwsIllegalArgumentException() {
        String invalidAllergyName = "";
        String invalidAllergyName2 = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Allergy(invalidAllergyName));
        assertThrows(IllegalArgumentException.class, () -> new Allergy(invalidAllergyName2));
    }

    @Test
    public void isValidAllergyName() {
        // null allergy name
        assertThrows(NullPointerException.class, () -> Allergy.isValidAllergyName(null));

        // invalid allergy names
        assertFalse(Allergy.isValidAllergyName("       ")); // spaces only

        // valid allergy names
        assertTrue(Allergy.isValidAllergyName("peanuts")); // alphabets only
        assertTrue(Allergy.isValidAllergyName("12345")); // numbers only
    }

}
