package gwendal.psm.listeners;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;

import gwendal.psm.ContactsActivity;

/**
 * Created by gwendal on 01/03/15.
 * Add a contact to a contact group.
 */
public class AddContactListener extends ActivityListener {

    /**
     * If it is for a test or not.
     */
    private boolean testFlag;

    /**
     * Constructor.
     * @param activity Parent activity.
     * @param testFlag Indicates if it is for a test or not.
     */
    public AddContactListener(Activity activity, boolean testFlag) {
        super(activity);
        this.testFlag = testFlag;
    }

    @Override
    public void onClick(View v) {
        if(this.testFlag) {
            Intent pickContactIntent = new Intent(this.activity, ContactsActivity.class);
            this.activity.startActivityForResult(pickContactIntent, 1);
        } else {
            Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            this.activity.startActivityForResult(pickContactIntent, 1);
        }
    }
}
