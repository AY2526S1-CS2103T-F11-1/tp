package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String MESSAGE_WARNING = " \nNote: Additional arguments detected. You may provide extra "
            + "arguments, but they will be ignored.\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all persons in the address book.";

    private final boolean hasExtraArgs;

    /*
        * Constructor for ListCommand(default) when no extra arguments are provided.
     */
    public ListCommand() {
        this.hasExtraArgs = false;
    }

    /*
        * Constructor for ListCommand when extra arguments are provided.
     */
    public ListCommand(String args) {
        this.hasExtraArgs = true;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (hasExtraArgs) {
            return new CommandResult(MESSAGE_SUCCESS + MESSAGE_WARNING);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ListCommand)) {
            return false;
        }

        ListCommand otherListCommand = (ListCommand) other;
        return this.hasExtraArgs == otherListCommand.hasExtraArgs;
    }
}
