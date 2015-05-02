package gwendal.psm.controllers;

import model.ContactGroup;

/**
 * Created by gwendal on 02/05/15.
 */
public class ContactViewList {

    /**
     * Observed group.
     */
    public ContactGroup group;

    /**
     * Constructor.
     * @param group Observed group.
     */
    public ContactViewList(ContactGroup group) {
        super();
        this.group = group;
    }
}
