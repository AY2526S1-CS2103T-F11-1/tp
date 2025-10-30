package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Changes the UI theme of the application.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = "Available themes include (light/dark/pink/blue)\n"
            + "Example: " + COMMAND_WORD + " light";

    public static final String MESSAGE_SUCCESS = "Theme changed to %s.";
    public static final String MESSAGE_UNKNOWN_THEME = "Unknown theme. " + MESSAGE_USAGE;
    private static final Map<String, String> THEME_MAP = Map.of(
            "light", "/view/light.css",
            "dark", "/view/dark.css",
            "pink", "/view/pink.css",
            "blue", "/view/blue.css"
    );

    private final String theme;

    /**
     * Creates a ThemeCommand to change the UI theme.
     *
     * @param theme The theme to switch to.
     */
    public ThemeCommand(String theme) {
        requireNonNull(theme);
        this.theme = theme.toLowerCase();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!isValidTheme(theme)) {
            throw new CommandException(MESSAGE_UNKNOWN_THEME);
        }

        String themePath = THEME_MAP.get(theme);
        return new CommandResult(String.format(MESSAGE_SUCCESS, theme), false, false, null, themePath);
    }

    private boolean isValidTheme(String theme) {
        return theme.equals("light")
                || theme.equals("dark")
                || theme.equals("pink")
                || theme.equals("blue");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ThemeCommand // instanceof handles nulls
                && theme.equals(((ThemeCommand) other).theme));
    }
}
