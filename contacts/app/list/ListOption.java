package contacts.app.list;

import contacts.app.edit.EditOption;
import contacts.exceptions.ContactNotFoundException;
import contacts.storage.Storage;

import java.util.Scanner;

// class prints contacts
// and allows to go and edit a contact with chosen id
public class ListOption {
    private final Scanner scan;
    private final Storage storage;
    private final EditOption editOpt;

    public ListOption(Scanner scan, EditOption editOpt, Storage storage) {
        this.scan = scan;
        this.editOpt = editOpt;
        this.storage = storage;
    }

    public void list() {
        boolean flag = true;
        do {
            System.out.print("[list] Enter action ([number], back): > ");
            String input = scan.next();
            if (input.matches("\\d+")) {
                int id = Integer.parseInt(input);
                try {
                    id--;
                    storage.printInfo(id);
                    System.out.println();
                    editOpt.edit(id);
                    flag = false;
                } catch (ContactNotFoundException e) {
                    e.printStackTrace();
                    System.out.println();
                }
            } else if ("back".equals(input)) {
                scan.nextLine();
                flag = false;
            }
        } while (flag);
    }
}
