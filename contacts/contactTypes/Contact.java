package contacts.contactTypes;
/*class represents contact in phone book
 * has static class Builder just like from Builder pattern from
 * https://www.baeldung.com/creational-design-patterns#builder
 * */

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    protected String name;
    protected LocalDateTime timeCreated;
    protected LocalDateTime timeUpdated;
    protected String phone;
    public static int withoutName = 0;
    protected boolean hasNumber;
    protected boolean isPerson;

    protected Contact(String name, String phone, boolean isPerson) {
        this.name = name;
        this.phone = phone;
        this.isPerson = isPerson;
        this.hasNumber = isValidNumber(phone);
        this.timeCreated = LocalDateTime.now();
        this.timeUpdated = LocalDateTime.now();
    }

    protected static boolean isValidNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("(?i)\\s*\\+?((\\s*\\(\\s*[a-z\\d]+\\s*\\)(\\s+| *- *)" +
                "([a-z\\d]{2,}))|(\\s*[a-z\\d]+(\\s+| *- *)\\( *([a-z\\d]{2,}) *\\))|(\\s*\\(\\s*[a-z\\d]+\\s*\\)\\s*)" +
                "|(\\s*[a-z\\d]+\\s*))((\\s+| *- *)[a-z\\d]{2,})*");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // if number doesn't match pattern then field hasNumber is false
    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
        hasNumber = isValidNumber(phoneNumber);
    }

    public boolean hasNumber() {
        return hasNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void newUpdatedTime() {
        this.timeUpdated = LocalDateTime.now();
    }
}
