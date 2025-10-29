package seedu.address.ui;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Panel containing the viewed person's details.
 */
public class PersonViewPanel extends UiPart<Region> {

    private static final String FXML = "PersonViewPanel.fxml";

    // CSS Style classes
    private static final String TAG_STYLE_CLASS = "person-view-tag";
    private static final String NONE_TAG_STYLE_CLASS = "person-view-none-tag";
    private static final String FIELD_LABEL_STYLE_CLASS = "person-view-field-label";
    private static final String VALUE_LABEL_STYLE_CLASS = "person-view-value-label";

    private final Person person;

    @FXML
    private VBox personViewContainer;

    @FXML
    private Label nameLabel;

    @FXML
    private GridPane personalInfoGrid;

    @FXML
    private GridPane medicalInfoGrid;

    @FXML
    private FlowPane allergiesFlowPane;

    @FXML
    private FlowPane pastDiagnosisFlowPane;

    @FXML
    private FlowPane medicineFlowPane;

    @FXML
    private VBox upcomingAppointmentsBox;

    @FXML
    private VBox pastAppointmentsBox;

    /**
     * Creates a {@code PersonViewPanel} with the given {@code Person} and their separated appointment lists.
     *
     * @param person The person whose details are to be displayed.
     * @param upcomingAppointments The list of upcoming appointments associated with the person.
     * @param pastAppointments The list of past appointments associated with the person.
     */
    public PersonViewPanel(Person person, ObservableList<Appointment> upcomingAppointments,
                           ObservableList<Appointment> pastAppointments) {
        super(FXML);
        this.person = person;
        getRoot().getStylesheets().add(getClass().getResource("/view/PersonViewPanel.css").toExternalForm());

        fillPersonDetails();
        fillAppointments(upcomingAppointments, pastAppointments);
    }

    /**
     * Fills in the person's details for viewing.
     */
    private void fillPersonDetails() {
        nameLabel.setText(person.getName().fullName);

        // Personal Info
        addGridRow(personalInfoGrid, "Identity Number:", person.getIdentityNumber().identityNumber);
        addGridRow(personalInfoGrid, "Date of Birth:", person.getDateOfBirth().toString()
                + " (" + person.getDateOfBirth().calculateAge() + " yrs old)");
        addGridRow(personalInfoGrid, "Gender:", person.getGender().toString());
        addGridRow(personalInfoGrid, "Phone:", person.getPhone().value);
        addGridRow(personalInfoGrid, "Email:", person.getEmail().value);
        addGridRow(personalInfoGrid, "Address:", person.getAddress().value);

        // Medical Info
        addGridRow(medicalInfoGrid, "Emergency Contact:", person.getEmergencyContact().toString());
        addGridRow(medicalInfoGrid, "Blood Type:", person.getBloodType().toString());
        addGridRow(medicalInfoGrid, "Alcoholic Record:", person.getAlcoholicRecord().toString());
        addGridRow(medicalInfoGrid, "Smoking Record:", person.getSmokingRecord().toString());
        addGridRow(medicalInfoGrid, "Past Medical History:", person.getPastMedicalHistory().toString());

        // Lists
        Set<String> allergyValues = person.getAllergies().stream()
                .map(a->a.allergyName)
                .collect(Collectors.toSet());
        Set<String> medicineValues = person.getMedicines().stream()
                .map(m->m.medicine)
                .collect(Collectors.toSet());
        populateFlowPane(allergiesFlowPane, allergyValues);
        populateFlowPane(medicineFlowPane, medicineValues);
    }

    /**
     * Fills in the person's appointments using the pre-filtered and sorted lists.
     * Clears previous appointment content before adding new items.
     *
     * @param upcomingAppointments The list of upcoming appointments (sorted chronologically).
     * @param pastAppointments The list of past appointments (sorted reverse chronologically).
     */
    private void fillAppointments(ObservableList<Appointment> upcomingAppointments,
                                  ObservableList<Appointment> pastAppointments) {
        populateAppointmentBox(upcomingAppointmentsBox, upcomingAppointments);
        populateAppointmentBox(pastAppointmentsBox, pastAppointments);
    }

    /**
     * Populates the given VBox with appointment labels from the provided list.
     * If the list is empty or null, adds a "None" label.
     *
     * @param box The VBox to populate.
     * @param appointments The list of appointments to display.
     */
    private void populateAppointmentBox(VBox box, ObservableList<Appointment> appointments) {
        box.getChildren().clear();

        if (appointments == null || appointments.isEmpty()) {
            Label noneLabel = new Label("None");
            noneLabel.getStyleClass().add(NONE_TAG_STYLE_CLASS);
            box.getChildren().add(noneLabel);
        } else {
            for (Appointment appointment : appointments) {
                Label apptLabel = new Label(formatAppointmentForDisplay(appointment));
                apptLabel.getStyleClass().add(VALUE_LABEL_STYLE_CLASS);
                apptLabel.setWrapText(true);
                box.getChildren().add(apptLabel);
            }
        }
    }

    /**
     * Formats an appointment's date, time, and notes for display in the view panel.
     *
     * @param appointment The appointment to format.
     * @return A formatted string representation.
     */
    private String formatAppointmentForDisplay(Appointment appointment) {
        String dateTimeStr = appointment.getDateTime().toString();
        String notesStr = appointment.getNotes().toString();
        return dateTimeStr + (notesStr.isEmpty() ? "" : ": " + notesStr);
    }

    /**
     * Adds a new row (field name and value) to a given GridPane.
     * Aligns field names to the top-right and allows values to wrap.
     *
     * @param grid The GridPane to add to.
     * @param fieldName The text for the field label (e.g., "Phone:").
     * @param value The text for the value label.
     */
    private void addGridRow(GridPane grid, String fieldName, String value) {
        Label fieldLabel = new Label(fieldName);
        fieldLabel.getStyleClass().add(FIELD_LABEL_STYLE_CLASS);
        fieldLabel.setAlignment(Pos.TOP_RIGHT);

        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add(VALUE_LABEL_STYLE_CLASS);
        valueLabel.setWrapText(true);

        int newRowIndex = grid.getRowCount();
        grid.add(fieldLabel, 0, newRowIndex);
        grid.add(valueLabel, 1, newRowIndex);
    }

    /**
     * Populates a FlowPane with styled labels from a Set of items (like Allergies or Medicines).
     * If the set is empty or null, it adds a "None" label.
     *
     * @param pane The FlowPane to populate.
     * @param items The Set of items to display. Items should have a meaningful `toString()` representation.
     */
    private void populateFlowPane(FlowPane pane, Set<?> items) {
        pane.getChildren().clear();

        if (items == null || items.isEmpty()) {
            Label noneLabel = new Label("None");
            noneLabel.getStyleClass().add(NONE_TAG_STYLE_CLASS);
            pane.getChildren().add(noneLabel);
            return;
        }

        List<String> sortedItemStrings = items.stream()
                .map(Object::toString)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();

        for (String itemStr : sortedItemStrings) {
            Label itemLabel = new Label(itemStr);
            itemLabel.getStyleClass().add(TAG_STYLE_CLASS);
            pane.getChildren().add(itemLabel);
        }
    }
}
