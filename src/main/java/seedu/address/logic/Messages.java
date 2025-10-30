package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_PARAMETERS =
            "Invalid parameter(s) for '%1$s' command: %2$s."
                    + "\n Please refer to the Help page or type 'help' for the valid parameters";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields = Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Identity Number: ")
                .append(person.getIdentityNumber())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Emergency Contact: ")
                .append(person.getEmergencyContact())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Date of Birth: ")
                .append(person.getDateOfBirth())
                .append("; Blood Type: ")
                .append(person.getBloodType())
                .append("; Gender: ")
                .append(person.getGender())
                .append("; Alcoholic Record: ")
                .append(person.getAlcoholicRecord())
                .append("; Smoking Record: ")
                .append(person.getSmokingRecord())
                .append("; Past Medical History: ")
                .append(person.getPastMedicalHistory())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        builder.append("; Allergies: ");
        person.getAllergies().forEach(builder::append);
        builder.append("; Medicines: ");
        person.getMedicines().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code appointment} for display to the user.
     */
    public static String format(Appointment appointment) {
        final StringBuilder builder = new StringBuilder();
        builder.append(appointment.getDateTime())
                .append("; Identity Number: ")
                .append(appointment.getPatientId())
                .append("; Notes: ")
                .append(appointment.getNotes());
        return builder.toString();
    }

}
