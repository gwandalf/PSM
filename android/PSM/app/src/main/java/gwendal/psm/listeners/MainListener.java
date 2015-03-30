package gwendal.psm.listeners;

import android.content.Context;
import android.view.View;

import gwendal.psm.MainActivity;

/**
 * Created by gwendal on 12/03/15.
 * Listener launched by the MainActivity.
 */
public abstract class MainListener implements View.OnClickListener {

    /**
     * Launcher.
     */
    protected MainActivity main;

    /**
     * Constructor.
     * @param main MainActivity.
     */
    public MainListener(MainActivity main) {
        this.main = main;
    }
}
