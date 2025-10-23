package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.AlcoholicRecord;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.PastDiagnoses;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SmokingRecord;

/**
 * Test class for JsonAdaptedPerson.
 */
public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "@651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DOB = "32-13-2020";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ALCOHOLIC_RECORD = " ";
    private static final String INVALID_BLOOD_TYPE = " ";
    private static final String INVALID_GENDER = " ";
    private static final String INVALID_SMOKING_RECORD = " ";
    private static final String INVALID_PAST_DIAGNOSES = " ";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_IDENTITY_NUMBER = BENSON.getIdentityNumber().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_EMERGENCY_CONTACT = BENSON.getEmergencyContact().toString();
    private static final String VALID_DOB = BENSON.getDateOfBirth().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAllergy> VALID_ALLERGIES = BENSON.getAllergies().stream()
            .map(JsonAdaptedAllergy::new)
            .collect(Collectors.toList());
    private static final String VALID_BLOOD_TYPE = "AB";
    private static final String VALID_ALCOHOLIC_RECORD = BENSON.getAlcoholicRecord().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_SMOKING_RECORD = BENSON.getSmokingRecord().toString();
    private static final String VALID_PAST_DIAGNOSES = BENSON.getPastDiagnoses().toString();
    private static final List<JsonAdaptedMedicine> VALID_MEDICINES = BENSON.getMedicines().stream()
            .map(JsonAdaptedMedicine::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_validAlcoholicRecordVariations_returnsAlcoholicRecord() throws Exception {
        String[] validAlcoholicRecord = {"Social drinker", "Occasional", "Never"};
        for (String record : validAlcoholicRecord) {
            JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE,
                    VALID_EMAIL, VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE,
                    record, VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES,
                    VALID_MEDICINES);
            AlcoholicRecord expected = new AlcoholicRecord(record);
            assertEquals(expected, person.toModelType().getAlcoholicRecord());
        }
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD,
                VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD,
                VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD,
                VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD,
                VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD,
                VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, null,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD,
                VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE,
                VALID_ALCOHOLIC_RECORD, VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES,
                VALID_MEDICINES);

        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL,
                        null, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE,
                        VALID_ALCOHOLIC_RECORD, VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES,
                        VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, INVALID_DOB, VALID_BLOOD_TYPE,
                        VALID_ALCOHOLIC_RECORD, VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES,
                        VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = DateOfBirth.MESSAGE_FORMAT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_EMERGENCY_CONTACT, VALID_TAGS, null, VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD,
                        VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES,
                        VALID_MEDICINES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_futureDateOfBirth_throwsIllegalValueException() {
        LocalDate future = LocalDate.now().plusDays(1);
        String futureDateString = future.format(DateOfBirth.DATE_FORMATTER);
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_EMERGENCY_CONTACT, VALID_TAGS, futureDateString, VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD,
                        VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = DateOfBirth.MESSAGE_PAST_DATE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAlcoholicRecord_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE, INVALID_ALCOHOLIC_RECORD,
                        VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES,
                        VALID_MEDICINES);

        String expectedMessage = AlcoholicRecord.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAlcoholicRecord_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE, null,
                VALID_GENDER,
                VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AlcoholicRecord.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullBloodType_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, null, VALID_ALCOHOLIC_RECORD,
                VALID_GENDER,
                VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE,
                VALID_ALCOHOLIC_RECORD, null, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES,
                VALID_MEDICINES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSmokingRecord_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD,
                        VALID_GENDER, INVALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES, VALID_MEDICINES);
        String expectedMessage = SmokingRecord.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSmokingRecord_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB, VALID_ALCOHOLIC_RECORD,
                        VALID_GENDER, VALID_BLOOD_TYPE, null, VALID_ALLERGIES, VALID_PAST_DIAGNOSES,
                        VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, SmokingRecord.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPastDiagnoses_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER,
                        VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB,
                        VALID_BLOOD_TYPE,
                        VALID_ALCOHOLIC_RECORD, VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES,
                        INVALID_PAST_DIAGNOSES, VALID_MEDICINES);
        String expectedMessage = PastDiagnoses.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPastDiagnoses_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER,
                        VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB,
                        VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD, VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES,
                        null, VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PastDiagnoses.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_longPastDiagnoses_triggersFinePreview() {
        Logger logger = LogsCenter.getLogger(JsonAdaptedPerson.class);
        Level old = logger.getLevel();
        try {
            logger.setLevel(Level.FINE);
            // Build a JsonAdaptedPerson with valid fields and a long pastDiagnoses string (over 80 chars)
            JsonAdaptedPerson person =
                    new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER,
                            VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_TAGS, VALID_DOB,
                            VALID_BLOOD_TYPE, VALID_ALCOHOLIC_RECORD, VALID_GENDER, VALID_SMOKING_RECORD,
                            VALID_ALLERGIES, "DiagnosisA, DiagnosisB, DiagnosisC, DiagnosisD, DiagnosisE, DiagnosisF,"
                            + " DiagnosisG", VALID_MEDICINES);
            // We only need to ensure toModelType runs (and exercises logger.fine). The toModelType method throws
            // IllegalValueException on invalid input, but here the input is valid.
            assertDoesNotThrow(() -> person.toModelType());
        } finally {
            logger.setLevel(old);
        }
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_IDENTITY_NUMBER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_EMERGENCY_CONTACT, invalidTags, VALID_DOB, VALID_BLOOD_TYPE,
                VALID_ALCOHOLIC_RECORD, VALID_GENDER, VALID_SMOKING_RECORD, VALID_ALLERGIES, VALID_PAST_DIAGNOSES,
                VALID_MEDICINES);

        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
