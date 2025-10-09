package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DateParserUtilTest {

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ISO_LOCAL_DATE
    );

    @Test
    public void parseDate_validIsoFormat_success() {
        String validDate = "2020-12-31";
        LocalDate expected = LocalDate.of(2020, 12, 31);
        assertEquals(expected, DateParserUtil.parseDate(validDate, FORMATTERS));
    }

    @Test
    public void parseDate_invalidFormat_throwsIllegalArgumentException() {
        String invalidDate = "31/12/2020";
        assertThrows(DateTimeParseException.class, () -> DateParserUtil.parseDate(invalidDate, FORMATTERS));
    }

    @Test
    public void parseDate_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "2020-02-30";
        assertThrows(DateTimeParseException.class, () -> DateParserUtil.parseDate(invalidDate, FORMATTERS));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DateParserUtil.parseDate(null, FORMATTERS));
    }
}
