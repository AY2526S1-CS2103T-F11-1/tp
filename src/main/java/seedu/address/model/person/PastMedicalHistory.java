package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a person's past medical history in the HealthNote.
 */
public class PastMedicalHistory {
    public static final String MESSAGE_CONSTRAINTS =
            "Past medical history can be any string, and it should not be blank.";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code PastMedicalHistory}.
     *
     * @param medicalHistory A string representing the past medical history. Can be empty.
     */
    public PastMedicalHistory(String medicalHistory) {
        requireNonNull(medicalHistory);
        checkArgument(isValidPastMedicalHistory(medicalHistory), MESSAGE_CONSTRAINTS);
        this.value = medicalHistory;
    }

    /**
     * Returns true if a given string is a valid past medical history.
     */
    public static boolean isValidPastMedicalHistory(String test) {
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
        if (!(other instanceof PastMedicalHistory)) {
            return false;
        }

        PastMedicalHistory otherMedicalHistory = (PastMedicalHistory) other;
        return value.equals(otherMedicalHistory.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
