package gwendal.psm.listeners;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import gwendal.psm.DialogFactory;
import model.Contact;
import model.ContactGroup;

/**
 * Created by gwendal on 29/03/15.
 */
public class SendSmsListener implements View.OnClickListener {

    /**
     * Group of recipients.
     */
    private ContactGroup group;

    /**
     * Sms text.
     */
    private EditText smsText;

    /**
     * Constructor.
     * @param group ContactGroup of recipients.
     * @param smsText SMS content.
     */
    public SendSmsListener(ContactGroup group, EditText smsText) {
        this.group = group;
        this.smsText = smsText;
    }

    @Override
    public void onClick(View v) {
        SmsManager manager = SmsManager.getDefault();
        PendingIntent sentPI = null;//PendingIntent.getBroadcast(v.getContext(), 0, new Intent("SMS_SENT"), 0);
        PendingIntent deliveredPI = null;//PendingIntent.getBroadcast(v.getContext(), 0, new Intent("SMS_DELIVERED"), 0);
        for(Contact c : this.group) {
            if(!c.getNumber().equals("")) {
                manager.sendTextMessage(c.getNumber(), null, this.smsText.getText().toString(), sentPI, deliveredPI);
            }
        }
        DialogFactory.showInformationDialog("SMS envoyé à tous les membres du groupe " + this.group.getName() + ".", v.getContext());
    }
}
