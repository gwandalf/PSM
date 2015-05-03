package gwendal.psm.controllers;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;


import model.Contact;

/**
 * Created by gwendal on 20/03/15.
 * Factory making Contacts from intents.
 */
public class ContactFactory {

    /**
     * Parent context.
     */
    public Context parentContext;

    /**
     * Constructor.
     * @param parentContext Parent context.
     */
    public ContactFactory(Context parentContext) {
        this.parentContext = parentContext;
    }

    /**
     * Makes a Contact retrieved from the specified Intent.
     * @param data Intent.
     * @return Contact.
     */
    public Contact makeContact(Intent data) {
        Uri contactUri = data.getData();
        Cursor cursor = this.parentContext.getContentResolver().query(contactUri, null, null, null, null);
        cursor.moveToFirst();
        int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int nameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        int hasPhoneColumn = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        String id = cursor.getString(idColumn);
        String hasPhone = cursor.getString(hasPhoneColumn);
        String name = cursor.getString(nameColumn);
        String number = "";
        if(hasPhone.equalsIgnoreCase("1")) {
            Cursor phones = this.parentContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
            phones.moveToFirst();
            int numberColumn = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            number = phones.getString(numberColumn);
        }
        return new Contact(name, number);
    }

}
