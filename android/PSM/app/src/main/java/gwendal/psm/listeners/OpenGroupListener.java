package gwendal.psm.listeners;

import android.view.View;

import model.ContactGroup;

/**
 * Created by gwendal on 12/03/15.
 * Open the ContactGroupActivity on the selected group.
 */
public class OpenGroupListener extends GroupListener {

    /**
     * Constructor.
     * @param group Group to open.
     */
    public OpenGroupListener(ContactGroup group) {
        super(group);
    }

    @Override
    public void onClick(View v) {
        this.group.open();
    }
}
