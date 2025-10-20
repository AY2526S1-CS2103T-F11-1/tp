package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ALCOHOLIC_RECORD_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ALCOHOLIC_RECORD_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ALLERGY_DESC_NUTS;
import static seedu.address.logic.commands.CommandTestUtil.BLOOD_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DATE_OF_BIRTH_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_OF_BIRTH_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.IDENTITY_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IDENTITY_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ALCOHOLIC_RECORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BLOOD_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_OF_BIRTH_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_IDENTITY_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SMOKING_RECORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SMOKING_RECORD_DESC_NO;
import static seedu.address.logic.commands.CommandTestUtil.SMOKING_RECORD_DESC_YES;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALCOHOLIC_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IDENTITY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SMOKING_RECORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.AlcoholicRecord;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.IdentityNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SmokingRecord;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + DATE_OF_BIRTH_DESC_BOB
                + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES
                + ALLERGY_DESC_NUTS,
                new AddCommand(expectedPerson));


        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BLOOD_TYPE_DESC
                + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES + ALLERGY_DESC_NUTS,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_FRIEND + BLOOD_TYPE_DESC
                + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple date of birth
        assertParseFailure(parser, DATE_OF_BIRTH_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE_OF_BIRTH));

        // multiple alcoholic record
        assertParseFailure(parser, ALCOHOLIC_RECORD_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ALCOHOLIC_RECORD));

        // multiple blood type
        assertParseFailure(parser, BLOOD_TYPE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BLOOD_TYPE));

        // multiple genders
        assertParseFailure(parser, GENDER_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + IDENTITY_NUMBER_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + NAME_DESC_AMY + ADDRESS_DESC_AMY + DATE_OF_BIRTH_DESC_AMY + BLOOD_TYPE_DESC
                        + ALCOHOLIC_RECORD_DESC_AMY + GENDER_DESC_AMY + SMOKING_RECORD_DESC_NO
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_IDENTITY_NUMBER, PREFIX_ADDRESS,
                        PREFIX_EMAIL, PREFIX_PHONE, PREFIX_DATE_OF_BIRTH, PREFIX_BLOOD_TYPE, PREFIX_ALCOHOLIC_RECORD,
                        PREFIX_GENDER, PREFIX_SMOKING_RECORD));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid date of birth
        assertParseFailure(parser, INVALID_DATE_OF_BIRTH_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE_OF_BIRTH));

        // valid value followed by invalid value
        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedPersonString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid date of birth
        assertParseFailure(parser, validExpectedPersonString + INVALID_DATE_OF_BIRTH_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE_OF_BIRTH));

        // invalid alcoholic record
        assertParseFailure(parser, validExpectedPersonString + INVALID_ALCOHOLIC_RECORD_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ALCOHOLIC_RECORD));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().withAllergies().build();
        assertParseSuccess(parser, NAME_DESC_AMY + IDENTITY_NUMBER_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + DATE_OF_BIRTH_DESC_AMY + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_AMY
                + GENDER_DESC_AMY + SMOKING_RECORD_DESC_NO, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES
                + ALLERGY_DESC_NUTS,
                Name.MESSAGE_CONSTRAINTS);

        // invalid identity number
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_IDENTITY_NUMBER_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES
                + ALLERGY_DESC_NUTS,
                IdentityNumber.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES
                + ALLERGY_DESC_NUTS,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES
                + ALLERGY_DESC_NUTS,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + INVALID_ADDRESS_DESC + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB
                + SMOKING_RECORD_DESC_YES + ALLERGY_DESC_NUTS, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + INVALID_TAG_DESC + TAG_DESC_FRIEND
                + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES
                + ALLERGY_DESC_NUTS,
                Email.MESSAGE_CONSTRAINTS);

        // invalid date of birth
        assertParseFailure(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + INVALID_DATE_OF_BIRTH_DESC + INVALID_TAG_DESC
                + TAG_DESC_FRIEND + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB
                + SMOKING_RECORD_DESC_YES + ALLERGY_DESC_NUTS, Email.MESSAGE_CONSTRAINTS);

        // invalid alcoholic record
        assertParseFailure(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BLOOD_TYPE_DESC
                + GENDER_DESC_BOB + INVALID_ALCOHOLIC_RECORD_DESC + SMOKING_RECORD_DESC_YES + ALLERGY_DESC_NUTS,
                AlcoholicRecord.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BLOOD_TYPE_DESC
                + ALCOHOLIC_RECORD_DESC_BOB + INVALID_GENDER_DESC + SMOKING_RECORD_DESC_YES + ALLERGY_DESC_NUTS,
                Gender.MESSAGE_CONSTRAINTS);

        // invalid blood type
        assertParseFailure(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + INVALID_BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES
                + ALLERGY_DESC_NUTS,
                BloodType.MESSAGE_CONSTRAINTS);

        // invalid smoking record
        assertParseFailure(parser, NAME_DESC_BOB + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + BLOOD_TYPE_DESC + INVALID_SMOKING_RECORD_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB,
                SmokingRecord.MESSAGE_CONSTRAINTS);

        // two invalid values, only the first invalid value reported
        assertParseFailure(
                parser, INVALID_NAME_DESC + IDENTITY_NUMBER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + DATE_OF_BIRTH_DESC_BOB + BLOOD_TYPE_DESC + ALCOHOLIC_RECORD_DESC_BOB
                + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES + ALLERGY_DESC_NUTS, Name.MESSAGE_CONSTRAINTS
        );

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATE_OF_BIRTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BLOOD_TYPE_DESC
                + ALCOHOLIC_RECORD_DESC_BOB + GENDER_DESC_BOB + SMOKING_RECORD_DESC_YES + ALLERGY_DESC_NUTS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
