package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPolishPhoneNumber(String phoneNumber) {
        // The regular expression pattern for a Polish phone number with 9 digits
        String regex = "^(\\d[ -]?){9}$";
        return phoneNumber.matches(regex);
    }
}
