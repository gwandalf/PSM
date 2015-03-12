package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

/**
 * Created by gwendal on 12/03/15.
 * Listener needing its parent context.
 */
public abstract class LaunchedListener implements View.OnClickListener {

    /**
     * Launcher.
     */
    protected Context launcher;

    public LaunchedListener(Context launcher) {

    }
}
