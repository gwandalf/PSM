package model;

import java.io.Serializable;

/**
 * Created by gwendal on 01/03/15.
 * Contact.
 */
public class Contact implements Serializable {

    /**
     * ID.
     */
    private String id;

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
     * @param id ID.
     * @param name Name.
     * @param number Phone number.
     */
    public Contact(String id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (number != null ? !number.equals(contact.number) : contact.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    /**
     * Gets the ID.
     * @return the ID.
     */
    public String getId() {
        return this.id;
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
