package gwendal.psm;

import android.app.Instrumentation;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gwendal.psm.controllers.ContactGroupController;
import gwendal.psm.controllers.ContactGroupControllerList;
import gwendal.psm.listeners.OpenGroupListener;
import model.ContactGroup;

/**
 * Created by gwendal on 13/03/15.
 *
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * MainActivity instance.
     */
    private MainActivity main;

    /**
     * Label of a group.
     */
    private TextView groupLabel;

    /**
     * Constructor.
     * @param activityClass Activity Under test.
     */
    public MainActivityTest(Class activityClass) {
        super(activityClass);
        this.main = getActivity();
        this.main.contactGroupCtrlList = new ContactGroupControllerList();
        ContactGroup cg = new ContactGroup();
        ContactGroupController ctrl = new ContactGroupController(cg, this.main);
        this.groupLabel = (TextView) ctrl.getView();
        OpenGroupListener open = new OpenGroupListener(this.main, ctrl);
        this.groupLabel.setOnClickListener(open);
    }

    /**
     * Constructor.
     */
    public MainActivityTest() {
        super(MainActivity.class);

    }

    public void testOpenNextActivity() {
        this.main = getActivity();
        this.main.contactGroupCtrlList = new ContactGroupControllerList();
        ContactGroup cg = new ContactGroup();
        ContactGroupController ctrl = new ContactGroupController(cg, this.main);
        this.groupLabel = (TextView) ctrl.getView();
        OpenGroupListener open = new OpenGroupListener(this.main, ctrl);
        this.groupLabel.setOnClickListener(open);
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);

        this.groupLabel.performClick();

        ContactGroupActivity cga = (ContactGroupActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(cga);
        assertEquals(cg, cga.getObserved());
        //assertEquals(cg, this.main.inModif);
        cga.finish();
    }
}
