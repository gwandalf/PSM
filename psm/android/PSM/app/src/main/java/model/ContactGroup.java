package model;

import java.io.Serializable;
import java.util.HashSet;

import interfaces.ContactGroupInterface;

/**
 * Created by gwendal on 15/02/15.
 * Contact Group.
 */
public class ContactGroup extends HashSet<Contact> implements ContactGroupInterface, Serializable {

    /**
     * Id.
     */
    private int id;

    //Name.
    private String name;

    /**
     * Constructor.
     */
    public ContactGroup(int id) {
        this.id = 0;
        this.name = "(Sans nom)";
    }

    /**
     * Constructor.
     * @param id Id.
     * @param name Name.
     */
    public ContactGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the id.
     * @return Id.
     */
    public int getId() {
        return id;
    }
}
