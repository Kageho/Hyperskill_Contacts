package contacts.storage;

import contacts.exceptions.ContactNotFoundException;
import contacts.contactTypes.Contact;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final String NO_SUCH_CONTACT = "There is no such contact!";
    private List<Contact> contacts;

    public Storage() {
        contacts = new ArrayList<>();
    }

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void remove(int id) throws ContactNotFoundException {
        if (id > 0 && id <= contacts.size()) {
            contacts.remove(id - 1);
        } else {
            throw new ContactNotFoundException(NO_SUCH_CONTACT);
        }
    }

    public int getCount() {
        return contacts.size();
    }

    public void printContactList() {
        int i = 1;
        for (var e : contacts) {
            System.out.printf("%d. %s\n", i++, e.getName());
        }
    }

    public void printInfo(int id) throws ContactNotFoundException {
        if (id >= 0 && id < contacts.size()) {
            System.out.println(contacts.get(id));
        } else {
            throw new ContactNotFoundException(NO_SUCH_CONTACT);
        }
    }

    public Contact getForEditing(int id) throws ContactNotFoundException {
        if (id > 0 && id <= contacts.size()) {
            return contacts.get(id - 1);
        } else {
            throw new ContactNotFoundException(NO_SUCH_CONTACT);
        }
    }

}

