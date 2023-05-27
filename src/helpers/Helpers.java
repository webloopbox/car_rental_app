package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    /**
     * Checks if the given email address is valid.
     *
     * @param email the email address to validate
     * @return true if the email address is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Checks if the given phone number is a valid Polish phone number. The
     * phone number should have 9 digits and may include spaces or dashes for
     * formatting.
     *
     * @param phoneNumber the phone number to validate
     * @return true if the phone number is a valid Polish phone number, false
     * otherwise
     */
    public boolean isValidPolishPhoneNumber(String phoneNumber) {
        // The regular expression pattern for a Polish phone number with 9 digits
        String regex = "^(\\d[ -]?){9}$";
        return phoneNumber.matches(regex);
    }
}
