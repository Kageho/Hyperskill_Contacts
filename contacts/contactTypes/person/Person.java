package contacts.contactTypes.person;

import contacts.contactTypes.Contact;

public class Person extends Contact {
    private String surname;
    private String birthDate;
    private String gender;


    private Person(String name, String surname, String birthDate, String gender, String phone) {
        super(name, phone, true);

        this.birthDate = birthDate;

        this.gender = gender;
        this.surname = surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
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
            this.name = name;
            return this;
        }

        public PersonBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonBuilder setBirthDate(String birthDate) {
            this.birthDate = birthDate;
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
