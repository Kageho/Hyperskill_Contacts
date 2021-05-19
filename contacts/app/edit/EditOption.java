package contacts.app.edit;

import contacts.contactTypes.Contact;
import contacts.exceptions.ContactNotFoundException;
import contacts.storage.Storage;

import java.util.List;
import java.util.Scanner;

// class allows edit contact's field
public class EditOption {
    private final Storage storage;
    private final Scanner scan;

    public EditOption(Storage storage, Scanner scan) {
        this.storage = storage;
        this.scan = scan;
    }

    public void edit(int id) throws ContactNotFoundException {
        if (!storage.isValidIndex(id)) {
            throw new ContactNotFoundException();
        }
        System.out.print("[record] Enter action (edit, delete, menu): > ");
        String action = scan.next().toLowerCase();
        scan.nextLine();
        switch (action) {
            case "edit":
                try {
                    Contact contact = storage.getForEditing(id);
                    List<String> fields = contact.getNameOfFields();
                    System.out.print("Select a field (");
                    for (int i = 0; i < fields.size(); i++) {
                        System.out.printf("%s%s", fields.get(i), fields.size() - 1 == i ? "): > " : ", ");
                    }
                    String fieldToEdit = scan.next().toLowerCase();
                    scan.nextLine();
                    if (fields.contains(fieldToEdit)) {
                        System.out.printf("Enter %s: > ", fieldToEdit);
                        String newValue = scan.nextLine();
                        contact.editField(fieldToEdit, newValue);
                    } else {
                        System.out.println("There is no such filed");
                    }

                } catch (ContactNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    storage.delete(id);
                } catch (ContactNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "menu":
                break;
            default:
                System.out.println("There is no such option!");
        }
    }
}
