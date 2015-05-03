package model;

import java.io.Serializable;
import java.util.Observable;

/**
 * Created by gwendal on 01/03/15.
 * Contact.
 */
public class Contact extends Observable implements ModelItem {

    /**
     * Name.
     */
    private String name;

    /**
     * Phone number.
     */
    private String number;

    /**
     * Constructor.
     * @param name Name.
     * @param number Phone number.
     */
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (number != null ? !number.equals(contact.number) : contact.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }

    /**
     * Gets the name.
     * @return the Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the number.
     * @return the number.
     */
    public String getNumber() {
        return this.number;
    }
}
