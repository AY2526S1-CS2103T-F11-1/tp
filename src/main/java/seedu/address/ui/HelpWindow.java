package seedu.address.ui;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page that shows Markdown content.
 */
public class HelpWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    private static final String AVAILABLECOMMANDSPATH = "/docs/AvailableCommands.md";

    @FXML
    private WebView helpContent;

    /**
     * Creates a HelpWindow using the given Stage as the root.
     *
     * @param root The Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        loadMarkdownFile();
    }

    /**
     * Creates a HelpWindow with a new Stage.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Loads the Available Commands Markdown file into the WebView.
     * If the file is not found or an error occurs, an error message is displayed.
     */
    private void loadMarkdownFile() {
        try (InputStream input = getClass().getResourceAsStream(AVAILABLECOMMANDSPATH)) {
            if (input == null) {
                helpContent.getEngine().loadContent("<p>Available Commands file not found</p>");
                return;
            }

            String markdown = new String(input.readAllBytes(), StandardCharsets.UTF_8);

            Parser parser = Parser.builder().build();
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            Node document = parser.parse(markdown);
            String html = renderer.render(document);

            helpContent.getEngine().loadContent(html);
        } catch (IOException e) {
            helpContent.getEngine().loadContent("<p>Error loading Available Commands file.</p>");
        }
    }

    /**
     * Shows the help window.
     */
    public void show() {
        logger.fine("Showing help page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void hide() {
        getRoot().hide();
    }

    public void focus() {
        getRoot().requestFocus();
    }
}
