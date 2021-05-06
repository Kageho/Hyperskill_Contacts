package contacts.person;
/*class represents contact in phone book
* has static class Builder just like from Builder pattern from
* https://www.baeldung.com/creational-design-patterns#builder
* */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean hasNumber;

    private Person(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.hasNumber = isValidNumber(phoneNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return hasNumber ? phoneNumber : "[no number]";
    }
// if number doesn't match pattern then field hasNumber is false
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        hasNumber = isValidNumber(phoneNumber);
    }

    public boolean hasNumber() {
        return hasNumber;
    }

    private static boolean isValidNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("(?i)\\s*\\+?( *[a-z\\d]+( +| *- *)\\( *[a-z\\d]{2,} *\\)|" +
                " *\\( *[a-z\\d]+ *\\)( +| *- *)[a-z\\d]{2,}| *[a-z\\d]+)(( +| *- *)([a-z\\d]{2,}))*");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() * 13 + lastName.hashCode() * 11 + phoneNumber.hashCode() * 17;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!(obj instanceof Person)) {
            return false;
        } else {
            Person person = (Person) obj;
            return firstName.equals(person.firstName) && lastName.equals(person.lastName)
                    && phoneNumber.equals(person.phoneNumber);
        }
    }

    @Override
    public String toString() {
        StringBuilder personInfo = new StringBuilder();
        personInfo.append(firstName).append(" ");
        personInfo.append(lastName).append(", ");
        personInfo.append(hasNumber ? phoneNumber : "[no number]");
        return personInfo.toString();
    }

    public static class PersonBuilder {
        private String firstName;
        private String lastName;
        private String phoneNumber;

        public PersonBuilder() {
        }

        public PersonBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Person build() {
            return new Person(firstName, lastName, phoneNumber);
        }
    }
}
