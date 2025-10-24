package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentNotes;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.IdentityNumber;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/*
 * Tests for AddAppointmentCommand.
 * Minimal model stubs are provided inside tests to avoid changing production Model classes.
 */
public class AddAppointmentCommandTest {

    @Test
    public void equals_sameValues_returnsTrue() {
        AddAppointmentCommand cmd1 = new AddAppointmentCommand(Index.fromOneBased(1),
                new AppointmentTime("01-01-2025 10:00"), new AppointmentNotes("note"));
        AddAppointmentCommand cmd2 = new AddAppointmentCommand(Index.fromOneBased(1),
                new AppointmentTime("01-01-2025 10:00"), new AppointmentNotes("note"));
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void toString_containsFields() {
        AddAppointmentCommand cmd = new AddAppointmentCommand(Index.fromOneBased(2),
                new AppointmentTime("01-01-2025 10:00"), new AppointmentNotes("n"));
        String s = cmd.toString();
        assertTrue(s.contains("index"));
        assertTrue(s.contains("appointmentTime"));
        assertTrue(s.contains("appointmentNotes"));
    }

    @Test
    public void execute_addSuccessful_appointmentAdded() throws Exception {
        Person p = new PersonBuilder().withIdentityNumber("ID1").build();
        ModelStubWithPersonList model = new ModelStubWithPersonList(Collections.singletonList(p));
        AppointmentTime time = new AppointmentTime("01-01-2025 10:00");
        AppointmentNotes notes = new AppointmentNotes("checkup");
        AddAppointmentCommand cmd = new AddAppointmentCommand(Index.fromOneBased(1), time, notes);

        CommandResult result = cmd.execute(model);
        // message contains formatted appointment; only check that appointment was added to the model
        List<Appointment> added = model.addedAppointments;
        assertEquals(1, added.size());
        assertEquals(new Appointment(notes, time, new IdentityNumber("ID1")), added.get(0));
        assertTrue(result.getFeedbackToUser().contains("New appointment added"));
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Person p = new PersonBuilder().withIdentityNumber("ID1").build();
        AppointmentTime time = new AppointmentTime("01-01-2025 10:00");
        AppointmentNotes notes = new AppointmentNotes("checkup");
        Appointment existing = new Appointment(notes, time, new IdentityNumber("ID1"));
        Model model = new ModelStubWithExistingAppointment(existing, Collections.singletonList(p));
        AddAppointmentCommand cmd = new AddAppointmentCommand(Index.fromOneBased(1), time, notes);

        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Person p = new PersonBuilder().withIdentityNumber("ID1").build();
        Model model = new ModelStubWithPersonList(Collections.singletonList(p));
        AppointmentTime time = new AppointmentTime("01-01-2025 10:00");
        AppointmentNotes notes = new AppointmentNotes("checkup");
        AddAppointmentCommand cmd = new AddAppointmentCommand(Index.fromOneBased(2), time, notes);

        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a list of persons and records added appointments.
     */
    private class ModelStubWithPersonList extends ModelStub {
        final List<Person> persons;
        final ArrayList<Appointment> addedAppointments = new ArrayList<>();

        ModelStubWithPersonList(List<Person> persons) {
            requireNonNull(persons);
            this.persons = new ArrayList<>(persons);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(persons));
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return addedAppointments.stream().anyMatch(a -> a.isSameAppointment(appointment));
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            addedAppointments.add(appointment);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that contains a single existing appointment (used to simulate duplicates).
     */
    private class ModelStubWithExistingAppointment extends ModelStubWithPersonList {
        private final Appointment existing;

        ModelStubWithExistingAppointment(Appointment existing, List<Person> persons) {
            super(persons);
            requireNonNull(existing);
            this.existing = existing;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return existing.isSameAppointment(appointment) || super.hasAppointment(appointment);
        }
    }

}
