package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public static final String MESSAGE_WARNING = " \nNote: Additional arguments detected. You may provide extra "
            + "arguments, but they will be ignored.\n";

    private final boolean hasExtraArgs;

    /*
     * Constructor for HelpCommand(default) when no extra arguments are provided.
     */
    public HelpCommand() {
        this.hasExtraArgs = false;
    }

    /*
     * Constructor for HelpCommand when extra arguments are provided.
     */
    public HelpCommand(String args) {
        this.hasExtraArgs = true;
    }

    @Override
    public CommandResult execute(Model model) {
        if (hasExtraArgs) {
            return new CommandResult(SHOWING_HELP_MESSAGE + MESSAGE_WARNING, true, false);
        } else {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof HelpCommand)) {
            return false;
        }

        HelpCommand otherHelpCommand = (HelpCommand) other;
        return this.hasExtraArgs == otherHelpCommand.hasExtraArgs;
    }
}
