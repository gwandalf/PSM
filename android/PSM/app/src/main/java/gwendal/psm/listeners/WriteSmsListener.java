package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import java.security.acl.Group;

import gwendal.psm.ContactGroupActivity;
import gwendal.psm.SmsActivity;
import model.ContactGroup;

/**
 * Created by gwendal on 12/03/15.
 * Launches the SmsActivity on the selected group.
 */
public class WriteSmsListener implements View.OnClickListener {

    /**
     * Selected ContactGroup;
     */
    private ContactGroup group;

    /**
     * Constructor.
     * @param group
     */
    public WriteSmsListener(ContactGroup group) {
        this.group = group;
    }

    @Override
    public void onClick(View v) {
        SmsActivity.launchOnContactGroup(v.getContext(), this.group);
    }
}
