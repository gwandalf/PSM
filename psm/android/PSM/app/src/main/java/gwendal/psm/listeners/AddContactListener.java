package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import gwendal.psm.ContactGroupActivity;

/**
 * Created by gwendal on 01/03/15.
 * Add a contact to a contact group.
 */
public class AddContactListener extends LaunchedListener {

    /**
     * ContactGroup activity
     */
    private ContactGroupActivity activity;

    /**
     * Constructor.
     * @param launcher Parent activity.
     */
    public AddContactListener(Context launcher) {
        super(launcher);
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        this.activity.pickContact();
    }
}
