package contacts.storage;

import contacts.person.Person;
import contacts.personException.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Person> personList;

    public Storage() {
        personList = new ArrayList<>();
    }

    public void add(Person person) {
        personList.add(person);
    }

    public void remove(int id) throws PersonNotFoundException {
        if (id > 0 && id <= personList.size()) {
            personList.remove(id - 1);
        } else {
            throw new PersonNotFoundException("There is no such person!");
        }
    }

    public int getCount() {
        return personList.size();
    }

    public void printPersonList() {
        int i = 1;
        for (var e : personList) {
            System.out.printf("%d. %s\n", i++, e.toString());
        }
    }

    public Person getForEditing(int id) throws PersonNotFoundException {
        if (id > 0 && id <= personList.size()) {
            return personList.get(id - 1);
        } else {
            throw new PersonNotFoundException("There is no such person");
        }
    }

}

