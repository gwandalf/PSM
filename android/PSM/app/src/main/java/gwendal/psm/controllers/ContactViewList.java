package gwendal.psm.controllers;

import java.util.Observable;

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
     * Forces this instance to create and add a view for all the contacts that weren't represented.
     */
    public void forceUpdate() {
        ContactGroup group = getContactGroup();
        for(Contact c : group.getList()) {
            ItemTextView viewItem;
            if(this.modelViewMap.containsKey(c)) {
                viewItem = this.modelViewMap.get(c);
            } else {
                viewItem = new ItemTextView(this.parentContext, c);
                this.modelViewMap.put(c, viewItem);
            }
            this.parentLayout.addView(viewItem.textView, viewItem.params);
        }
    }

    /**
     * Gets the observed Contact Group.
     * @return The observed Contact Group.
     */
    public ContactGroup getContactGroup() {
        return (ContactGroup)this.observed;
    }
}
