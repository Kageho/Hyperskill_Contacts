package contacts.app;


import contacts.app.edit.EditOption;
import contacts.app.list.ListOption;
import contacts.app.search.SearchOption;
import contacts.contactFactory.ContactCreator;
import contacts.exceptions.NoSuchContactTypeException;
import contacts.storage.SerializationUtils;
import contacts.storage.Storage;
import contacts.typeOfOperations.Operations;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    private final Storage storage;
    private final Scanner scan;
    private final ContactCreator contactCreator;
    private final EditOption editOption;
    private final ListOption listOption;
    private final SearchOption searchOption;
    private static final String mainMenu = "[menu] Enter action (add, list, search, count, exit): > ";
    private String fileName;

    public App(String[] args) {
        storage = readContacts(args);
        scan = new Scanner(System.in);
        contactCreator = new ContactCreator();
        editOption = new EditOption(storage, scan);
        listOption = new ListOption(scan, editOption, storage);
        searchOption = new SearchOption(storage, scan, editOption);
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
                    // saving contacts
                    writeContacts();
                    break;
                case ADD:
                    addContact();
                    break;
                case COUNT:
                    printCount();
                    break;
                case LIST:
                    if (storage.getCount() > 0) {
                        storage.printContactList();
                        listOption.list();
                    } else {
                        System.out.println(Storage.LIST_IS_EMPTY);
                    }
                    break;
                case SEARCH:
                    if (storage.getCount() != 0) {
                        searchOption.run("search");
                    } else {
                        System.out.println(Storage.LIST_IS_EMPTY);
                    }
            }
            System.out.println();
        } while (flag);

    }

    private void printMenu() {
        System.out.print(mainMenu);
    }


    private void printCount() {
        System.out.printf("The Phone Book has %d records.\n", storage.getCount());
    }

    private void addContact() {
        try {
            storage.add(contactCreator.createContact());
            System.out.println("The record added.");
        } catch (NoSuchContactTypeException e) {
            e.printStackTrace();
        }
    }

    // method reads contacts from file
    // if there is no file
    //  method creates an empty storage with contacts
    // and initialize default file name
    // for saving contacts

    private Storage readContacts(String[] args) {
        Storage storage = null;
        if (args.length > 0) {
            try {
                System.out.println("open " + args[0]);
                storage = (Storage) SerializationUtils.deserialize(args[0]);
                fileName = args[0];
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            storage = new Storage();
            fileName = "contacts";
        }
        return storage;
    }

    private void writeContacts() {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            SerializationUtils.serialize(storage, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
