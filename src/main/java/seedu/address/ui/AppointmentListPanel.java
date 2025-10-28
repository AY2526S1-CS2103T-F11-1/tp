package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;

/**
 * Panel containing the list of appointments, separated into upcoming and past.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    // Add FXML fields for both ListViews
    @FXML
    private ListView<Appointment> upcomingListView;
    @FXML
    private ListView<Appointment> pastListView;

    /**
     * Creates an {@code AppointmentListPanel} with the given upcoming and past {@code ObservableList}s.
     */
    public AppointmentListPanel(ObservableList<Appointment> upcomingList, ObservableList<Appointment> pastList) {
        super(FXML);

        upcomingListView.setItems(upcomingList);
        upcomingListView.setCellFactory(listView -> new AppointmentListViewCell());

        pastListView.setItems(pastList);
        pastListView.setCellFactory(listView -> new AppointmentListViewCell());

        getRoot().getStylesheets().add(getClass().getResource("/view/PersonViewPanel.css").toExternalForm());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Appointment}
     * using an {@code AppointmentCard}.
     */
    class AppointmentListViewCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(appointment, getIndex() + 1).getRoot());
            }
        }
    }
}
