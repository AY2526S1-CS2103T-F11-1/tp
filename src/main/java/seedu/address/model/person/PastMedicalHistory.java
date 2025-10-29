package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a person's past medical history in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPastMedicalHistory(String)}
 */
public class PastMedicalHistory {
    public static final String MESSAGE_CONSTRAINTS = "Past medical history can be any string, "
            + "and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs a {@code PastMedicalHistory}.
     *
     * @param medicalHistory A valid past medical history.
     */
    public PastMedicalHistory(String medicalHistory) {
        requireNonNull(medicalHistory);
        if (medicalHistory.isEmpty()) {
            this.value = "None";
        } else {
            this.value = medicalHistory;
        }
    }

    /**
     * Returns true if a given string is a valid past medical history.
     * An empty string is also considered valid, indicating no past medical history.
     */
    public static boolean isValidPastMedicalHistory(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
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
