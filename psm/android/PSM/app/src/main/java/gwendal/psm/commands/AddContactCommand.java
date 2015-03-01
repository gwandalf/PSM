package gwendal.psm.commands;

import android.view.View;

import gwendal.psm.ContactGroupActivity;

/**
 * Created by gwendal on 01/03/15.
 * Add a contact to a contact group.
 */
public class AddContactCommand implements View.OnClickListener {

    /**
     * ContactGroup activity
     */
    private ContactGroupActivity activity;

    /**
     * Constructor.
     * @param activity Parent activity.
     */
    public AddContactCommand(ContactGroupActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        this.activity.pickContact();
    }
}
