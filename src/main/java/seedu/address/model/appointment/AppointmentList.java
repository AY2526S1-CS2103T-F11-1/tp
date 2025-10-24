package seedu.address.model.appointment;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Person;


/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls.
 * An appointment is considered unique by comparing using {@code Appointment#isSameAppointment(Appointment)}.
 * As such, adding and updating of appointment uses Appointment#isSameAppointment(Appointment) for equality to
 * ensure that the appointment being added or updated is unique in terms of identity in the AppointmentList.
 * However, the removal of an appointment uses Appointment#equals(Object) to ensure that the appointment
 * with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class AppointmentList implements Iterable<Appointment> {

    private Person patient;
    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> unmodifiableAppointmentList =
            FXCollections.unmodifiableObservableList(appointmentList);

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return appointmentList.stream().anyMatch(toCheck::isSameAppointment);
    }

    /**
     * Adds an appointment to the list.
     * The appointment must not exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        appointmentList.add(toAdd);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The {@code editedAppointment} must not be the same as another existing appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = appointmentList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.isSameAppointment(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        appointmentList.set(index, editedAppointment);
    }

    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!appointmentList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    /**
     * Removes the equivalent appointments from the list.
     * The appointments must exist in the list.
     */
    public void removeAll(Appointment... toRemove) {
        requireNonNull(toRemove);
        if (!appointmentList.removeAll(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    public void setAppointments(AppointmentList replacement) {
        requireNonNull(replacement);
        appointmentList.setAll(replacement.appointmentList);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        appointmentList.setAll(appointments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return unmodifiableAppointmentList;
    }

    @Override
    public Iterator<Appointment> iterator() {
        return appointmentList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentList otherAppointmentList)) {
            return false;
        }

        return appointmentList.equals(otherAppointmentList.appointmentList);
    }

    @Override
    public int hashCode() {
        return appointmentList.hashCode();
    }

    @Override
    public String toString() {
        return appointmentList.toString();
    }

    /**
     * Returns true if {@code appointments} contains only unique appointments.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).isSameAppointment(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
