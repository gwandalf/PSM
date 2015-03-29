package gwendal.psm;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import gwendal.psm.listeners.GetContactUriListener;


public class ContactsActivity extends Activity {

    /**
     * Add myself.
     */
    public Button me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while(cur.moveToNext()) {
            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Uri contactUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, id);
            Button button = new Button(this);
            if(name.equals("Moi"))
                this.me = button;
            GetContactUriListener getContact = new GetContactUriListener(contactUri, this);
            button.setOnClickListener(getContact);
            layout.addView(button);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Launches the native Contacts Activity.
     * @param launcher Launcher.
     */
    public static void launch(Activity launcher) {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        launcher.startActivityForResult(pickContactIntent, 1);
    }

    /**
     * Launches this activity if testFlag is set to true, launches the native Contacts Activity otherwise.
     * @param launcher Launcher.
     * @param testFlag if testFlag is set to true, launches the native Contacts Activity otherwise.
     */
    public static void launch(Activity launcher, boolean testFlag) {
        if(testFlag) {
            Intent pickContactIntent = new Intent(launcher, ContactsActivity.class);
            launcher.startActivityForResult(pickContactIntent, 1);
        } else
            launch(launcher);
    }
}
