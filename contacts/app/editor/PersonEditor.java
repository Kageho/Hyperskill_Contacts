package contacts.app.editor;

import contacts.contactTypes.Contact;
import contacts.contactTypes.person.Person;
import contacts.contactTypes.person.personProperty;

import java.util.Scanner;

public class PersonEditor {

    public void editPerson(Contact contact) {
        Scanner scan = new Scanner(System.in);
        Person person = (Person) contact;
        System.out.print("Select a field (name, surname, birth, gender, number): > ");
        personProperty property;
        try {
            property = personProperty.valueOf(scan.next().toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("There is no such field!");
            return;
        }
        changeProperty(property, person, scan);
    }

    private void changeProperty(personProperty property, Person person, Scanner scan) {
        scan.nextLine();
        switch (property) {
            case NAME:
                System.out.print("Enter name: > ");
                String name = scan.nextLine();
                person.setName(name);
                break;
            case NUMBER:
                System.out.print("Enter number: > ");
                String number = scan.nextLine();
                person.setPhoneNumber(number);
                if (!person.hasNumber()) {
                    System.out.println("Wrong number format!");
                }
                break;
            case SURNAME:
                System.out.print("Enter surname: > ");
                String surname = scan.nextLine();
                person.setSurname(surname);
                break;
            case GENDER:
                System.out.print("Enter gender: > ");
                String gender = scan.nextLine();
                person.setGender(gender);
                break;
            case BIRTH:
                System.out.print("Enter birth date: > ");
                String birth = scan.nextLine();
                person.setBirthDate(birth);
        }
        System.out.println("The record updated!");
        person.newUpdatedTime();
    }
}
