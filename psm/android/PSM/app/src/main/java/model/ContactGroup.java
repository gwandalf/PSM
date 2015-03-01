package model;

import java.io.Serializable;
import java.util.HashSet;

import interfaces.ContactGroupInterface;

/**
 * Created by gwendal on 15/02/15.
 * Contact Group.
 */
public class ContactGroup extends HashSet<Contact> implements ContactGroupInterface, Serializable {

    //Name.
    private String name;

    /**
     * Constructor.
     */
    public ContactGroup() {
        this.name = "(Sans nom)";
    }

    /**
     * Constructor.
     * @param name Contact Group name.
     */
    public ContactGroup(String name) {
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
}
