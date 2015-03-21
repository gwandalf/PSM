package gwendal.psm.stubs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;

/**
 * Created by gwendal on 12/03/15.
 * Contacts activity delegate.
 */
public class ContactsActivityStub {

    public static void launch(Activity launcher) {
        Intent pickContactIntent = new Intent(
                Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI
        );
        launcher.startActivityForResult(pickContactIntent, 1);
    }
}
