package contacts.contactTypes;
/*class represents contact in phone book
 * has static class Builder just like from Builder pattern from
 * https://www.baeldung.com/creational-design-patterns#builder
 *
 * Base class for contact entity
 * The class implements serializable
 * For saving contacts to file and restoring objects from there
 * */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {
    protected String name;
    protected LocalDateTime timeCreated;
    protected LocalDateTime timeUpdated;
    protected String phone;
    protected boolean hasNumber;


    protected Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
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
    protected void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
        hasNumber = isValidNumber(phoneNumber);
    }

    public static String getNonBlankName(Scanner scan, String name) {
        while (name.isBlank()) {
            System.out.println("Name cannot be an empty string! Enter name:");
            name = scan.nextLine();
        }
        return name;
    }

    public boolean hasNumber() {
        return hasNumber;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = getNonBlankName(new Scanner(System.in), name);
    }

    protected void newUpdatedTime() {
        this.timeUpdated = LocalDateTime.now();
    }

    public abstract List<String> getNameOfFields();

    // this method allows us see polymorphism in action
    public abstract void editField(String fieldName, String value);

    public abstract String[] getValuesOfFields();
}
