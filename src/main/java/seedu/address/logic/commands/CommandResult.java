package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final Optional<String> themePath;
    private final Optional<Person> personToView;
    private final boolean isHelp;
    private final boolean isExit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, Person personToView, String themePath) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isHelp = showHelp;
        this.isExit = exit;
        this.personToView = Optional.ofNullable(personToView);
        this.themePath = Optional.ofNullable(themePath);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, null, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields and
     * the personToView field set to its default value.
     */
    public CommandResult(String feedbackToUser, boolean isHelp, boolean isExit) {
        this(feedbackToUser, isHelp, isExit, null, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isHelp() {
        return isHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public Optional<Person> getPersonToView() {
        return personToView;
    }

    public boolean isView() {
        return personToView.isPresent();
    }

    public Optional<String> getThemePath() {
        return themePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isHelp == otherCommandResult.isHelp
                && isExit == otherCommandResult.isExit
                && Objects.equals(this.personToView, otherCommandResult.personToView)
                && Objects.equals(themePath, otherCommandResult.themePath);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("personToView", personToView)
                .add("isHelp", isHelp)
                .add("isExit", isExit)
                .add("themePath", themePath)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, personToView, themePath, isHelp, isExit);
    }
}
