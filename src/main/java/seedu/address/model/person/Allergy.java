package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Allergy in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAllergyName(String)}
 */
public class Allergy {

    public static final String MESSAGE_CONSTRAINTS =
            "Allergy can take any values, and should not be blank";
    /*
     * The first character of allergy must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[^\\s].*";

    public final String allergyName;

    /**
     * Constructs a {@code Allergy}.
     *
     * @param allergyName A valid allergy name.
     */
    public Allergy(String allergyName) {
        requireNonNull(allergyName);
        checkArgument(isValidAllergyName(allergyName), MESSAGE_CONSTRAINTS);
        this.allergyName = allergyName;
    }

    /**
     * Returns true if a given string is a valid allergy name.
     */
    public static boolean isValidAllergyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Allergy)) {
            return false;
        }

        Allergy otherAllergy = (Allergy) other;
        return allergyName.equals(otherAllergy.allergyName);
    }

    @Override
    public int hashCode() {
        return allergyName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[" + allergyName + "]";
    }

}
