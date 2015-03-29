package gwendal.psm;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import gwendal.psm.listeners.AddContactListener;

import static java.lang.Thread.sleep;

/**
 * Created by gwendal on 19/03/15.
 * Create a new ContactGroup and ad a contact to it.
 */
public class AddContactIntegrationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * MainActivity.
     */
    private static MainActivity main;

    /**
     * ContactGroupActivity.
     */
    private static ContactGroupActivity cga;

    /**
     * ContactsActivity.
     */
    private static ContactsActivity contacts;

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
        setActivityInitialTouchMode(false);
        main = getActivity();
        addMyGroup(this, main);
        Instrumentation.ActivityMonitor cgMonitor = getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);
        View groupView = main.contactGroupCtrlList.get(0).getView();
        Utils.performClick(main, groupView);
        cga = (ContactGroupActivity) getInstrumentation().waitForMonitor(cgMonitor);
        assertNotNull(cga);
        cga.finish();
        main.finish();
    }

    /**
     * Adds a group containing only myself.
     */
    public static void addMyGroup(ActivityInstrumentationTestCase2 testCase, MainActivity main) {
        Instrumentation.ActivityMonitor activityMonitor = testCase.getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);
        LinearLayout groupList = (LinearLayout) main.findViewById(R.id.group_list);
        int count = groupList.getChildCount();
        assertNotNull(main.addGroup);
        Utils.performClick(main, main.addGroup);
        ContactGroupActivity cga = (ContactGroupActivity) testCase.getInstrumentation().waitForMonitor(activityMonitor);
        assertNotNull(cga);
        AddContactListener addContactListener = new AddContactListener(cga, true);
        cga.setAddContactListener(addContactListener);
        final EditText groupName = (EditText) cga.findViewById(R.id.group_name);
        cga.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                groupName.setText("MyGroup");
            }
        });
        Button addContact = (Button) cga.findViewById(R.id.add_contact);
        Instrumentation.ActivityMonitor contactsActivityMonitor = testCase.getInstrumentation().addMonitor(ContactsActivity.class.getName(), null, false);
        Utils.performClick(cga, addContact);
        contacts = (ContactsActivity) testCase.getInstrumentation().waitForMonitor(contactsActivityMonitor);
        assertNotNull(contacts);
        LinearLayout contactList = (LinearLayout) cga.findViewById(R.id.contact_list);
        if(contacts.me != null) {
            Utils.performClick(contacts, contacts.me);
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertEquals(1, contactList.getChildCount());
        }
        Button okButton = (Button) cga.findViewById(R.id.register_group);
        Utils.performClick(cga, okButton);
        cga = null;
        assertEquals(count+1, groupList.getChildCount());
    }

}
