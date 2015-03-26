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
     * Contact data.
     */
    private Intent data;

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
     * TODO : for all touch to the UI, have nice functions like "static runOnUIThread(Button b)"
     */
    public void test() {
        setActivityInitialTouchMode(false);
        main = getActivity();
        Log.d("START", "Start test");
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);
        LinearLayout groupList = (LinearLayout) main.findViewById(R.id.group_list);
        assertEquals(0, groupList.getChildCount());
        assertNotNull(main.addGroup);
        Log.d("BEFORE", "before performClick");
        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                main.addGroup.performClick();
            }
        });
        cga = (ContactGroupActivity) getInstrumentation().waitForMonitor(activityMonitor);
        assertNotNull(cga);
        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AddContactListener addContactListener = new AddContactListener(cga, true);
                cga.setAddContactListener(addContactListener);
                EditText groupName = (EditText) cga.findViewById(R.id.group_name);
                groupName.setText("name");
                Button addContact = (Button) cga.findViewById(R.id.add_contact);
                Log.d("MIDDLE", "3rd mark");
                Instrumentation.ActivityMonitor contactsActivityMonitor = getInstrumentation().addMonitor(ContactsActivity.class.getName(), null, false);
                addContact.performClick();
            }
        });
        contacts = (ContactsActivity) getInstrumentation().waitForMonitor(contactsActivityMonitor);
        assertNotNull(contacts);
        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayout layout = (LinearLayout) contacts.findViewById(R.id.layout);
                Log.d("MIDDLE", "Middle test");
                if(layout.getChildCount() != 0) {
                    Button button = (Button) layout.getChildAt(0);
                    button.performClick();
                    LinearLayout contactList = (LinearLayout) cga.findViewById(R.id.contact_list);
                    assertEquals(1, contactList.getChildCount());
                }
                Button okButton = (Button) cga.findViewById(R.id.register_group);
                okButton.performClick();
                assertEquals(1, groupList.getChildCount());
                Instrumentation.ActivityMonitor cgMonitor = getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);
                View groupView = main.contactGroupCtrlList.get(0).getView();
                groupView.performClick();
            }
        });
    }

    public class MainActivityRunnable implements Runnable {

        private MainActivity main;

        public MainActivityRunnable(MainActivity main) {
            this.main = main;
        }

        @Override
        public void run() {






            cga = (ContactGroupActivity) getInstrumentation().waitForMonitorWithTimeout(cgMonitor, 5000);
            assertNotNull(cga);
        }
    }

}
