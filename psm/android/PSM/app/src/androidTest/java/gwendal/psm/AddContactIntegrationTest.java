package gwendal.psm;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.test.ActivityInstrumentationTestCase2;

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
        ContactGroupController cg = new ContactGroupController(this.main);
        this.main.contactGroupCtrlList.add(cg);
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);
        ContactGroupActivity.launchOnContactGroup(cg.getModel(), this.main, this.main.contactGroupCtrlList.size()-1);
        this.cga = (ContactGroupActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        this.cga.getObserved().setName("name");
        //ContactsActivityStub.launch(this.cga);
        Contact contact = new Contact("0", "contact", "0");
        this.cga.getObserved().add(contact);
        Instrumentation.ActivityMonitor finishMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
        Intent data = new Intent();
        data.putExtra(ContactGroupActivity.CONTACT_GROUP, this.cga.getObserved());
        if (this.cga.getParent() != null) {
            this.cga.getParent().setResult(Activity.RESULT_OK, data);
        }
        this.cga.finish();
        getInstrumentation().waitForMonitorWithTimeout(finishMonitor, 5000);
        ContactGroup group = cg.getModel();
        assertNotNull(group);
        assertEquals("name", group.getName());
        assertEquals(1, group.size());
    }

}
