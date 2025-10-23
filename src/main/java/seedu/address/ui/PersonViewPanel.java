package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * Panel containing the viewed person's details.
 */
public class PersonViewPanel extends UiPart<Region> {

    private static final String FXML = "PersonViewPanel.fxml";
    private final Person person;

    @FXML
    private VBox personViewContainer;

    @FXML
    private Label nameLabel;

    @FXML
    private Label identityNumberLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label bloodTypeLabel;

    @FXML
    private Label alcoholicRecordLabel;

    @FXML
    private Label gender;

    @FXML
    private Label dateOfBirthAndAgeLabel;

    @FXML
    private Label smokingRecordLabel;

    @FXML
    private Label medicineLabel;

    @FXML
    private Label pastDiagnosisLabel;

    @FXML
    private Label allergiesLabel;

    /**
     * Creates a {@code PersonViewPanel} with the given {@code Person}.
     */
    public PersonViewPanel(Person person) {
        super(FXML);
        this.person = person;
        fillPersonDetails();
    }

    /**
     * Fills in the person's details for viewing.
     */
    private void fillPersonDetails() {
        nameLabel.setText(person.getName().fullName);
        identityNumberLabel.setText(person.getIdentityNumber().identityNumber);
        phoneLabel.setText(person.getPhone().value);
        emailLabel.setText(person.getEmail().value);
        addressLabel.setText(person.getAddress().value);
        bloodTypeLabel.setText(person.getBloodType().toString());
        alcoholicRecordLabel.setText(person.getAlcoholicRecord().toString());
        gender.setText(person.getGender().toString());
        dateOfBirthAndAgeLabel.setText(person.getDateOfBirth().toString());
        smokingRecordLabel.setText(person.getSmokingRecord().toString());
        medicineLabel.setText(person.getMedicines().toString());
        pastDiagnosisLabel.setText(person.getPastDiagnoses().toString());
        allergiesLabel.setText(person.getAllergies().toString());

    }
}

