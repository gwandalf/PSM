package gwendal.psm.listeners;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import gwendal.psm.ContactsActivity;

/**
 * Created by gwendal on 25/03/15.
 * Gets a contact URI.
 */
public class GetContactUriListener implements View.OnClickListener {

    private Uri contactUri;

    private ContactsActivity contactsActivity;

    public GetContactUriListener(Uri contactUri, ContactsActivity contactsActivity) {
        super();
        this.contactUri = contactUri;
        this.contactsActivity = contactsActivity;
    }

    @Override
    public void onClick(View v) {
        Intent data = new Intent();
        data.setData(this.contactUri);
        this.contactsActivity.setResult(Activity.RESULT_OK, data);
        this.contactsActivity.finish();
    }
}
