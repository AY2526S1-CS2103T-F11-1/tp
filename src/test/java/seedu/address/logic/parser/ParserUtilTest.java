package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentNotes;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_PHONE = "$4";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DATE_OF_BIRTH = "32-02-2000";
    private static final String INVALID_TAG = " ";

    // New invalid appointment time (bad day)
    private static final String INVALID_APPOINTMENT_TIME = "32-01-2020 1000";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_DATE_OF_BIRTH = "01-01-1990";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_APPOINTMENT_TIME = "01-01-2020 10:00";
    private static final String VALID_APPOINTMENT_NOTES = "Bring reports";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseDateOfBirth_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateOfBirth((String) null));
    }

    @Test
    public void parseDateOfBirth_invalidValueFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateOfBirth(INVALID_DATE_OF_BIRTH));
    }

    @Test
    public void parseDateOfBirth_futureDate_throwsParseException() {
        LocalDate future = LocalDate.now().plusDays(1);
        String futureDateString = future.format(DateOfBirth.DATE_FORMATTER);
        assertThrows(ParseException.class, () -> ParserUtil.parseDateOfBirth(futureDateString));
    }

    @Test
    public void parseDateOfBirth_leapDay_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateOfBirth("31-02-2025"));
    }

    @Test
    public void parseDateOfBirth_validValueWithoutWhitespace_returnsDateOfBirth() throws Exception {
        DateOfBirth expectedDateOfBirth = new DateOfBirth(VALID_DATE_OF_BIRTH);
        assertEquals(expectedDateOfBirth, ParserUtil.parseDateOfBirth(VALID_DATE_OF_BIRTH));
    }

    @Test
    public void parseDateOfBirth_validValueWithWhitespace_returnsTrimmedDateOfBirth() throws Exception {
        String dateOfBirthWithWhitespace = WHITESPACE + VALID_DATE_OF_BIRTH + WHITESPACE;
        DateOfBirth expectedDateOfBirth = new DateOfBirth(VALID_DATE_OF_BIRTH);
        assertEquals(expectedDateOfBirth, ParserUtil.parseDateOfBirth(dateOfBirthWithWhitespace));
    }

    // AppointmentTime tests
    @Test
    public void parseAppointmentTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppointmentTime((String) null));
    }

    @Test
    public void parseAppointmentTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentTime(INVALID_APPOINTMENT_TIME));
    }

    @Test
    public void parseAppointmentTime_leapDay_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentTime("29-02-2025 10:00"));
    }

    @Test
    public void parseAppointmentTime_validValueWithoutWhitespace_returnsAppointmentTime() throws Exception {
        AppointmentTime expected = new AppointmentTime(VALID_APPOINTMENT_TIME);
        assertEquals(expected, ParserUtil.parseAppointmentTime(VALID_APPOINTMENT_TIME));
    }

    @Test
    public void parseAppointmentTime_validValueWithWhitespace_returnsTrimmedAppointmentTime() throws Exception {
        String timeWithWhitespace = WHITESPACE + VALID_APPOINTMENT_TIME + WHITESPACE;
        AppointmentTime expected = new AppointmentTime(VALID_APPOINTMENT_TIME);
        assertEquals(expected, ParserUtil.parseAppointmentTime(timeWithWhitespace));
    }

    // AppointmentNotes tests
    @Test
    public void parseAppointmentNotes_null_returnsEmptyAppointmentNotes() throws Exception {
        AppointmentNotes expected = new AppointmentNotes("");
        assertEquals(expected, ParserUtil.parseAppointmentNotes(null));
    }

    @Test
    public void parseAppointmentNotes_validValueWithoutWhitespace_returnsAppointmentNotes() throws Exception {
        AppointmentNotes expected = new AppointmentNotes(VALID_APPOINTMENT_NOTES);
        assertEquals(expected, ParserUtil.parseAppointmentNotes(VALID_APPOINTMENT_NOTES));
    }

    @Test
    public void parseAppointmentNotes_validValueWithWhitespace_returnsTrimmedAppointmentNotes() throws Exception {
        String notesWithWhitespace = WHITESPACE + VALID_APPOINTMENT_NOTES + WHITESPACE;
        AppointmentNotes expected = new AppointmentNotes(VALID_APPOINTMENT_NOTES);
        assertEquals(expected, ParserUtil.parseAppointmentNotes(notesWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parsePastMedicalHistory_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePastMedicalHistory((String) null));
    }

    @Test
    public void parsePastMedicalHistory_validValueWithoutWhitespace_returnsPastMedicalHistory() throws Exception {
        String validPastMedicalHistory = "Diabetes, Hypertension";
        assertEquals(validPastMedicalHistory, ParserUtil.parsePastMedicalHistory(validPastMedicalHistory).value);
    }

    @Test
    public void parsePastMedicalHistory_validValueWithWhitespace_returnsTrimmedPastMedicalHistory() throws Exception {
        String pastMedicalHistoryWithWhitespace = WHITESPACE + "Diabetes, Hypertension" + WHITESPACE;
        String expectedPastMedicalHistory = "Diabetes, Hypertension";
        assertEquals(expectedPastMedicalHistory,
                ParserUtil.parsePastMedicalHistory(pastMedicalHistoryWithWhitespace).value);
    }

    @Test
    public void parsePastMedicalHistory_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePastMedicalHistory(""));
    }

    @Test
    public void parsePastMedicalHistory_longValid_triggersFinePreview() throws Exception {
        Logger logger = LogsCenter.getLogger(ParserUtil.class);
        Level old = logger.getLevel();
        try {
            logger.setLevel(Level.FINE); // ensure fine logs are enabled to run the fine branch
            String longMedicalHistory = "Diabetes, Hypertension, Asthma, Chronic Obstructive Pulmonary Disease, "
                    + "SomeOtherMedicalHistory, AnotherOne, Extra"; // > 80 chars
            // should not throw; simply calling it executes the logger.fine preview branch
            ParserUtil.parsePastMedicalHistory(longMedicalHistory);
        } finally {
            logger.setLevel(old);
        }
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
