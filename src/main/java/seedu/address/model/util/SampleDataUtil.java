package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
import seedu.address.model.person.PastDiagnoses;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SmokingRecord;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new IdentityNumber("ALY123"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                new EmergencyContact("[Mother] +65 28937632"),
                getTagSet("friends"), new DateOfBirth("01-01-1998"), new BloodType("AB"),
                new AlcoholicRecord("Social drinker"), new Gender("M"), new SmokingRecord("Heavy smoker"),
                getAllergySet("nuts"), new PastDiagnoses("Diabetes"), getMedicineSet("antidepressents")),
            new Person(new Name("Bernice Yu"), new IdentityNumber("BY6767"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new EmergencyContact("[Mother] +60 1296739024"),
                getTagSet("colleagues", "friends"), new DateOfBirth("02-02-1999"), new BloodType("AB"),
                new AlcoholicRecord("Occasional"), new Gender("F"), new SmokingRecord("Non-smoker"),
                getAllergySet("nuts"), new PastDiagnoses(""), getMedicineSet("antidepressents")),
            new Person(new Name("Charlotte Oliveiro"), new IdentityNumber("CO6969"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new EmergencyContact("[Father] 47592832"),
                getTagSet("neighbours"), new DateOfBirth("03-03-2000"), new BloodType("AB"),
                new AlcoholicRecord("Never"), new Gender("F"), new SmokingRecord("Non-smoker"),
                getAllergySet("nuts"), new PastDiagnoses("Hypertension"), getMedicineSet("antidepressents")),
            new Person(new Name("David Li"), new IdentityNumber("DL6767"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new EmergencyContact("[Someone] +5521351231"),
                getTagSet("family"), new DateOfBirth("04-04-2001"), new BloodType("AB"),
                new AlcoholicRecord("Yes"), new Gender("M"), new SmokingRecord("Quitter"),
                getAllergySet("nuts"), new PastDiagnoses("Asthma"), getMedicineSet("antidepressents")),
            new Person(new Name("Irfan Ibrahim"), new IdentityNumber("II6767"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                new EmergencyContact("[Another person] +429-482-3834"),
                getTagSet("classmates"), new DateOfBirth("05-05-2002"), new BloodType("AB"),
                new AlcoholicRecord("Former drinker"), new Gender("M"), new SmokingRecord("Occasional smoker"),
                getAllergySet("nuts"), new PastDiagnoses(""), getMedicineSet("antidepressents")),
            new Person(new Name("Roy Balakrishnan"), new IdentityNumber("RB6767"),
                new Phone("92624417"), new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                new EmergencyContact("[Another person] +429-482-3834"),
                getTagSet("colleagues"), new DateOfBirth("06-06-2003"), new BloodType("AB"),
                new AlcoholicRecord("Yes"), new Gender("M"), new SmokingRecord("Non-smoker"),
                getAllergySet("nuts"), new PastDiagnoses(""), getMedicineSet("antidepressents"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a allergy set containing the list of strings given.
     */
    public static Set<Allergy> getAllergySet(String... strings) {
        return Arrays.stream(strings)
                .map(Allergy::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a medicine set containing the list of strings given.
     */
    public static Set<Medicine> getMedicineSet(String... strings) {
        return Arrays.stream(strings)
                .map(Medicine::new)
                .collect(Collectors.toSet());
    }

}
