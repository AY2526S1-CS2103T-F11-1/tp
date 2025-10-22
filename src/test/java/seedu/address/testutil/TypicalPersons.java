package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALCOHOLIC_RECORD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALCOHOLIC_RECORD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOOD_TYPE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OF_BIRTH_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OF_BIRTH_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICINE_ANTIDEPRESSANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SMOKING_RECORD_NO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SMOKING_RECORD_YES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withIdentityNumber("AP67")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withTags("friends").withDateOfBirth("20-05-1998").withBloodType("O")
            .withAlcoholicRecord("Social drinker").withGender("F").withSmokingRecord("yes")
            .withMedicines("antidepressants").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withIdentityNumber("BM67")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432")
            .withDateOfBirth("01-01-2000").withTags("owesMoney", "friends").withBloodType("O")
            .withAlcoholicRecord("Occasional").withGender("M").withSmokingRecord("no")
            .withMedicines("antidepressants").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withIdentityNumber("CK67")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
            .withDateOfBirth("20-12-1998").withBloodType("O").withAlcoholicRecord("Never").withGender("M")
            .withSmokingRecord("yes")
            .withMedicines("antidepressants").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withIdentityNumber("DM67")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withDateOfBirth("30-05-1995").withBloodType("O").withAlcoholicRecord("Yes").withGender("M")
            .withSmokingRecord("yes")
            .withMedicines("antidepressants").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withIdentityNumber("EM67")
            .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave")
            .withDateOfBirth("20-11-1999").withBloodType("O").withAlcoholicRecord("Social drinker").withGender("M")
            .withMedicines("antidepressants").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withIdentityNumber("FK67")
            .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo")
            .withDateOfBirth("15-08-1997").withBloodType("O").withAlcoholicRecord("Never").withGender("M")
            .withSmokingRecord("no")
            .withMedicines("antidepressants").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withIdentityNumber("GB67")
            .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street").withDateOfBirth("20-05-2000")
            .withTags("owesMoney", "friends").withAlcoholicRecord("Never").withTags("owesMoney", "friends")
            .withGender("M").withSmokingRecord("yes")
            .withMedicines("antidepressants").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withIdentityNumber("HOOO3523").withEmail("stefan@example.com").withAddress("little india")
            .withBloodType("O").withAlcoholicRecord("Social drinker").withGender("M").withSmokingRecord("no")
            .withMedicines("antidepressants").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withIdentityNumber("I6244").withEmail("hans@example.com").withAddress("chicago ave").withBloodType("O")
            .withAlcoholicRecord("Never").withGender("M").withSmokingRecord("yes")
            .withMedicines("antidepressants").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withIdentityNumber(VALID_ID_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).withDateOfBirth(VALID_DATE_OF_BIRTH_AMY).withBloodType(VALID_BLOOD_TYPE)
            .withAlcoholicRecord(VALID_ALCOHOLIC_RECORD_AMY).withGender(VALID_GENDER_AMY)
            .withSmokingRecord(VALID_SMOKING_RECORD_NO)
            .withMedicines(VALID_MEDICINE_ANTIDEPRESSANT).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withIdentityNumber(VALID_ID_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withDateOfBirth(VALID_DATE_OF_BIRTH_BOB)
            .withBloodType(VALID_BLOOD_TYPE).withAlcoholicRecord(VALID_ALCOHOLIC_RECORD_BOB)
            .withGender(VALID_GENDER_BOB).withSmokingRecord(VALID_SMOKING_RECORD_YES)
            .withMedicines(VALID_MEDICINE_ANTIDEPRESSANT).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
