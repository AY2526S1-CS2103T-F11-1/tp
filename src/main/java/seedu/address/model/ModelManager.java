package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.IdentityNumber;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Appointment> filteredAppointments;

    // Lists from the full AddressBook appointment list
    private final SortedList<Appointment> sortedAllUpcomingAppointments;
    private final SortedList<Appointment> sortedAllPastAppointments;

    // Lists to hold appointments for the person currently being viewed
    private final FilteredList<Appointment> viewedPersonAppointments;
    private final ObservableList<Appointment> viewedPersonUpcomingAppointments;
    private final ObservableList<Appointment> unmodifiableViewedPersonUpcomingAppointments;
    private final ObservableList<Appointment> viewedPersonPastAppointments;
    private final ObservableList<Appointment> unmodifiableViewedPersonPastAppointments;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredAppointments = new FilteredList<>(this.addressBook.getAppointmentList());


        viewedPersonAppointments = new FilteredList<>(this.addressBook.getAppointmentList());
        viewedPersonUpcomingAppointments = new FilteredList<>(viewedPersonAppointments, Appointment::isAfterNow);
        viewedPersonPastAppointments = new FilteredList<>(viewedPersonAppointments, Appointment::isBeforeNow);
        unmodifiableViewedPersonUpcomingAppointments = FXCollections.unmodifiableObservableList(
                new SortedList<>(viewedPersonUpcomingAppointments, Appointment::compareByDateTime));
        unmodifiableViewedPersonPastAppointments = FXCollections.unmodifiableObservableList(
                new SortedList<>(viewedPersonPastAppointments, Appointment::compareByDateTime));

        FilteredList<Appointment> allUpcomingAppointments =
                new FilteredList<>(this.addressBook.getAppointmentList(), Appointment::isAfterNow);
        FilteredList<Appointment> allPastAppointments =
                new FilteredList<>(this.addressBook.getAppointmentList(), Appointment::isBeforeNow);

        sortedAllUpcomingAppointments = new SortedList<>(allUpcomingAppointments, Appointment::compareByDateTime);
        sortedAllPastAppointments = new SortedList<>(allPastAppointments, Appointment::compareByDateTime);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return addressBook.hasAppointment(appointment);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        Predicate<Appointment> personAppointmentPredicate =
                a -> a.getPatientId().equals(target.getIdentityNumber());
        List<Appointment> toRemove = this.addressBook.getAppointmentList().stream()
                .filter(personAppointmentPredicate)
                .toList();
        toRemove.forEach(addressBook::removeAppointment);
        logger.fine("Removed appointments associated with deleted person: " + target.getName());
    }

    @Override
    public void deleteAppointment(Appointment target) {
        addressBook.removeAppointment(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        addressBook.addAppointment(appointment);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        if (!target.getIdentityNumber().equals(editedPerson.getIdentityNumber())) {
            IdentityNumber oldId = target.getIdentityNumber();
            IdentityNumber newId = editedPerson.getIdentityNumber();

            List<Appointment> appointmentsToUpdate = addressBook.getAppointmentList().stream()
                    .filter(appointment -> appointment.getPatientId().equals(oldId))
                    .collect(Collectors.toList());

            for (Appointment oldAppointment : appointmentsToUpdate) {
                Appointment updatedAppointment = new Appointment(
                        oldAppointment.getNotes(),
                        oldAppointment.getDateTime(),
                        newId
                );

                addressBook.setAppointment(oldAppointment, updatedAppointment);
            }
        }
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        addressBook.setAppointment(target, editedAppointment);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Appointment List Accessors ==========================================================

    /**
     * Updates the main filtered appointment list.
     */
    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    /**
     * Returns the main filtered appointment list. Used for general display/filtering.
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    /**
     * Returns a filtered list of appointments for a specific person ID.
     * Sets the internal viewed person list to the returned filtered list.
     * It filters directly from the source AddressBook list.
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList(IdentityNumber personId) {
        requireNonNull(personId);
        Predicate<Appointment> personPredicate = appointment -> appointment.getPatientId().equals(personId);
        viewedPersonAppointments.setPredicate(personPredicate);
        return viewedPersonAppointments;
    }


    //=========== Viewed Person Appointment List Accessors ===================================================

    /**
     * Returns the dedicated list holding upcoming appointments for the currently viewed person.
     * The UI (PersonViewPanel) should observe/use this list.
     */
    @Override
    public ObservableList<Appointment> getViewedPersonUpcomingAppointmentList() {
        return unmodifiableViewedPersonUpcomingAppointments;
    }

    /**
     * Returns the dedicated list holding past appointments for the currently viewed person.
     * The UI (PersonViewPanel) should observe/use this list.
     */
    @Override
    public ObservableList<Appointment> getViewedPersonPastAppointmentList() {
        return unmodifiableViewedPersonPastAppointments;
    }


    //=========== Upcoming/Past Appointment List Accessors ==================================================

    /**
     * Returns a sorted list of ALL upcoming appointments.
     */
    @Override
    public ObservableList<Appointment> getUpcomingAppointmentList() {
        return sortedAllUpcomingAppointments;
    }

    /**
     * Returns a sorted list of ALL past appointments.
     */
    @Override
    public ObservableList<Appointment> getPastAppointmentList() {
        return sortedAllPastAppointments;
    }


    //=========== Equals ====================================================================================

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredAppointments.equals(otherModelManager.filteredAppointments)
                && viewedPersonAppointments.equals(otherModelManager.viewedPersonAppointments);
    }
}
