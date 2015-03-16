package gwendal.psm.listeners;

import android.view.View;

import gwendal.psm.ContactGroupActivity;

/**
 * Created by gwendal on 16/03/15.
 */
public abstract class GroupListener implements View.OnClickListener {

    /**
     * ContactGroupActivity.
     */
    protected ContactGroupActivity cga;

    /**
     * Constructor.
     * @param cga ContactGroupActivity.
     */
    public GroupListener(ContactGroupActivity cga) { this.cga = cga; }
}
