package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }

    /**
     * Adds the specified {@code Appointment} to the {@code AddressBook} being built.
     *
     * @param appointment The {@code Appointment} to be added. Must not be null.
     * @return The current {@code AddressBookBuilder} instance, allowing for method chaining.
     */
    public AddressBookBuilder withAppointment(Appointment appointment) {
        addressBook.addAppointment(appointment);
        return this;
    }
}
