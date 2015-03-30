package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import gwendal.psm.ContactGroupActivity;
import gwendal.psm.MainActivity;
import gwendal.psm.controllers.ContactGroupController;
import model.ContactGroup;

/**
 * Created by gwendal on 12/03/15.
 * Open the ContactGroupActivity on the selected group.
 */
public class OpenGroupListener extends MainListener {

    /**
     * Group to open.
     */
    private ContactGroupController group;

    /**
     * Constructor.
     * @param main parent.
     * @param group Group to open.
     */
    public OpenGroupListener(MainActivity main, ContactGroupController group) {
        super(main);
        this.group = group;
    }

    @Override
    public void onClick(View v) {
        int requestCode = this.main.contactGroupCtrlList.indexOf(this.group);
        ContactGroupActivity.launchOnContactGroup(this.group.getModel(), this.main, requestCode);
    }
}
