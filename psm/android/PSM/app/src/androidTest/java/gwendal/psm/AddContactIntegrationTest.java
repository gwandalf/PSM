package gwendal.psm;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.test.ActivityInstrumentationTestCase2;

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
        ContactGroup cg = new ContactGroup();
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);
        ContactGroupActivity.launchOnContactGroup(cg, this.main);
        this.cga = (ContactGroupActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        this.cga.getObserved().setName("name");
        assertEquals("name", cg.getName());
        ContactsActivityStub.launch(this.cga);
        Contact contact = new Contact("0", "contact", "0");
        cg.add(contact);
        Instrumentation.ActivityMonitor finishMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
        this.cga.finish();
        getInstrumentation().waitForMonitorWithTimeout(finishMonitor, 5000);
        assertTrue(this.main.contactGroupCtrlSet.contains(cg));
    }

}
