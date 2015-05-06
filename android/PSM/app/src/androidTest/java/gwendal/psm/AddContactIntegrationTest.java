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
        Utils.addMyGroup(this, main);
        Instrumentation.ActivityMonitor cgMonitor = getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);
        View groupView = main.groupListView.getChildAt(0);
        Utils.performClick(main, groupView);
        cga = (ContactGroupActivity) getInstrumentation().waitForMonitor(cgMonitor);
        assertNotNull(cga);
        cga.finish();
        main.finish();
    }

}
