package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import gwendal.psm.ContactGroupActivity;
import gwendal.psm.MainActivity;
import gwendal.psm.controllers.ContactGroupController;
import model.ContactGroup;

/**
 * Created by gwendal on 15/02/15.
 * Add a Contact Group.
 */
public class CreateGroupListener extends MainListener {

    /**
     * Constructor.
     * @param main Current context.
     */
    public CreateGroupListener(MainActivity main) {
        super(main);
    }

    @Override
    public void onClick(View v) {
        ContactGroupController ctrl = new ContactGroupController(this.main);
        this.main.contactGroupCtrlList.add(ctrl);
        ContactGroupActivity.launchOnContactGroup(null, this.main, this.main.contactGroupCtrlList.size()-1);
    }
}
