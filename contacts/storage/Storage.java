package contacts.storage;

import contacts.exceptions.ContactNotFoundException;
import contacts.contactTypes.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
/* contacts will be serialized  */
public class Storage implements Serializable {

    public static final String LIST_IS_EMPTY = "The list is empty!";
    private static final long serialVersionUID = 1L;
    private final List<Contact> contacts;

    public Storage() {
        contacts = new ArrayList<>();
    }

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public int getCount() {
        return contacts.size();
    }

    public void printContactList() {
        IntStream.rangeClosed(1, contacts.size())
                .forEach(e -> System.out.printf("%d. %s\n", e, contacts.get(e - 1).getName()));
    }

    public void printInfo(int id) throws ContactNotFoundException {
        if (isValidIndex(id)) {
            System.out.println(contacts.get(id));
        } else {
            throw new ContactNotFoundException();
        }
    }

    public void delete(int id) throws ContactNotFoundException {
        if (isValidIndex(id)) {
            contacts.remove(id);
            System.out.println("The record has deleted!");
        } else {
            throw new ContactNotFoundException();
        }
    }

    public boolean isValidIndex(int id) {
        return contacts.size() > id && id >= 0;
    }

    public Contact getForEditing(int id) throws ContactNotFoundException {
        if (isValidIndex(id)) {
            return contacts.get(id);
        } else {
            throw new ContactNotFoundException();
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}

