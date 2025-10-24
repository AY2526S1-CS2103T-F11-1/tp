package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_IDENTITY_NUMBER = new Prefix("id/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_EMERGENCY_CONTACT = new Prefix("ec/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_ALLERGY = new Prefix("al/");
    public static final Prefix PREFIX_DATE_OF_BIRTH = new Prefix("dob/");
    public static final Prefix PREFIX_BLOOD_TYPE = new Prefix("b/");
    public static final Prefix PREFIX_ALCOHOLIC_RECORD = new Prefix("ar/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_SMOKING_RECORD = new Prefix("s/");
    public static final Prefix PREFIX_PAST_DIAGNOSES = new Prefix("pd/");
    public static final Prefix PREFIX_MEDICINE = new Prefix("m/");
    public static final Prefix PREFIX_APPOINTMENT_TIME = new Prefix("adt/");
    public static final Prefix PREFIX_APPOINTMENT_NOTE = new Prefix("note/");
}
