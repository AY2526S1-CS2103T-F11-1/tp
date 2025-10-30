package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SmokingRecord;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final List<Prefix> ADD_COMMAND_PREFIXES = List.of(
            PREFIX_NAME, PREFIX_IDENTITY_NUMBER, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
            PREFIX_EMERGENCY_CONTACT, PREFIX_TAG, PREFIX_DATE_OF_BIRTH, PREFIX_BLOOD_TYPE, PREFIX_ALCOHOLIC_RECORD,
            PREFIX_GENDER, PREFIX_SMOKING_RECORD, PREFIX_ALLERGY, PREFIX_PAST_MEDICAL_HISTORY, PREFIX_MEDICINE
    );

    private static final Pattern PREFIX_FINDER_PATTERN = Pattern.compile("\\s+(\\w+\\\\)");

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Set<String> unrecognizedPrefixes = findUnrecognizedPrefixes(args);
        if (!unrecognizedPrefixes.isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_PARAMETERS,
                    AddCommand.COMMAND_WORD,
                    String.join(", ", unrecognizedPrefixes)));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                ADD_COMMAND_PREFIXES.toArray(new Prefix[0])
        );

        if (!arePrefixesPresent(argMultimap,
                PREFIX_NAME, PREFIX_IDENTITY_NUMBER, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMERGENCY_CONTACT, PREFIX_DATE_OF_BIRTH, PREFIX_BLOOD_TYPE, PREFIX_GENDER
            ) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_IDENTITY_NUMBER, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_EMERGENCY_CONTACT, PREFIX_DATE_OF_BIRTH, PREFIX_BLOOD_TYPE,
                PREFIX_ALCOHOLIC_RECORD, PREFIX_GENDER, PREFIX_SMOKING_RECORD, PREFIX_PAST_MEDICAL_HISTORY);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        IdentityNumber identityNumber = ParserUtil.parseIdentityNumber(
                argMultimap.getValue(PREFIX_IDENTITY_NUMBER).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        EmergencyContact emergencyContact = ParserUtil
                .parseEmergencyContact(argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Allergy> allergyList = ParserUtil.parseAllergies(argMultimap.getAllValues(PREFIX_ALLERGY));
        Set<Medicine> medicineList = ParserUtil.parseMedicines(argMultimap.getAllValues(PREFIX_MEDICINE));
        DateOfBirth dateOfBirth = ParserUtil.parseDateOfBirth(argMultimap.getValue(PREFIX_DATE_OF_BIRTH).get());
        BloodType bloodType = ParserUtil.parseBloodType(argMultimap.getValue(PREFIX_BLOOD_TYPE).get());
        AlcoholicRecord alcoholicRecord = ParserUtil.parseAlcoholicRecord(
                argMultimap.getValue(PREFIX_ALCOHOLIC_RECORD).orElse("None"));
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        SmokingRecord smokingRecord = ParserUtil.parseSmokingRecord(
                argMultimap.getValue(PREFIX_SMOKING_RECORD).orElse("None"));
        PastMedicalHistory pastMedicalHistory = ParserUtil.parsePastMedicalHistory(
                argMultimap.getValue(PREFIX_PAST_MEDICAL_HISTORY).orElse("None")
        );

        Person person = new Person(name, identityNumber, phone, email, address, emergencyContact, tagList, dateOfBirth,
                bloodType, alcoholicRecord, gender, smokingRecord, allergyList, pastMedicalHistory, medicineList);

        return new AddCommand(person);
    }

    /**
     * Finds prefixes in the args string that are not present in the ADD_COMMAND_PREFIXES list.
     *
     * @param args The raw arguments string.
     * @return A Set of unrecognized prefix strings found in args.
     */
    private Set<String> findUnrecognizedPrefixes(String args) {
        Set<String> knownPrefixStrings = ADD_COMMAND_PREFIXES.stream()
                .map(Prefix::getPrefix)
                .collect(Collectors.toSet());
        Set<String> unrecognized = new HashSet<>();
        Matcher matcher = PREFIX_FINDER_PATTERN.matcher(" " + args);

        while (matcher.find()) {
            String potentialPrefix = matcher.group(1);
            if (!knownPrefixStrings.contains(potentialPrefix)) {
                unrecognized.add(potentialPrefix);
            }
        }
        return unrecognized;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
