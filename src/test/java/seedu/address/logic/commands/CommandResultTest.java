package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, null, null)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());

        // different themePath value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, null,
                "path/to/theme").hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", personToView=" + commandResult.getPersonToView()
                + ", isHelp=" + commandResult.isHelp() + ", isExit=" + commandResult.isExit()
                + ", themePath=" + commandResult.getThemePath() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void equals_personToViewAndThemePath() {
        Person person = new PersonBuilder().withName("Alice").build();
        Person anotherPerson = new PersonBuilder().withName("Bob").build();

        CommandResult base = new CommandResult("feedback", false, false, person, "theme1");
        CommandResult same = new CommandResult("feedback", false, false, person, "theme1");
        CommandResult differentPerson = new CommandResult("feedback", false, false, anotherPerson, "theme1");
        CommandResult differentTheme = new CommandResult("feedback", false, false, person, "theme2");

        // same personToView and same themePath -> true
        assertTrue(base.equals(same));

        // different personToView -> false
        assertFalse(base.equals(differentPerson));

        // different themePath -> false
        assertFalse(base.equals(differentTheme));
    }

    @Test
    public void getPersonToView_presentAndEmpty() {
        Person person = new PersonBuilder().withName("Alice").build();

        CommandResult withPerson = new CommandResult("feedback", false, false, person, null);
        CommandResult withoutPerson = new CommandResult("feedback");

        // personToView present -> Optional non-empty
        assertTrue(withPerson.getPersonToView().isPresent());
        assertEquals(person, withPerson.getPersonToView().get());

        // personToView absent -> Optional empty
        assertFalse(withoutPerson.getPersonToView().isPresent());
    }
}
