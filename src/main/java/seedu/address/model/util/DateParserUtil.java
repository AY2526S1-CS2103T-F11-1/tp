package seedu.address.model.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Utility class for parsing and formatting dates using specified {@code DateTimeFormatter} objects.
 */
public class DateParserUtil {

    /**
     * Parses the given date string using a list of {@code DateTimeFormatter} objects and returns
     * the matching {@code LocalDate}.
     * If none of the formatters match, a {@code DateTimeParseException} is thrown.
     *
     * @param date The date string to be parsed, which must conform to at least one of the formats
     *             specified in the {@code formatters} list.
     * @param formatters A list of {@code DateTimeFormatter} objects that define the acceptable formats
     *                   for the date string.
     * @return The parsed {@code LocalDate} object if the date string matches one of the specified formats.
     * @throws DateTimeParseException
     *      If the date string does not match any of the provided {@code DateTimeFormatter} formats
     */
    public static LocalDate parseDate(String date, List<DateTimeFormatter> formatters) throws DateTimeParseException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // continue to next formatter
            }
        }
        // should never reach here if isValidDate is called before
        throw new DateTimeParseException("Invalid date format", date, 0);
    }

    /**
     * Formats a given {@code LocalDate} object to a {@code String} using the specified {@code DateTimeFormatter}.
     *
     * @param date The {@code LocalDate} object to be formatted. Must not be null.
     * @param formatter The {@code DateTimeFormatter} to use for formatting. Must not be null.
     * @return A {@code String} representation of the formatted date.
     */
    public static String formatDate(LocalDate date, DateTimeFormatter formatter) {
        return date.format(formatter);
    }

    /**
     * Checks if a given date string is valid according to any of the specified {@code DateTimeFormatter} formats.
     *
     * @param dateString The date string to be validated. Must not be null.
     * @param formatters
     *      A list of {@code DateTimeFormatter} objects that define the acceptable date formats. Must not be null.
     * @return {@code true} if the date string matches at least one of the specified formats, {@code false} otherwise.
     */
    public static boolean isValidDate(String dateString, List<DateTimeFormatter> formatters) {
        return formatters.stream().anyMatch(formatter -> isValid(dateString, formatter));
    }

    private static boolean isValid(String dateString, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
