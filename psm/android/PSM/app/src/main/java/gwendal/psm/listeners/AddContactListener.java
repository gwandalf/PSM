package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import gwendal.psm.ContactGroupActivity;
import gwendal.psm.stubs.ContactsActivityStub;

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
     * Constructor.
     * @param cga Parent activity.
     */
    public AddContactListener(ContactGroupActivity cga) {
        super(cga);
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        ContactsActivityStub.launch(this.activity);
    }
}
