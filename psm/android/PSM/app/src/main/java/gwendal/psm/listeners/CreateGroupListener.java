package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import gwendal.psm.ContactGroupActivity;
import gwendal.psm.MainActivity;
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
       ContactGroup cg = new ContactGroup();
       ContactGroupActivity.launchOnContactGroup(cg, this.main);
    }
}
