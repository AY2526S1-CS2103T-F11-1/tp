package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's smoking record in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSmokingRecord(String)}
 */
public class SmokingRecord {
    public static final String MESSAGE_CONSTRAINTS =
            "Smoking record cannot be null or empty.";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String smokingRecord;

    /**
     * Constructs a {@code SmokingRecord}.
     *
     * @param record A valid smoking record.
     */
    public SmokingRecord(String record) {
        requireNonNull(record);
        checkArgument(isValidSmokingRecord(record), MESSAGE_CONSTRAINTS);
        this.smokingRecord = record;
    }

    /**
     * Returns if a given string is a valid smoking record.
     */
    public static boolean isValidSmokingRecord(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return smokingRecord;
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
        return smokingRecord.equalsIgnoreCase(otherRecord.smokingRecord);
    }

    @Override
    public int hashCode() {
        return smokingRecord.hashCode();
    }
}
