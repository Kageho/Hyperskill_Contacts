package contacts.app.search;

import contacts.app.edit.EditOption;
import contacts.contactTypes.Contact;
import contacts.exceptions.ContactNotFoundException;
import contacts.storage.Storage;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*class for searching info in contacts*/
public class SearchOption {
    // contacts are stored here
    private final Storage storage;
    private final Scanner scan;
    // user can type a number, it's a contact id and then
    // it takes him to editing contact
    private final EditOption editOpt;
    // flag that indicates whether results are found
    private boolean zeroResults;

    public SearchOption(Storage storage, Scanner scan, EditOption editOpt) {
        this.storage = storage;
        this.scan = scan;
        this.editOpt = editOpt;
    }

    public void run(String action) {
        do {
            switch (action) {
                case "again":
                case "search":
                    System.out.print("Enter search query: > ");
                    String query = scan.nextLine();
                    printResults(searchPattern(query));
                    System.out.println();
                    break;
                case "back":
                    return;
                default:
                    if (action.matches(".*\\d+.*")) {
                        try {
                            int id = Integer.parseInt(action);
                            storage.printInfo(--id);
                            System.out.println();
                            editOpt.edit(id);
                        } catch (ContactNotFoundException e) {
                            e.printStackTrace();
                        }
                        return;
                    } else {
                        System.out.println("There is no such option\n");
                        zeroResults = true;
                    }
            }
            System.out.printf("[search] Enter action (%sback, again): > ", !zeroResults ? "[number], " : "");
            action = scan.next();
            scan.nextLine();
        } while (true);
    }

    private void printResults(List<Contact> contacts) {
        zeroResults = contacts.isEmpty();
        System.out.printf("Found %d results:\n", contacts.size());
        IntStream.rangeClosed(1, contacts.size())
                .forEach(id -> System.out.printf("%d. %s\n", id, contacts.get(id - 1).getName()));
    }

    private List<Contact> searchPattern(String pattern) {
        final String finalPattern = getFinalPattern(pattern);
        return storage.getContacts()
                .stream()
                .filter(e -> Arrays.stream(e.getValuesOfFields())
                        .anyMatch(str -> str.matches(finalPattern)))
                .collect(Collectors.toList());
    }

    private String getFinalPattern(String pattern) {
        return String.format("(?i).*%s.*", pattern);
    }
}
