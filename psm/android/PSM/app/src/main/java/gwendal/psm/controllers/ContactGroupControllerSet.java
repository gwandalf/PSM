package gwendal.psm.controllers;

import java.util.HashSet;

import model.ContactGroup;

/**
 * Created by gwendal on 17/03/15.
 * Set of ContactGroupControllers.
 */
public class ContactGroupControllerSet extends HashSet<ContactGroupController> {

    /**
     * Constructor.
     */
    public ContactGroupControllerSet() {
        super();
    }

    /**
     * Gets the component which has its model field pointing to the specified ContactGroup.
     * @param group ContactGroup.
     * @return Controller if the group was found, null otherwise.
     */
    public ContactGroupController getControllerOfModel(ContactGroup group) {
        for(ContactGroupController cgc : this) {
            if(cgc.getModel() == group)
                return cgc;
        }
        return null;
    }

}
