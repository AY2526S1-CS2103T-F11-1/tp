package seedu.address.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String DEFAULT_THEME_PATH = "/view/dark.css";
    private static  String currentThemePath;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private AppointmentListPanel appointmentListPanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane detailsPanelPlaceholder;

    @FXML
    private SplitPane mainSplitPane;

    @FXML
    private StackPane appointmentListPanelPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic, String initialThemePath) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        currentThemePath = initialThemePath != null ? initialThemePath : DEFAULT_THEME_PATH; // Default theme
        setTheme(initialThemePath);

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        appointmentListPanel =
                new AppointmentListPanel(logic.getUpcomingAppointmentList(), logic.getPastAppointmentList());
        appointmentListPanelPlaceholder.getChildren().add(appointmentListPanel.getRoot());

        detailsPanelPlaceholder.getChildren().clear();
        Label defaultLabel = new Label("Select a person using the 'view' command to see details.");
        defaultLabel.setStyle("-fx-text-fill: grey; -fx-font-style: italic;");
        detailsPanelPlaceholder.getChildren().add(defaultLabel);
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Handles switching to the detailed person view.
     * Triggered when a view command for a person completes.
     */
    @FXML
    private void handleView() {
        Optional<Person> personOptional = logic.getPersonToView();

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            ObservableList<Appointment> upcomingAppointments = logic.getViewedPersonUpcomingAppointmentList();
            ObservableList<Appointment> pastAppointments = logic.getViewedPersonPastAppointmentList();

            PersonViewPanel personViewPanel = new PersonViewPanel(person, upcomingAppointments, pastAppointments);
            detailsPanelPlaceholder.getChildren().setAll(personViewPanel.getRoot());
        } else {
            logger.warning("handleViewPerson called but no person was available in Logic.");
            handleDefaultView();
        }
    }

    /**
     * Resets the view to the default single-panel layout (shows only the person list).
     */
    private void handleDefaultView() {
        detailsPanelPlaceholder.getChildren().clear();
        Label defaultLabel = new Label("Select a person using the 'view <index>' command to see details.");
        defaultLabel.setStyle("-fx-text-fill: grey; -fx-font-style: italic;");
        detailsPanelPlaceholder.getChildren().add(defaultLabel);

        logic.clearViewedData();
    }

    /**
     * Sets the theme of the application. Includes fallback to default theme.
     * @param themePath The path to the CSS file.
     */
    private void setTheme(String themePath) {
        if (primaryStage.getScene() == null) {
            logger.warning("Scene not available for setting theme.");
            return;
        }
        try {
            String cssUrl = getClass().getResource(themePath).toExternalForm();
            primaryStage.getScene().getStylesheets().clear();
            primaryStage.getScene().getStylesheets().add(cssUrl);
            currentThemePath = themePath;
            logger.info("Applied theme: " + themePath);
        } catch (NullPointerException e) {
            logger.severe("Could not find theme CSS file: " + themePath + ". Applying default theme.");
            if (!themePath.equals(DEFAULT_THEME_PATH)) { // Avoid infinite loop if default itself fails
                setTheme(DEFAULT_THEME_PATH);
            }
        } catch (Exception e) {
            logger.severe("Error applying theme: " + themePath + " - " + e.getMessage());
            // Optionally add fallback here too
        }
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isView()) {
                handleView();
            }

            commandResult.getThemePath().ifPresent(this::setTheme);

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
