package gwendal.psm.listeners;

import android.util.Log;
import android.view.View;

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
        GroupList.INSTANCE.add(group);
        group.open();
    }
}
