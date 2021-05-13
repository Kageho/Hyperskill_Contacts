package contacts.app.editor;

import contacts.contactTypes.Contact;
import contacts.contactTypes.organization.Organization;
import contacts.contactTypes.organization.organizationProperty;

import java.util.Scanner;

public class OrganizationEditor {
    public void editOrg(Contact contact) {
        Scanner scan = new Scanner(System.in);
        Organization org = (Organization) contact;
        System.out.print("Select a field (name, address, number): > ");
        organizationProperty property;
        try {
            property = organizationProperty.valueOf(scan.next().toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("There is no such field!");
            return;
        }
        changeProperty(property, org, scan);
    }

    private void changeProperty(organizationProperty property, Organization org, Scanner scan) {
        scan.nextLine();
        switch (property) {
            case NAME:
                System.out.print("Enter name: > ");
                String name = scan.nextLine();
                org.setName(name);
                break;
            case ADDRESS:
                System.out.print("Enter address: > ");
                String address = scan.nextLine();
                org.setAddress(address);
                break;
            case NUMBER:
                System.out.print("Enter number: > ");
                String number = scan.nextLine();
                org.setPhoneNumber(number);
                if (!org.hasNumber()) {
                    System.out.println("Wrong number format!");
                }
        }
        org.newUpdatedTime();
        System.out.println("The record updated!");
    }
}
