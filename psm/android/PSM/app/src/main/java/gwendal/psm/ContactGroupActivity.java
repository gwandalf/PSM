package gwendal.psm;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashSet;

import gwendal.psm.listeners.AddContactListener;
import gwendal.psm.listeners.SaveGroupListener;
import gwendal.psm.controllers.ContactController;
import model.Contact;
import model.ContactGroup;


public class ContactGroupActivity extends Activity {

    public static final String CONTACT_GROUP = "cg";

    /**
     * Observed contact group.
     */
    private ContactGroup observed;

    /**
     * Activity layout.
     */
    private LinearLayout layout;

    /**
     * ContactController set.
     */
    private HashSet<ContactController> contactCtrlSet;

    /**
     * Group name field.
     */
    private EditText groupName;

    /**
     * Button used to add a contact.
     */
    private Button addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_group);
        Bundle data = this.getIntent().getExtras();
        if(data != null) {
            Log.d("DATA", "data != null");
            this.observed = (ContactGroup) data.get(CONTACT_GROUP);
        } else {
            Log.d("DATA", "data == null");
            this.observed = MainActivity.factory.makeContactGroup("");
        }
        this.layout = (LinearLayout) findViewById(R.id.contact_list);
        this.contactCtrlSet = new HashSet<ContactController>();
        for(Contact c : this.observed) {
            ContactController ctrl = new ContactController(this, c);
            this.contactCtrlSet.add(ctrl);
            this.layout.addView(ctrl.getView());
        }
        this.addContact = (Button) findViewById(R.id.add_contact);
        AddContactListener addContactListener = new AddContactListener(this, false);
        addContact.setOnClickListener(addContactListener);
        Button okButton = (Button) findViewById(R.id.register_group);
        this.groupName = (EditText) findViewById(R.id.group_name);
        this.groupName.setText(this.observed.getName());
        SaveGroupListener ok = new SaveGroupListener(this);
        okButton.setOnClickListener(ok);
    }

    /**
     * Sets the addContact listener.
     * @param listener AddContactListener.
     */
    public void setAddContactListener(AddContactListener listener) {
        this.addContact.setOnClickListener(listener);
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
     * @param activity Launcher activity.
     */
    public static void launchOnContactGroup(ContactGroup cg, Activity activity, int requestCode) {
        Intent intent = new Intent(activity, ContactGroupActivity.class);
        if(cg != null)
            intent.putExtra(CONTACT_GROUP, cg);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            ContactController ctrl = new ContactController(this, data);
            this.observed.add(ctrl.getModel());
            this.layout.addView(ctrl.getView(), ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        }
        else
            DialogFactory.showErrorDialog("Le contact n'a pas pu être ajouté.", this);
    }

    /**
     * Gets the groupName.
     * @return groupName.
     */
    public EditText getGroupName() { return this.groupName; }

    /**
     * Gets the observed group.
     * @return the observed group.
     */
    public ContactGroup getObserved() {
        return observed;
    }
}
