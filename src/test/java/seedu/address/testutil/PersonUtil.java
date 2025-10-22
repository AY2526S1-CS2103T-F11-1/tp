package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALCOHOLIC_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IDENTITY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAST_DIAGNOSES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SMOKING_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Allergy;
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
        sb.append(PREFIX_BLOOD_TYPE + person.getBloodType().bloodType + " ");
        sb.append(PREFIX_DATE_OF_BIRTH + person.getDateOfBirth().toString() + " ");
        sb.append(PREFIX_ALCOHOLIC_RECORD + person.getAlcoholicRecord().toString() + " ");
        sb.append(PREFIX_GENDER + person.getGender().gender + " ");
        sb.append(PREFIX_SMOKING_RECORD + person.getSmokingRecord().toString() + " ");
        sb.append(PREFIX_PAST_DIAGNOSES + person.getPastDiagnoses().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getAllergies().stream().forEach(
                s -> sb.append(PREFIX_ALLERGY + s.allergyName + " ")
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
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
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
        descriptor.getPastDiagnoses().ifPresent(pastDiagnoses -> sb.append(PREFIX_PAST_DIAGNOSES)
                .append(pastDiagnoses.value).append(" "));
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
        return sb.toString();
    }
}
