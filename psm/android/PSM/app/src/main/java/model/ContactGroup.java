package model;

import java.io.Serializable;

import interfaces.ContactGroupInterface;

/**
 * Created by gwendal on 15/02/15.
 * Contact Group.
 */
public class ContactGroup implements ContactGroupInterface, Serializable {

    //Name.
    private String name;

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
