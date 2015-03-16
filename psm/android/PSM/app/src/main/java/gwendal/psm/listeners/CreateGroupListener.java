package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import gwendal.psm.ContactGroupActivity;
import model.ContactGroup;

/**
 * Created by gwendal on 15/02/15.
 * Add a Contact Group.
 */
public class CreateGroupListener extends LaunchedListener {

    /**
     * Constructor.
     * @param launcher Current context.
     */
    public CreateGroupListener(Context launcher) {
        super(launcher);
    }

    @Override
    public void onClick(View v) {
       ContactGroup cg = new ContactGroup();
       ContactGroupActivity.launchOnContactGroup(cg, this.launcher);
    }
}
