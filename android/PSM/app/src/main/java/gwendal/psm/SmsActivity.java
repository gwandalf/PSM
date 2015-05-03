package gwendal.psm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import gwendal.psm.listeners.SendSmsListener;
import model.ContactGroup;
import model.GroupList;


public class SmsActivity extends Activity {

    /**
     * Group or recipients.
     */
    public ContactGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        this.group = GroupList.INSTANCE.getActive();
        Button sendButton = (Button) findViewById(R.id.send_button);
        EditText smsText = (EditText) findViewById(R.id.sms_text);
        SendSmsListener sendSmsListener = new SendSmsListener(group, smsText);
        sendButton.setOnClickListener(sendSmsListener);
        Log.d("SEND", "create of SmsActivity performed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
