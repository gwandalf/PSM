package gwendal.psm;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import gwendal.psm.listeners.AddContactListener;

import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by gwendal on 27/03/15.
 * Utility functions for tests.
 */
public class Utils {

    /**
     * Contacts activity.
     */
    public static ContactsActivity contacts;

    /**
     * Performs a click from the UI Thread.
     * @param activity Running activity.
     * @param v View to be clicked.
     */
    public static void performClick(Activity activity, View v) {
        activity.runOnUiThread(new ViewRunnable(v) {
            @Override
            public void run() {
                v.performClick();
            }
        });
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

    /**
     * Runnable containing a view.
     */
    public abstract static class ViewRunnable implements Runnable {

        /**
         * Touched view.
         */
        protected View v;

        /**
         * Constructor.
         * @param v Touched view.
         */
        public ViewRunnable(View v) {
            this.v = v;
        }
    }

}
