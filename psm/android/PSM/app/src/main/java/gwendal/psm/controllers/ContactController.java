package gwendal.psm.controllers;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import model.Contact;

/**
 * Created by gwendal on 12/03/15.
 * Makes the link between the model and the view.
 */
public class ContactController {

    /**
     * Model.
     */
    private Contact model;

    /**
     * View.
     */
    private TextView view;

    /**
     * Constructor. Initializes the model with the specified data.
     * @param parent Parent Activity.
     * @param data Data retrieved from the contacts app.
     */
    public ContactController(Context parent, Intent data) {
        Uri contactUri = data.getData();
        Cursor cursor = parent.getContentResolver().query(contactUri, null, null, null, null);
        cursor.moveToFirst();
        int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int nameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        int hasPhoneColumn = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        String id = cursor.getString(idColumn);
        String hasPhone = cursor.getString(hasPhoneColumn);
        String name = cursor.getString(nameColumn);
        String number = "";
        if(hasPhone.equalsIgnoreCase("1")) {
            Cursor phones = parent.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
            phones.moveToFirst();
            int numberColumn = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            number = phones.getString(numberColumn);
        }
        this.model = new Contact(name, number);
        this.view = new TextView(parent);
        this.view.setText(this.model.getName());
    }

    /**
     * Constructor.
     * @param parent
     * @param model
     */
    public ContactController(Context parent, Contact model) {
        this.model = model;
        this.view = new TextView(parent);
        this.view.setText(this.model.getName());
    }

    public Contact getModel() {
        return model;
    }

    public void setModel(Contact model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }
}
