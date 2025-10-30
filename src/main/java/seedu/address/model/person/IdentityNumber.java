package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Identity Number in the system.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class IdentityNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "The identity number must consist only of letters, digits, or hyphens,"
                    + " and it should not be blank or contain any whitespace characters";

    /*
     * The identity number must consist only of letters, digits or hyphens.
     * It cannot be blank or contain any whitespace characters.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}-]+$";

    public final String identityNumber;

    /**
     * Constructs an {@code Identity number}.
     *
     * @param id A valid id.
     */
    public IdentityNumber(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.identityNumber = id.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid identity number.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return identityNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdentityNumber)) {
            return false;
        }

        IdentityNumber otherId = (IdentityNumber) other;
        return identityNumber.equals(otherId.identityNumber);
    }

    @Override
    public int hashCode() {
        return identityNumber.hashCode();
    }

}
