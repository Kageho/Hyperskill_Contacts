package contacts.contactTypes.person;

import contacts.contactTypes.Contact;
import contacts.contactTypes.person.dateValidator.DateValidatorRegex;

import java.util.List;
import java.util.Scanner;

public class Person extends Contact {
    private String surname;
    private String birthDate;
    private String gender;
    private static final List<String> nameOfFields;

    static {
        nameOfFields = List.of("name", "surname", "birth", "gender", "number");
    }

    private Person(String name, String surname, String birthDate, String gender, String phone) {
        super(name, phone);
        this.birthDate = birthDate;
        this.gender = gender;
        this.surname = surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    private void setBirthDate(String birthDate) {
        this.birthDate = checkBirthDate(birthDate);
    }

    private void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + name.hashCode();
        result = result * 31 + (surname == null ? 11 : surname.hashCode());
        result = result * 31 + (gender == null ? 19 : gender.hashCode());
        result = result * 31 + (birthDate == null ? 23 : birthDate.hashCode());
        result = result * 31 + (phone == null ? 29 : phone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else if (!(obj instanceof Person)) {
            return false;
        } else {
            Person person = (Person) obj;
            return person.hashCode() == hashCode();
        }
    }

    @Override
    public String getName() {
        return String.format("%s %s", name, surname);
    }

    @Override
    public List<String> getNameOfFields() {
        return nameOfFields;
    }


    @Override
    public void editField(String fieldName, String value) {
        personProperty property;
        try {
            property = personProperty.valueOf(fieldName.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("There is no such field!");
            return;
        }
        switch (property) {
            case NAME:
                setName(value);
                break;
            case NUMBER:
                setPhoneNumber(value);
                if (hasNumber()) {
                    System.out.println("Wrong number format!");
                }
                break;
            case SURNAME:
                setSurname(value);
                break;
            case GENDER:
                setGender(value);
                break;
            case BIRTH:
                setBirthDate(value);
        }
        System.out.println("The record updated!");
        // contact is updated
        newUpdatedTime();
    }
    // method works until the date gets valid format
    // or the date is blank
    private static String checkBirthDate(String birth) {
        if (birth.isBlank()) {
            System.out.println("Bad birth date!");
            return birth;
        }
        while (!DateValidatorRegex.isValid(birth)) {
            System.out.println("Birth date is invalid");
            System.out.println("Enter date in yyyy.mm.dd format!");
            Scanner scan = new Scanner(System.in);
            birth = scan.nextLine();
        }
        return birth;
    }

    @Override
    public String[] getValuesOfFields() {
        return new String[]{name, surname, birthDate, gender, phone};
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Name: ").append(name).append("\n");
        info.append("Surname: ").append(surname.isBlank() ? "[no data]" : surname).append("\n");
        info.append("Birth date: ").append(birthDate.isBlank() ? "[no data]" : birthDate).append("\n");
        info.append("Gender: ").append(gender.isBlank() ? "[no data]" : gender).append("\n");
        info.append("Number: ").append(hasNumber ? phone : "[no data]").append("\n");
        info.append("Time created: ").append(timeCreated).append("\n");
        info.append("Time last edit: ").append(timeUpdated);
        return info.toString();
    }

    public static class PersonBuilder {
        private String name;
        private String surname;
        private String birthDate;
        private String gender;
        private String phone;

        public PersonBuilder setName(String name) {
            this.name = name.isBlank() ? getNonBlankName(new Scanner(System.in), name) : name;
            return this;
        }

        public PersonBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonBuilder setBirthDate(String birthDate) {
            this.birthDate = checkBirthDate(birthDate);
            return this;
        }

        public PersonBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Person build() {
            return new Person(name, surname, birthDate, gender, phone);
        }
    }
}
