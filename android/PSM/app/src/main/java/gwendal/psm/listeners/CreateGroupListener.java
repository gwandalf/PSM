package gwendal.psm.listeners;

import android.util.Log;
import android.view.View;

import model.ApplicationManager;
import model.ContactGroup;
import model.GroupList;

/**
 * Created by gwendal on 15/02/15.
 * Add a Contact Group.
 */
public class CreateGroupListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        ContactGroup group = new ContactGroup();
        ApplicationManager.GROUP_LIST.add(group);
        group.open();
    }
}
