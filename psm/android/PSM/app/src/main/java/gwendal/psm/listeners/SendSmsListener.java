package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import java.security.acl.Group;

import gwendal.psm.ContactGroupActivity;

/**
 * Created by gwendal on 12/03/15.
 * Launches the SMS app on the selected group.
 */
public class SendSmsListener extends GroupListener {

    public SendSmsListener(ContactGroupActivity cga) {
        super(cga);
    }

    @Override
    public void onClick(View v) {

    }
}
