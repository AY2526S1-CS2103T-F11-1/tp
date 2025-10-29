package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentNotes;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.AlcoholicRecord;
import seedu.address.model.person.Allergy;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Gender;
import seedu.address.model.person.IdentityNumber;
import seedu.address.model.person.Medicine;
import seedu.address.model.person.Name;
import seedu.address.model.person.PastMedicalHistory;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SmokingRecord;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String id} into an {@code IdentityNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static IdentityNumber parseIdentityNumber(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!IdentityNumber.isValidId(trimmedId)) {
            throw new ParseException(IdentityNumber.MESSAGE_CONSTRAINTS);
        }
        return new IdentityNumber(trimmedId);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String emergencyContact} into an {@code EmergencyContact}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code emergencyContact} is invalid.
     */
    public static EmergencyContact parseEmergencyContact(String emergencyContact) throws ParseException {
        requireNonNull(emergencyContact);
        String trimmedEmergencyContact = emergencyContact.trim();
        if (!EmergencyContact.isValidEmergencyContact(trimmedEmergencyContact)) {
            throw new ParseException(EmergencyContact.MESSAGE_FORMAT_CONSTRAINTS);
        }
        return new EmergencyContact(trimmedEmergencyContact);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String dateOfBirth} into an {@code dateOfBirth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateOfBirth} is invalid.
     */
    public static DateOfBirth parseDateOfBirth(String dateOfBirth) throws ParseException {
        requireNonNull(dateOfBirth);
        String trimmedDateOfBirth = dateOfBirth.trim();
        if (!DateOfBirth.isValidDate(trimmedDateOfBirth)) {
            throw new ParseException(DateOfBirth.MESSAGE_FORMAT_CONSTRAINTS);
        }
        if (!DateOfBirth.isValidDateOfBirth(trimmedDateOfBirth)) {
            throw new ParseException(DateOfBirth.MESSAGE_PAST_DATE_CONSTRAINTS);
        }
        return new DateOfBirth(trimmedDateOfBirth);
    }
    /**
     * Parses a {@code String allergy} into a {@code Allergy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code allergy} is invalid.
     */
    public static Allergy parseAllergy(String allergy) throws ParseException {
        requireNonNull(allergy);
        String trimmedAllergy = allergy.trim();
        if (!Allergy.isValidAllergyName(trimmedAllergy)) {
            throw new ParseException(Allergy.MESSAGE_CONSTRAINTS);
        }
        return new Allergy(trimmedAllergy);
    }

    /**
     * Parses {@code Collection<String> allergies} into a {@code Set<Tag>}.
     */
    public static Set<Allergy> parseAllergies(Collection<String> allergies) throws ParseException {
        requireNonNull(allergies);
        final Set<Allergy> allergySet = new HashSet<>();
        for (String allergyName : allergies) {
            allergySet.add(parseAllergy(allergyName));
        }
        return allergySet;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String medicine} into a {@code Medicine}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code medicine} is invalid.
     */
    public static Medicine parseMedicine(String medicine) throws ParseException {
        requireNonNull(medicine);
        String trimmedMedicine = medicine.trim();
        if (!Medicine.isValidMedicineName(trimmedMedicine)) {
            throw new ParseException(Medicine.MESSAGE_CONSTRAINTS);
        }
        return new Medicine(trimmedMedicine);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Medicine> parseMedicines(Collection<String> medicines) throws ParseException {
        requireNonNull(medicines);
        final Set<Medicine> medicineSet = new HashSet<>();
        for (String medicineName : medicines) {
            medicineSet.add(parseMedicine(medicineName));
        }
        return medicineSet;
    }

    /**
     * Parses a {@code String bloodType} into a {@code BloodType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bloodType} is invalid.
     */
    public static BloodType parseBloodType(String bloodType) throws ParseException {
        requireNonNull(bloodType);
        String trimmedBloodType = bloodType.trim();
        if (!BloodType.isValidBloodType(trimmedBloodType)) {
            throw new ParseException(BloodType.MESSAGE_CONSTRAINTS);
        }
        return new BloodType(trimmedBloodType);
    }

    /**
     * Parses a {@code String alcoholicRecord} into a {@code AlcoholicRecord}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code alcoholicRecord} is invalid.
     */
    public static AlcoholicRecord parseAlcoholicRecord(String alcoholicRecord) throws ParseException {
        requireNonNull(alcoholicRecord);
        String trimmedAlcoholicRecord = alcoholicRecord.trim();
        if (!AlcoholicRecord.isValidAlcoholicRecord(trimmedAlcoholicRecord)) {
            throw new ParseException(AlcoholicRecord.MESSAGE_CONSTRAINTS);
        }
        return new AlcoholicRecord(trimmedAlcoholicRecord);
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String smokingRecord} into a {@code SmokingRecord}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code smokingRecord} is invalid.
     */
    public static SmokingRecord parseSmokingRecord(String smokingRecord) throws ParseException {
        requireNonNull(smokingRecord);
        String trimmedRecord = smokingRecord.trim();
        if (!SmokingRecord.isValidSmokingRecord(trimmedRecord)) {
            throw new ParseException(SmokingRecord.MESSAGE_CONSTRAINTS);
        }
        return new SmokingRecord(trimmedRecord);
    }

    /**
     * Parses a {@code String pastMedicalHistory} into a {@code PastMedicalHistory}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code pastMedicalHistory} is invalid.
     */
    public static PastMedicalHistory parsePastMedicalHistory(String pastMedicalHistory) throws ParseException {
        requireNonNull(pastMedicalHistory);
        String trimmedPastMedicalHistory = pastMedicalHistory.trim();
        return new PastMedicalHistory(trimmedPastMedicalHistory);
    }

    /**
     * Parses a {@code String appointmentTime} into a {@code AppointmentTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code appointmentTime} is invalid.
     */
    public static AppointmentTime parseAppointmentTime(String appointmentTime) throws ParseException {
        requireNonNull(appointmentTime);
        String trimmedAppointmentTime = appointmentTime.trim();
        if (!AppointmentTime.isValidDateTime(trimmedAppointmentTime)) {
            throw new ParseException(AppointmentTime.MESSAGE_FORMAT_CONSTRAINTS);
        }
        return new AppointmentTime(trimmedAppointmentTime);
    }

    /**
     * Parses a {@code String appointmentNotes} into a {@code AppointmentNotes}.
     * Null string will be parsed as an empty string.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static AppointmentNotes parseAppointmentNotes(String appointmentNotes) throws ParseException {
        appointmentNotes = appointmentNotes == null ? "" : appointmentNotes;
        String trimmedAppointmentNotes = appointmentNotes.trim();
        return new AppointmentNotes(trimmedAppointmentNotes);
    }
}
