package gwendal.psm;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.test.ActivityInstrumentationTestCase2;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import gwendal.psm.controllers.ContactGroupController;
import gwendal.psm.stubs.ContactsActivityStub;
import model.Contact;
import model.ContactGroup;

/**
 * Created by gwendal on 19/03/15.
 * Create a new ContactGroup and ad a contact to it.
 */
public class AddContactIntegrationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * MainActivity.
     */
    private MainActivity main;

    /**
     * ContactGroupActivity.
     */
    private ContactGroupActivity cga;

    /**
     * Contact data.
     */
    private Intent data;

    /**
     * ContactsActivity.
     */
    private ContactsActivity contacts;

    /**
     * Constructor.
     */
    public AddContactIntegrationTest() {
        super(MainActivity.class);
    }

    /**
     * Test.
     */
    public void test() {
        this.main = getActivity();
        Button addGroup = (Button) this.main.findViewById(R.id.add_group);
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);
        addGroup.performClick();
        this.cga = (ContactGroupActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(this.cga);
        EditText groupName = (EditText) this.cga.findViewById(R.id.group_name);
        groupName.setText("name");
        Button addContact = (Button) this.cga.findViewById(R.id.add_contact);
        Instrumentation.ActivityMonitor contactsActivityMonitor = getInstrumentation().addMonitor(ContactsActivity.class.getName(), null, false);
        addContact.performClick();
        this.contacts = (ContactsActivity) getInstrumentation().waitForMonitorWithTimeout(contactsActivityMonitor, 5000);
        assertNotNull(this.contacts);
        LinearLayout layout = (LinearLayout) this.contacts.findViewById(R.id.layout);
        if(layout.getChildCount() != 0) {
            Button button = (Button) layout.getChildAt(0);
            button.performClick();
            LinearLayout contactList = (LinearLayout) this.cga.findViewById(R.id.contact_list);
            assertEquals(1, contactList.getChildCount());
        }
        Button okButton = (Button) this.cga.findViewById(R.id.register_group);
        okButton.performClick();
        LinearLayout groupList = (LinearLayout) this.main.findViewById(R.id.group_list);
        assertEquals(1, groupList.getChildCount());
    }

}
