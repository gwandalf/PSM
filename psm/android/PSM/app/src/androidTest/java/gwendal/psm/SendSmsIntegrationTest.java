package gwendal.psm;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import java.util.Set;

import gwendal.psm.stubs.SmsActivityStub;
import model.Contact;
import model.ContactGroup;

/**
 * Created by gwendal on 20/03/15.
 * Tests the sending of a sms to a group.
 */
public class SendSmsIntegrationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * ContactGroup.
     */
    private ContactGroup cg;

    /**
     * Contact table.
     */
    private Contact[] cTab;

    /**
     * Constructor.
     */
    public SendSmsIntegrationTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        this.cg = new ContactGroup("group");
        this.cTab = new Contact[3];
        for(int i = 0 ; i < 3 ; i++) {
            this.cTab[i] = new Contact("" + i, "c" + i, "0" + i);
            this.cg.add(this.cTab[i]);
        }
    }

    /**
     * Tests the sending of a sms to a group.
     */
    public void test() {
        Intent data = SmsActivityStub.launchOnGroup(this.cg, getActivity());
        Set<Contact> contacts = ContactFactory.makeContactSet(data);
        assertTrue(this.cg.equals(contacts));
    }
}
