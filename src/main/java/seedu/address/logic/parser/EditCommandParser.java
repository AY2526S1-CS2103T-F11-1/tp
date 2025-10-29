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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Allergy;
import seedu.address.model.person.Medicine;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_IDENTITY_NUMBER,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EMERGENCY_CONTACT, PREFIX_TAG, PREFIX_DATE_OF_BIRTH,
                PREFIX_SMOKING_RECORD, PREFIX_BLOOD_TYPE, PREFIX_GENDER, PREFIX_ALLERGY, PREFIX_PAST_MEDICAL_HISTORY,
                PREFIX_MEDICINE, PREFIX_ALCOHOLIC_RECORD);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_IDENTITY_NUMBER, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_EMERGENCY_CONTACT, PREFIX_DATE_OF_BIRTH, PREFIX_SMOKING_RECORD, PREFIX_GENDER,
                PREFIX_ALCOHOLIC_RECORD, PREFIX_BLOOD_TYPE, PREFIX_PAST_MEDICAL_HISTORY);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_IDENTITY_NUMBER).isPresent()) {
            editPersonDescriptor.setIdentityNumber(ParserUtil.parseIdentityNumber(argMultimap
                    .getValue(PREFIX_IDENTITY_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).isPresent()) {
            editPersonDescriptor.setEmergencyContact(ParserUtil.parseEmergencyContact(
                    argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE_OF_BIRTH).isPresent()) {
            editPersonDescriptor.setDateOfBirth(ParserUtil.parseDateOfBirth(
                    argMultimap.getValue(PREFIX_DATE_OF_BIRTH).get()));
        }
        if (argMultimap.getValue(PREFIX_SMOKING_RECORD).isPresent()) {
            editPersonDescriptor.setSmokingRecord(ParserUtil.parseSmokingRecord(
                    argMultimap.getValue(PREFIX_SMOKING_RECORD).get()));
        }
        if (argMultimap.getValue(PREFIX_BLOOD_TYPE).isPresent()) {
            editPersonDescriptor.setBloodType(ParserUtil.parseBloodType(
                    argMultimap.getValue(PREFIX_BLOOD_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(ParserUtil.parseGender(
                    argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_PAST_MEDICAL_HISTORY).isPresent()) {
            editPersonDescriptor.setPastMedicalHistory(ParserUtil.parsePastMedicalHistory(
                    argMultimap.getValue(PREFIX_PAST_MEDICAL_HISTORY).get()));
        }
        if (argMultimap.getValue(PREFIX_ALCOHOLIC_RECORD).isPresent()) {
            editPersonDescriptor.setAlcoholicRecord(ParserUtil.parseAlcoholicRecord(
                    argMultimap.getValue(PREFIX_ALCOHOLIC_RECORD).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parseAllergiesForEdit(argMultimap.getAllValues(PREFIX_ALLERGY)).ifPresent(editPersonDescriptor::setAllergies);
        parseMedicinesForEdit(argMultimap.getAllValues(PREFIX_MEDICINE)).ifPresent(editPersonDescriptor::setMedicines);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> allergies} into a {@code Set<Allergy>} if
     * {@code allergies} is non-empty.
     * If {@code allergies} contain only one element which is an empty string, it will be
     * parsed into a
     * {@code Set<Allergy>} containing zero allergies.
     */
    private Optional<Set<Allergy>> parseAllergiesForEdit(Collection<String> allergies) throws ParseException {
        assert allergies != null;

        if (allergies.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> allergySet =
                allergies.size() == 1 && allergies.contains("") ? Collections.emptySet() : allergies;
        return Optional.of(ParserUtil.parseAllergies(allergySet));
    }

    /**
     * Parses {@code Collection<String> medicines} into a {@code Set<Medicine>} if
     * {@code medicines} is non-empty.
     * If {@code medicines} contain only one element which is an empty string, it will be
     * parsed into a
     * {@code Set<Medicine>} containing zero medicines.
     */
    private Optional<Set<Medicine>> parseMedicinesForEdit(Collection<String> medicines) throws ParseException {
        assert medicines != null;

        if (medicines.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> medicineSet =
                medicines.size() == 1 && medicines.contains("") ? Collections.emptySet() : medicines;
        return Optional.of(ParserUtil.parseMedicines(medicineSet));
    }

}
