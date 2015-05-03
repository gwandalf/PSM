package gwendal.psm.listeners;

import android.app.Activity;
import android.view.View;

/**
 * Created by gwendal on 03/05/15.
 * Listener using an activity.
 */
public abstract class ActivityListener implements View.OnClickListener {

    /**
     * Parent activity.
     */
    protected Activity activity;

    /**
     * Constructor.
     * @param activity Parent activity.
     */
    public ActivityListener(Activity activity) {
        this.activity = activity;
    }

}
