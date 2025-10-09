package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import seedu.address.model.util.DateParserUtil;

/**
 * Represents a person's date of birth in the system.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateOfBirth(String)}.
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be of the following formats: DD-MM-YYYY, DD/MM/YYYY or DD.MM.YYYY";

    /**
     * A list of {@code DateTimeFormatter} objects that define the supported date formats.
     * The formats include:
     * - "dd-MM-yyyy": Dates with dashes as separators (e.g., 01-01-2023).
     * - "dd/MM/yyyy": Dates with slashes as separators (e.g., 01/01/2023).
     * - "dd.MM.yyyy": Dates with dots as separators (e.g., 01.01.2023).
     * These formatters are used to parse and validate user-provided date inputs
     * and ensure compatibility with the system's date handling logic.
     */
    public static final List<DateTimeFormatter> FORMATTERS = List.of(
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("dd.MM.yyyy")
    );

    /**
     * A {@code DateTimeFormatter} that specifies the pattern for date formatting and parsing.
     * The pattern used is "dd-MM-yyyy", which represents a date in the format: day-month-year.
     * Ensures consistency in handling date formats across the application.
     */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * A regular expression that defines the validation rules for date strings.
     * The rules are as follows:
     * - The date must be in the format "DD-MM-YYYY", "DD/MM/YYYY", or "DD.MM.YYYY".
     * - The day (DD) must be a valid number ranging from 00 to 31.
     * - The month (MM) must be a valid number ranging from 00 to 12.
     * - The year (YYYY) must be a four-digit number.
     * - The separator between day, month, and year can be "-", "/", or ".".
     * These rules ensure the date is formatted correctly and can be validated against the system's requirements.
     */ /*
     * Dates: 00 to 31
     * Separators: -, /, or .
     * Months: 00 to 12
     * Separators: -, /, or .
     * Years: Four digits
     */
    public static final String VALIDATION_REGEX = "^(0[1-9]|[12]\\d|3[01])[-/.](0[1-9]|1[0-2])[-/.](\\d{4})$";

    public final LocalDate dateOfBirth;

    /**
     * Constructs a {@code DateOfBirth}.
     * Validates and parses the input date of the birth string, ensuring it conforms to the required format.
     *
     * @param dateOfBirth A string representing the date of birth, which must conform
     *                    to the accepted date formats and validation rules.
     *                    Must not be null or invalid.
     * @throws IllegalArgumentException If the provided {@code dateOfBirth} string
     *                                  does not pass validation.
     */
    public DateOfBirth(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        checkArgument(isValidDateOfBirth(dateOfBirth), MESSAGE_CONSTRAINTS);
        this.dateOfBirth = DateParserUtil.parseDate(dateOfBirth, FORMATTERS);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDateOfBirth(String test) {
        return test.matches(VALIDATION_REGEX) && DateParserUtil.isValidDate(test, FORMATTERS);
    }


    @Override
    public String toString() {
        return DateParserUtil.formatDate(dateOfBirth, DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateOfBirth otherDate)) {
            return false;
        }

        return dateOfBirth.equals(otherDate.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return dateOfBirth.hashCode();
    }

}
