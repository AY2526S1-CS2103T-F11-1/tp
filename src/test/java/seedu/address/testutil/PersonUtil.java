package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALCOHOLIC_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IDENTITY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAST_MEDICAL_HISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SMOKING_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Allergy;
import seedu.address.model.person.Medicine;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_IDENTITY_NUMBER + person.getIdentityNumber().identityNumber + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_EMERGENCY_CONTACT + person.getEmergencyContact().toString() + " ");
        sb.append(PREFIX_BLOOD_TYPE + person.getBloodType().bloodType + " ");
        sb.append(PREFIX_DATE_OF_BIRTH + person.getDateOfBirth().toString() + " ");
        sb.append(PREFIX_ALCOHOLIC_RECORD + person.getAlcoholicRecord().toString() + " ");
        sb.append(PREFIX_GENDER + person.getGender().gender + " ");
        sb.append(PREFIX_SMOKING_RECORD + person.getSmokingRecord().toString() + " ");
        sb.append(PREFIX_PAST_MEDICAL_HISTORY + person.getPastMedicalHistory().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getAllergies().stream().forEach(
                s -> sb.append(PREFIX_ALLERGY + s.allergyName + " ")
        );
        person.getMedicines().stream().forEach(
                s -> sb.append(PREFIX_ALLERGY + s.medicine + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getIdentityNumber().ifPresent(identityNumber -> sb.append(PREFIX_IDENTITY_NUMBER)
                .append(identityNumber.identityNumber).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value)
                .append(" "));
        descriptor.getEmergencyContact().ifPresent(e -> sb.append(PREFIX_EMERGENCY_CONTACT)
                .append(e.toString()).append(" "));
        descriptor.getDateOfBirth().ifPresent(
                dob -> sb.append(PREFIX_DATE_OF_BIRTH).append(dob.toString()).append(" "));
        descriptor.getBloodType().ifPresent(bloodType -> sb.append(PREFIX_NAME).append(bloodType.bloodType)
                .append(" "));
        descriptor.getAlcoholicRecord().ifPresent(alcoholicRecord -> sb.append(PREFIX_ALCOHOLIC_RECORD)
                .append(alcoholicRecord.alcoholicRecord).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.gender)
                .append(" "));
        descriptor.getSmokingRecord().ifPresent(smokingRecord -> sb.append(PREFIX_SMOKING_RECORD)
                .append(smokingRecord.toString()).append(" "));
        descriptor.getPastMedicalHistory().ifPresent(pastMedicalHistory -> sb.append(PREFIX_PAST_MEDICAL_HISTORY)
                .append(pastMedicalHistory.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        sb.append(" ");
        if (descriptor.getAllergies().isPresent()) {
            Set<Allergy> allergy = descriptor.getAllergies().get();
            if (allergy.isEmpty()) {
                sb.append(PREFIX_ALLERGY);
            } else {
                allergy.forEach(s -> sb.append(PREFIX_ALLERGY).append(s.allergyName).append(" "));
            }
        }
        sb.append(" ");
        if (descriptor.getMedicines().isPresent()) {
            Set<Medicine> medicine = descriptor.getMedicines().get();
            if (medicine.isEmpty()) {
                sb.append(PREFIX_MEDICINE);
            } else {
                medicine.forEach(s -> sb.append(PREFIX_MEDICINE).append(s.medicine).append(" "));
            }
        }
        return sb.toString();
    }
}
