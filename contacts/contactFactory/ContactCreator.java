package contacts.contactFactory;

import contacts.contactTypes.Contact;
import contacts.contactTypes.organization.Organization;
import contacts.contactTypes.person.Person;
import contacts.exceptions.NoSuchContactTypeException;

import java.util.Scanner;

// the class is used as simple factory idiom
public class ContactCreator {

    public Contact createContact() throws NoSuchContactTypeException {
        System.out.print("Enter the type (person, organization): > ");
        Scanner scan = new Scanner(System.in);
        typeOfContact type;
        try {
            type = typeOfContact.valueOf(scan.nextLine().toUpperCase());
        } catch (Exception e) {
            throw new NoSuchContactTypeException();
        }
        Contact contact = null;
        switch (type) {
            case PERSON:
                contact = createPersonContact(scan);
                break;
            case ORGANIZATION:
                contact = createOrgContact(scan);
        }
        return contact;
    }

    private Contact createOrgContact(Scanner scan) {
        Organization.OrganizationBuilder orgBuilder = new Organization.OrganizationBuilder();
        System.out.print("Enter the organization name: > ");
        String name = scan.nextLine();
        orgBuilder.setName(name);

        System.out.print("Enter the address: > ");
        String address = scan.nextLine();

        System.out.print("Enter the number: > ");
        String phone = scan.nextLine();

        Organization org = orgBuilder.setPhone(phone)
                .setAddress(address)
                .build();
        if (!org.hasNumber()) {
            System.out.println("Wrong number format!");
        }
        return org;
    }

    private Contact createPersonContact(Scanner scan) {

        Person.PersonBuilder builder = new Person.PersonBuilder();
        System.out.print("Enter the name: > ");
        String name = scan.nextLine();
        builder.setName(name);

        System.out.print("Enter the surname: > ");
        String surname = scan.nextLine();
        builder.setSurname(surname);

        System.out.print("Enter the birth date: > ");
        String birthDate = scan.nextLine();
        builder.setBirthDate(birthDate);
      /*  if (birthDate.isBlank()) {
            System.out.println("Bad birth date!");
        }*/

        System.out.print("Enter the gender (M, F): > ");
        String gender = scan.nextLine();
        if (gender.isBlank()) {
            System.out.println("Bad gender!");
        }
        builder.setGender(gender);

        System.out.print("Enter the number: > ");
        String number = scan.nextLine();
        builder.setPhone(number);

        Person person = builder.build();
        if (!person.hasNumber()) {
            System.out.println("Wrong number format!");
        }
        return person;
    }
}
