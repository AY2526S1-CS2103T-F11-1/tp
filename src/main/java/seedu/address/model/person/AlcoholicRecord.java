package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a person's alcoholic status in the HealthNote address book.
 * Stores a string value, restricted to "Yes" or "No" (case-insensitive).
 */
public class AlcoholicRecord {
    public static final String MESSAGE_CONSTRAINTS =
            "Alcoholic record can be any string, and it should not be blank.";

    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String alcoholicRecord;

    /**
     * Constructs an {@code AlcoholicRecord}.
     *
     * @param alcoholicRecord An alcoholic record string.
     */
    public AlcoholicRecord(String alcoholicRecord) {
        requireNonNull(alcoholicRecord);
        checkArgument(isValidAlcoholicRecord(alcoholicRecord), MESSAGE_CONSTRAINTS);
        this.alcoholicRecord = alcoholicRecord;
    }

    /**
     * Returns true if a given string is a valid alcoholic record.
     */
    public static boolean isValidAlcoholicRecord(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation for display.
     */
    @Override
    public String toString() {
        return alcoholicRecord;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AlcoholicRecord)) {
            return false;
        }

        AlcoholicRecord otherRecord = (AlcoholicRecord) other;
        return alcoholicRecord.equalsIgnoreCase(otherRecord.alcoholicRecord);
    }

    @Override
    public int hashCode() {
        return alcoholicRecord.hashCode();
    }
}
