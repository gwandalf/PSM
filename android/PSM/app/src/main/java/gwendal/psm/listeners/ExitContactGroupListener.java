package gwendal.psm.listeners;

import android.app.Activity;
import android.view.View;

/**
 * Created by gwendal on 03/05/15.
 * Exits the current activity.
 */
public class ExitContactGroupListener extends ActivityListener {

    /**
     * Constructor.
     * @param activity Parent activity.
     */
    public ExitContactGroupListener(Activity activity) {
        super(activity);
    }

    @Override
    public void onClick(View v) {
        this.activity.finish();
    }
}
