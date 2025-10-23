package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameOrIdContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameOrIdContainsKeywordsPredicate firstPredicate =
                new NameOrIdContainsKeywordsPredicate(firstPredicateKeywordList);
        NameOrIdContainsKeywordsPredicate secondPredicate =
                new NameOrIdContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameOrIdContainsKeywordsPredicate firstPredicateCopy =
                new NameOrIdContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameOrIdContainsKeywordsPredicate predicate =
                new NameOrIdContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameOrIdContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameOrIdContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameOrIdContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameOrIdContainsKeywordsPredicate predicate = new NameOrIdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameOrIdContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameOrIdContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_idContainsKeywords_returnsTrue() {
        // One keyword
        NameOrIdContainsKeywordsPredicate predicate =
                new NameOrIdContainsKeywordsPredicate(Collections.singletonList("A23"));
        assertTrue(predicate.test(new PersonBuilder().withIdentityNumber("A23").build()));

        // Multiple keywords (one matches)
        predicate = new NameOrIdContainsKeywordsPredicate(Arrays.asList("A23", "B99"));
        assertTrue(predicate.test(new PersonBuilder().withIdentityNumber("A23").build()));

        // Only one matching keyword among others
        predicate = new NameOrIdContainsKeywordsPredicate(Arrays.asList("B99", "A23"));
        assertTrue(predicate.test(new PersonBuilder().withIdentityNumber("A23").build()));

        // Mixed-case keyword (identity matching should be case-insensitive)
        predicate = new NameOrIdContainsKeywordsPredicate(Arrays.asList("a23"));
        assertTrue(predicate.test(new PersonBuilder().withIdentityNumber("A23").build()));
    }

    @Test
    public void test_idDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameOrIdContainsKeywordsPredicate predicate = new NameOrIdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withIdentityNumber("A23").build()));

        // Non-matching keyword
        predicate = new NameOrIdContainsKeywordsPredicate(Arrays.asList("B99"));
        assertFalse(predicate.test(new PersonBuilder().withIdentityNumber("A23").build()));

        // Keywords match phone, email and address, but do not match identity number
        predicate = new NameOrIdContainsKeywordsPredicate(
                Arrays.asList("87438807", "alexyeoh@example.com", "Geylang", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withIdentityNumber("A23").withPhone("87438807")
                .withEmail("alexyeoh@example.com").withAddress("Blk 30 Geylang Street 29, #06-40").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameOrIdContainsKeywordsPredicate predicate = new NameOrIdContainsKeywordsPredicate(keywords);

        String expected = NameOrIdContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
