package gwendal.psm;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import gwendal.psm.commands.AddContactCommand;
import gwendal.psm.commands.RegisterContactGroupCommand;
import model.Contact;
import model.ContactGroup;


public class ContactGroupActivity extends Activity {

    private static final String CONTACT_GROUP = "cg";

    /**
     * Observed contact group.
     */
    private ContactGroup observed;

    /**
     * Activity layout.
     */
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_group);
        Bundle data = this.getIntent().getExtras();
        if(data != null)
            this.observed = (ContactGroup)data.get(CONTACT_GROUP);
        else
            this.observed = new ContactGroup();
        this.layout = (LinearLayout) findViewById(R.id.contact_list);
        Button addContact = (Button) findViewById(R.id.add_contact);
        AddContactCommand addContactCommand = new AddContactCommand(this);
        addContact.setOnClickListener(addContactCommand);
        Button createGroup = (Button) findViewById(R.id.register_group);
        EditText et = (EditText) findViewById(R.id.group_name);
        RegisterContactGroupCommand command = new RegisterContactGroupCommand(this, et, this.observed);
        createGroup.setOnClickListener(command);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_group, menu);
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
     * Launch the activity on the specified Contact Group.
     * @param cg ContactGroup to be displayed.
     */
    public static void launchOnContactGroup(ContactGroup cg, Context ctx) {
        Intent intent = new Intent(ctx, ContactGroupActivity.class);
        intent.putExtra(CONTACT_GROUP, cg);
        ctx.startActivity(intent);
    }

    /**
     * Starts the contact activity for contact picking.
     */
    public void pickContact() {
        Intent pickContactIntent = new Intent(
                Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI
        );
        startActivityForResult(pickContactIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
            cursor.moveToFirst();
            int nameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameColumn);
            Contact contact = new Contact("id", name, "number");
            this.observed.add(contact);
            TextView contactView = new TextView(this);
            contactView.setText(contact.getName());
            this.layout.addView(contactView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        }
        else
            DialogFactory.showErrorDialog("Le contact n'a pas pu être ajouté.", this);
    }
}
