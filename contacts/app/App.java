package contacts.app;


import contacts.app.editor.OrganizationEditor;
import contacts.app.editor.PersonEditor;
import contacts.exceptions.ContactNotFoundException;
import contacts.contactTypes.Contact;
import contacts.exceptions.NoSuchContactTypeException;
import contacts.storage.Storage;
import contacts.typeOfOperations.Operations;

import java.util.Scanner;

public class App {
    private final Storage storage;
    private final Scanner scan;
    private final PersonEditor personEditor;
    private final OrganizationEditor orgEditor;
    private final ContactMaker contactMaker;

    public App() {
        storage = new Storage();
        scan = new Scanner(System.in);
        personEditor = new PersonEditor();
        orgEditor = new OrganizationEditor();
        contactMaker = new ContactMaker();
    }

    public void run() {
        boolean flag = true;
        Operations typeOfOperation;
        do {
            printMenu();
            try {
                String operation = scan.nextLine().trim().toUpperCase();
                typeOfOperation = Operations.valueOf(operation);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("There is no such operation!\n");
                continue;
            }
            switch (typeOfOperation) {
                case EXIT:
                    flag = false;
                    break;
                case ADD:
                    addContact();
                    break;
                case INFO:
                    showInfo();
                    break;
                case COUNT:
                    printCount();
                    break;
                case REMOVE:
                    remove();
                    break;
                case EDIT:
                    edit();
            }
            System.out.println();
        } while (flag);

    }

    private void printMenu() {
        System.out.print("Enter action (add, remove, edit, count, info, exit): > ");
    }

    private void showInfo() {
        if (storage.getCount() == 0) {
            System.out.println("there is nothing in the list");
        } else {
            storage.printContactList();
            System.out.print("Enter index to show info: > ");
            int id = scan.nextInt();
            scan.nextLine();
            try {
                storage.printInfo(id - 1);
            } catch (ContactNotFoundException e) {
                e.printStackTrace();
                System.out.println();
            }
        }
    }

    private void printCount() {
        System.out.printf("The Phone Book has %d records.\n", storage.getCount());
    }

    private void remove() {
        if (storage.getCount() != 0) {
            try {
                storage.printContactList();
                System.out.print("Select a record: > ");
                storage.remove(scan.nextInt());
                System.out.println("The record removed!");
            } catch (ContactNotFoundException e) {
                e.printStackTrace();
                System.out.println();
            }
            scan.nextLine();
        } else {
            System.out.println("No records to remove!");
        }
    }

    private void addContact() {
        try {
            storage.add(contactMaker.makeContact());
        } catch (NoSuchContactTypeException e) {
            e.printStackTrace();
        }
        System.out.println("The record added.");
    }

    private void edit() {
        if (storage.getCount() == 0) {
            System.out.println("No records to edit");
        } else {
            storage.printContactList();
            System.out.print("Select a record: > ");
            int id = scan.nextInt();
            scan.nextLine();
            try {
                Contact contact = storage.getForEditing(id);
                if (contact.isPerson()) {
                    personEditor.editPerson(contact);
                } else {
                    orgEditor.editOrg(contact);
                }
            } catch (ContactNotFoundException e) {
                e.printStackTrace();
                System.out.println();
            }
        }
    }

}
