package model;

import interfaces.ContactGroupInterface;

/**
 * Created by gwendal on 15/02/15.
 * Contact Group.
 */
public class ContactGroup implements ContactGroupInterface {

    //Name.
    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
