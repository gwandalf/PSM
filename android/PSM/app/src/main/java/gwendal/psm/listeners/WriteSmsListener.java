package gwendal.psm.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.security.acl.Group;

import gwendal.psm.ContactGroupActivity;
import gwendal.psm.SmsActivity;
import model.ContactGroup;

/**
 * Created by gwendal on 12/03/15.
 * Launches the SmsActivity on the selected group.
 */
public class WriteSmsListener extends GroupListener {

    /**
     * Constructor.
     * @param group
     */
    public WriteSmsListener(ContactGroup group) {
        super(group);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), SmsActivity.class);
        v.getContext().startActivity(intent);
    }
}
