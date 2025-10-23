package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ID = "A60";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_EMERGENCY_CONTACT = "[mother] 123456789";
    public static final String DEFAULT_BLOOD_TYPE = "O";
    public static final String DEFAULT_DOB = "01-01-2000";
    public static final String DEFAULT_ALCOHOLIC_RECORD = "Social drinker";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_SMOKING_RECORD = "Non-smoker";
    public static final String DEFAULT_PAST_DIAGNOSES = "";

    private Name name;
    private IdentityNumber identityNumber;
    private Phone phone;
    private Email email;
    private Address address;
    private EmergencyContact emergencyContact;
    private Set<Tag> tags;
    private Set<Allergy> allergies;
    private Set<Medicine> medicines;
    private BloodType bloodType;
    private DateOfBirth dateOfBirth;
    private AlcoholicRecord alcoholicRecord;
    private Gender gender;
    private SmokingRecord smokingRecord;
    private PastDiagnoses pastDiagnoses;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        identityNumber = new IdentityNumber(DEFAULT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        emergencyContact = new EmergencyContact(DEFAULT_EMERGENCY_CONTACT);
        tags = new HashSet<>();
        allergies = new HashSet<>();
        bloodType = new BloodType(DEFAULT_BLOOD_TYPE);
        dateOfBirth = new DateOfBirth(DEFAULT_DOB);
        alcoholicRecord = new AlcoholicRecord(DEFAULT_ALCOHOLIC_RECORD);
        gender = new Gender(DEFAULT_GENDER);
        smokingRecord = new SmokingRecord(DEFAULT_SMOKING_RECORD);
        pastDiagnoses = new PastDiagnoses(DEFAULT_PAST_DIAGNOSES);
        medicines = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        identityNumber = personToCopy.getIdentityNumber();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        emergencyContact = personToCopy.getEmergencyContact();
        tags = new HashSet<>(personToCopy.getTags());
        allergies = new HashSet<>(personToCopy.getAllergies());
        bloodType = personToCopy.getBloodType();
        dateOfBirth = personToCopy.getDateOfBirth();
        alcoholicRecord = personToCopy.getAlcoholicRecord();
        gender = personToCopy.getGender();
        smokingRecord = personToCopy.getSmokingRecord();
        pastDiagnoses = personToCopy.getPastDiagnoses();
        medicines = new HashSet<>(personToCopy.getMedicines());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code identityNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withIdentityNumber(String identityNumber) {
        this.identityNumber = new IdentityNumber(identityNumber);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<Allergy>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withAllergies(String ... allergies) {
        this.allergies = SampleDataUtil.getAllergySet(allergies);
        return this;
    }

    /**
     * Parses the {@code medicines} into a {@code Set<Medicine>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withMedicines(String ... medicines) {
        this.medicines = SampleDataUtil.getMedicineSet(medicines);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmergencyContact(String emergencyContact) {
        this.emergencyContact = new EmergencyContact(emergencyContact);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code dateOfBirth} of the {@code Person} that we are building.
     */
    public PersonBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new DateOfBirth(dateOfBirth);
        return this;
    }

    /**
     * Sets the {@code bloodType} of the {@code Person} that we are building.
     */
    public PersonBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
        return this;
    }

    /**
     * Sets the {@code alcoholicRecord} of the {@code Person} that we are building.
     */
    public PersonBuilder withAlcoholicRecord(String alcoholicRecord) {
        this.alcoholicRecord = new AlcoholicRecord(alcoholicRecord);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code smokingRecord} of the {@code Person} that we are building.
     */
    public PersonBuilder withSmokingRecord(String smokingRecord) {
        this.smokingRecord = new SmokingRecord(smokingRecord);
        return this;
    }

    /**
     * Sets the {@code pastDiagnoses} of the {@code Person} that we are building.
     */
    public PersonBuilder withPastDiagnoses(String pastDiagnoses) {
        this.pastDiagnoses = new PastDiagnoses(pastDiagnoses);
        return this;
    }

    /**
     * Builds the {@code Person} with the specified parameters.
     */
    public Person build() {
        return new Person(name, identityNumber, phone, email, address, emergencyContact, tags, dateOfBirth, bloodType,
                alcoholicRecord, gender, smokingRecord, allergies, pastDiagnoses, medicines);
    }
}
