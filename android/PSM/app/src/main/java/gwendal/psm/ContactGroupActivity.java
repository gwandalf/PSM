package gwendal.psm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import gwendal.psm.controllers.ContactFactory;
import gwendal.psm.controllers.ContactViewList;
import gwendal.psm.controllers.GroupViewList;
import gwendal.psm.listeners.AddContactListener;
import gwendal.psm.listeners.ExitContactGroupListener;
import gwendal.psm.listeners.WriteSmsListener;
import model.Contact;


public class ContactGroupActivity extends Activity {

    /**
     * Group name field.
     */
    private EditText groupName;

    /**
     * Button used to add a contact.
     */
    private Button addContact;

    /**
     * Button for saving the group.
     */
    private Button okButton;

    /**
     * Contact View List.
     */
    private ContactViewList contactViewList;

    /**
     * Contact factory.
     */
    private ContactFactory contactFactory;

    /**
     * Layout containing the Contact list.
     */
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_group);
        this.contactViewList = GroupViewList.INSTANCE.active;
        this.layout = (LinearLayout) findViewById(R.id.contact_list);
        this.contactViewList.parentContext = this;
        this.contactViewList.parentLayout = layout;
        this.contactViewList.forceUpdate();
        this.contactFactory = new ContactFactory(this);
        this.addContact = (Button) findViewById(R.id.add_contact);
        AddContactListener addContactListener = new AddContactListener(this, false);
        addContact.setOnClickListener(addContactListener);
        this.okButton = (Button) findViewById(R.id.register_group);
        this.groupName = (EditText) findViewById(R.id.group_name);
        this.groupName.setText(this.contactViewList.getContactGroup().getName());
        ExitContactGroupListener ok = new ExitContactGroupListener(this);
        okButton.setOnClickListener(ok);
        Button writeButton = (Button) findViewById(R.id.write_sms);
        WriteSmsListener writeSmsListener = new WriteSmsListener(this.contactViewList.getContactGroup());
        writeButton.setOnClickListener(writeSmsListener);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Contact contact = this.contactFactory.makeContact(data);
            this.contactViewList.getContactGroup().add(contact);
        }
        else
            DialogFactory.showErrorDialog("Le contact n'a pas pu être ajouté.", this);
    }

    @Override
    public void finish() {
        this.contactViewList.getContactGroup().setName(this.groupName.getText().toString());
        for(int i = 0 ; i < this.layout.getChildCount() ; i++) {
            this.layout.removeView(this.layout.getChildAt(i));
        }
        super.finish();
    }

    /**
     * Gets the groupName.
     * @return groupName.
     */
    public EditText getGroupName() { return this.groupName; }
}
