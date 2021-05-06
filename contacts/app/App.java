package contacts.app;


import contacts.person.Person;
import contacts.personException.PersonNotFoundException;
import contacts.personProperties.Property;
import contacts.storage.Storage;
import contacts.typeOfOperations.Operations;

import java.util.Scanner;

public class App {
    private final Storage storage;
    private final Scanner scan;

    public App() {
        storage = new Storage();
        scan = new Scanner(System.in);
    }

    public void run() {
        boolean flag = true;
        Operations typeOfOperation;
        do {
            printMenu();
            try {
                typeOfOperation = Operations.valueOf(scan.next().toUpperCase());
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("There is no such operation!");
                continue;
            }
            switch (typeOfOperation) {
                case EXIT:
                    flag = false;
                    break;
                case ADD:
                    addPerson();
                    break;
                case LIST:
                    printList();
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
        } while (flag);

    }

    private void printMenu() {
        System.out.print("Enter action (add, remove, edit, count, list, exit): > ");
    }

    private void printList() {
        if (storage.getCount() == 0) {
            System.out.println("there is nothing in the list");
        } else {
            storage.printPersonList();
        }
    }

    private void printCount() {
        System.out.printf("The Phone Book has %d records.\n", storage.getCount());
    }

    private void remove() {
        if (storage.getCount() != 0) {
            try {
                storage.printPersonList();
                System.out.print("Select a record: > ");
                storage.remove(scan.nextInt());
                System.out.println("The record removed!");
            } catch (PersonNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No records to remove!");
        }
    }

    private void addPerson() {
        System.out.print("Enter the name: > ");
        String fistName = scan.next();
        System.out.print("Enter the surname: > ");
        String lastName = scan.next();
        scan.nextLine();
        System.out.print("Enter the number: > ");
        String number = scan.nextLine().trim();
        Person person = new Person.PersonBuilder()
                .setFirstName(fistName)
                .setLastName(lastName)
                .setPhoneNumber(number)
                .build();
        if (!person.hasNumber()) {
            System.out.println("Wrong number format!");
        }
        storage.add(person);
        System.out.println("The record added.");
    }

    private void edit() {
        if (storage.getCount() == 0) {
            System.out.println("No records to edit!");
        } else {
            storage.printPersonList();
            System.out.print("Select a record: > ");
            int id = scan.nextInt();
            try {
                Person person = storage.getForEditing(id);

                System.out.print("Select a field (name, surname, number): > ");
                Property property;
                try {
                    property = Property.valueOf(scan.next().toUpperCase());
                } catch (IllegalArgumentException illegalArgumentException) {
                    System.out.println("There is no such field!");
                    return;
                }
                changeProperty(property, person);
            } catch (PersonNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void changeProperty(Property property, Person person) {
        switch (property) {
            case NAME:
                System.out.print("Enter name: > ");
                String name = scan.next();
                person.setFirstName(name);
                break;
            case NUMBER:
                scan.nextLine();
                System.out.print("Enter number: > ");
                String number = scan.nextLine();
                person.setPhoneNumber(number);
                if (!person.hasNumber()) {
                    System.out.println("Wrong number format!");
                }
                break;
            case SURNAME:
                System.out.print("Enter surname: > ");
                String surname = scan.next();
                person.setLastName(surname);
        }
        System.out.println("The record updated!");
    }
}
