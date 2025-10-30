package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's smoking record in the HealthNote.
 */
public class SmokingRecord {
    public static final String MESSAGE_CONSTRAINTS =
            "Smoking record can be any string, and it should not be blank.";
    /*
     * The first character of the smoking record must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[^\\s].*";
    public final String value;

    /**
     * Constructs a {@code SmokingRecord}.
     *
     * @param record A string.
     */
    public SmokingRecord(String record) {
        requireNonNull(record);
        checkArgument(isValidSmokingRecord(record), MESSAGE_CONSTRAINTS);
        this.value = record;
    }

    /**
     * Returns true if a given string is a valid smoking record.
     */
    public static boolean isValidSmokingRecord(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SmokingRecord)) {
            return false;
        }

        SmokingRecord otherRecord = (SmokingRecord) other;
        return value.equalsIgnoreCase(otherRecord.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
