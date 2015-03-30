package gwendal.psm.listeners;

import android.view.View;

import gwendal.psm.ContactGroupActivity;
import gwendal.psm.ContactsActivity;

/**
 * Created by gwendal on 01/03/15.
 * Add a contact to a contact group.
 */
public class AddContactListener extends GroupListener {

    /**
     * ContactGroup activity
     */
    private ContactGroupActivity activity;

    /**
     * If it is for a test or not.
     */
    private boolean testFlag;

    /**
     * Constructor.
     * @param cga Parent activity.
     */
    public AddContactListener(ContactGroupActivity cga, boolean testFlag) {
        super(cga);
        this.testFlag = testFlag;
    }

    @Override
    public void onClick(View v) {
        ContactsActivity.launch(this.cga, this.testFlag);
    }
}
