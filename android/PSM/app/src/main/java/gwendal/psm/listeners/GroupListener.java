package gwendal.psm.listeners;

import android.view.View;

import model.ContactGroup;

/**
 * Created by gwendal on 16/03/15.
 * Listener using a ContactGroup.
 */
public abstract class GroupListener implements View.OnClickListener {

    /**
     * ContactGroup.
     */
    protected ContactGroup group;

    /**
     * Constructor.
     * @param group ContactGroup.
     */
    public GroupListener(ContactGroup group) { this.group = group; }
}
