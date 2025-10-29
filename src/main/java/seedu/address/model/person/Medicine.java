package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's medicine in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMedicineName(String)}
 */
public class Medicine {

    public static final String MESSAGE_CONSTRAINTS =
            "Medicine field should not be blank or start with whitespace";

    /*
     * The first character of the medicine name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[^\\s].*";

    public final String medicine;

    /**
     * Constructs a {@code Medicine}.
     *
     * @param medicine A valid medicine.
     */
    public Medicine(String medicine) {
        requireNonNull(medicine);
        checkArgument(isValidMedicineName(medicine), MESSAGE_CONSTRAINTS);
        this.medicine = medicine;
    }

    /**
     * Returns true if a given string is a valid medicine.
     */
    public static boolean isValidMedicineName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Medicine)) {
            return false;
        }

        Medicine otherMedicine = (Medicine) other;
        return medicine.equals(otherMedicine.medicine);
    }

    @Override
    public int hashCode() {
        return medicine.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[" + medicine + "]";
    }

}
