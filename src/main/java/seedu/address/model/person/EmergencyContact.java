package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Person's emergency contact in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmergencyContact(String)}
 */
public class EmergencyContact {

    public static final String MESSAGE_FORMAT_CONSTRAINTS =
            "Emergency contact should be in this format: {[relationship]} {phone}\n"
                    + "example: [mother] 123456789";

    /**
     * Emergency contact must be in the format:
     * [relationship] phone_number
     * where the relationship is inside square brackets and the phone number
     * contains at least three digits (e.g. [mother] 91234567).
     */
    public static final String VALIDATION_REGEX = "^\\[[^\\]]+\\]\\s+\\+?[\\d\\s-]{3,}$";
    public final Phone phone;
    public final String relationship;

    /**
     * Constructs an {@code Emergency Contact}.
     *
     * @param relationshipAndPhone The relationship to the patient and the phone number of the
     *                             contact in the specified format.
     */
    public EmergencyContact(String relationshipAndPhone) {
        requireNonNull(relationshipAndPhone);
        checkArgument(isValidEmergencyContact(relationshipAndPhone), MESSAGE_FORMAT_CONSTRAINTS);

        String trimmed = relationshipAndPhone.trim();

        int startBracket = trimmed.indexOf('[');
        int endBracket = trimmed.indexOf(']');
        String relationshipPart = trimmed.substring(startBracket + 1, endBracket).trim();
        String phonePart = trimmed.substring(endBracket + 1).trim();

        this.relationship = relationshipPart;
        this.phone = new Phone(phonePart);
    }

    /**
     * Returns true if the given string matches the format for emergency number.
     */
    public static boolean isValidEmergencyContact(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "[" + relationship + "] " + phone;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmergencyContact)) {
            return false;
        }

        EmergencyContact otherEmergencyContact = (EmergencyContact) other;
        return relationship.equals(otherEmergencyContact.relationship) && phone.equals(otherEmergencyContact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relationship, phone);
    }

}
