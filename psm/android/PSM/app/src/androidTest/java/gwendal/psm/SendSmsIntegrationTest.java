package gwendal.psm;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
     * Constructor.
     */
    public SendSmsIntegrationTest() {
        super(MainActivity.class);
    }

    /**
     * MainActivity.
     */
    public static MainActivity main;

    /**
     * Tests the sending of a sms to a group.
     */
    public void test() {
        setActivityInitialTouchMode(false);
        main = getActivity();
        LinearLayout groupList = (LinearLayout) main.findViewById(R.id.group_list);
        View myGroup = getMyGroup(groupList);
        if(myGroup == null)
            AddContactIntegrationTest.addMyGroup(this, main);
        myGroup = getMyGroup(groupList);
        Utils.performClick(main, myGroup);
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ContactGroupActivity.class.getName(), null, false);
        assertNotNull(main.addGroup);
        Utils.performClick(main, main.addGroup);
        ContactGroupActivity cga = (ContactGroupActivity) getInstrumentation().waitForMonitor(activityMonitor);
        assertNotNull(cga);
        Instrumentation.ActivityMonitor smsMonitor = getInstrumentation().addMonitor(SmsActivity.class.getName(), null, false);
        Button writeSms = (Button) cga.findViewById(R.id.write_sms);
        Utils.performClick(cga, writeSms);
        SmsActivity sms = (SmsActivity) getInstrumentation().waitForMonitor(smsMonitor);
        assertNotNull(sms);
        final EditText et = (EditText) sms.findViewById(R.id.sms_text);
        sms.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                et.setText("SMS de test. Vous pouvez répondre si vous en avez marre d'en recevoir...");
            }
        });
        Button send = (Button) sms.findViewById(R.id.send_button);
        Utils.performClick(sms, send);
        Log.d("SEND", "click performed");
        sms.finish();
        cga.finish();
        main.finish();
    }

    /**
     * Gets the ContactGroup called "MyGroup".
     * @param groupList ContactGroup view list.
     * @return The ContactGroup called "MyGroup".
     */
    private View getMyGroup(LinearLayout groupList) {
        View myGroup = null;
        for(int i = 0 ; i < groupList.getChildCount() ; i++) {
            TextView tv = (TextView)groupList.getChildAt(i);
            if(tv.getText().equals("MyGroup"))
                myGroup = tv;
        }
        return myGroup;
    }
}
