package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a person's past diagnoses in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPastDiagnoses(String)}
 */
public class PastDiagnoses {
    public static final String MESSAGE_CONSTRAINTS = "Past diagnoses can be any string, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs a {@code PastDiagnoses}.
     *
     * @param diagnoses A valid past diagnoses.
     */
    public PastDiagnoses(String diagnoses) {
        requireNonNull(diagnoses);
        if (diagnoses.isEmpty()) {
            this.value = "None";
        } else {
            this.value = diagnoses;
        }
    }

    /**
     * Returns true if a given string is a valid past diagnoses.
     * An empty string is also considered valid, indicating no past diagnoses.
     */
    public static boolean isValidPastDiagnoses(String test) {
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
        if (!(other instanceof PastDiagnoses)) {
            return false;
        }

        PastDiagnoses otherDiagnoses = (PastDiagnoses) other;
        return value.equals(otherDiagnoses.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
