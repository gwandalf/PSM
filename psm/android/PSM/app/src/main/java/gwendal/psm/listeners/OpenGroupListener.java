package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import model.ContactGroup;

/**
 * Created by gwendal on 12/03/15.
 * Open the ContactGroupActivity on the selected group.
 */
public class OpenGroupListener extends LaunchedListener {

    /**
     * Group to open.
     */
    private ContactGroup group;

    /**
     * Constructor.
     * @param launcher parent.
     * @param group Group to open.
     */
    public OpenGroupListener(Context launcher, ContactGroup group) {
        super(launcher);
        this.group = group;
    }

    @Override
    public void onClick(View v) {

    }
}
