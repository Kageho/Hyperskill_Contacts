package contacts.app;

import contacts.contactTypes.Contact;
import contacts.contactTypes.organization.Organization;
import contacts.contactTypes.person.Person;
import contacts.exceptions.NoSuchContactTypeException;

import java.util.Scanner;

import static contacts.contactTypes.Contact.withoutName;

public class ContactMaker {
    public Contact makeContact() throws NoSuchContactTypeException {
        System.out.print("Enter the type (person, organization): > ");
        Scanner scan = new Scanner(System.in);
        String typeOfContact = scan.next().toLowerCase();
        switch (typeOfContact) {
            case "person":
                return personMaker(scan);
            case "organization":
                return makeOrg(scan);
            default:
                throw new NoSuchContactTypeException("There is no such type of contact!");
        }

    }

    private Person personMaker(Scanner scan) {
        scan.nextLine();
        System.out.print("Enter the name: > ");
        String name = scan.nextLine();
        name = checkName(name);
        System.out.print("Enter the surname: > ");
        String surname = scan.nextLine();
        System.out.print("Enter the birth date: > ");
        String birthDate = scan.nextLine();
        if (birthDate.isBlank()) {
            System.out.println("Bad birth date!");
        }
        System.out.print("Enter the gender (M, F): > ");
        String gender = scan.nextLine();
        if (gender.isBlank()) {
            System.out.println("Bad gender!");
        }
        System.out.print("Enter the number: > ");
        String number = scan.nextLine();
        Person person = new Person.PersonBuilder()
                .setGender(gender)
                .setSurname(surname)
                .setPhone(number)
                .setName(name)
                .setBirthDate(birthDate)
                .build();
        if (!person.hasNumber()) {
            System.out.println("Wrong number format!");
        }
        return person;
    }

    private Organization makeOrg(Scanner scan) {
        scan.nextLine();
        System.out.print("Enter the organization name: > ");
        String name = scan.nextLine();
        name = checkName(name);
        System.out.print("Enter the address: > ");
        String address = scan.nextLine();
        System.out.print("Enter the number: > ");
        String phone = scan.nextLine();
        Organization org = new Organization.OrganizationBuilder()
                .setAddress(address)
                .setName(name)
                .setPhone(phone)
                .build();
        if (!org.hasNumber()) {
            System.out.println("Wrong number format!");
        }
        return org;
    }

    private String checkName(String name) {
        if (name.isBlank()) {
            System.out.println("You haven't provided with any name\nso the default name is defaultName" + withoutName);
            name = "defaultName" + withoutName++;
        }
        return name;
    }
}
