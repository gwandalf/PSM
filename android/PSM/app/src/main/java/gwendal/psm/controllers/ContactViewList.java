package gwendal.psm.controllers;

import gwendal.psm.views.ObserverList;
import model.Contact;
import model.ContactGroup;

/**
 * Created by gwendal on 02/05/15.
 * Contact View List.
 */
public class ContactViewList extends ObserverList<Contact> {

    /**
     * Constructor.
     * @param group Observed group.
     */
    public ContactViewList(ContactGroup group) {
        super();
        this.observed = group;
        group.addObserver(this);
    }

    /**
     * Gets the observed Contact Group.
     * @return The observed Contact Group.
     */
    public ContactGroup getContactGroup() {
        return (ContactGroup)this.observed;
    }
}
