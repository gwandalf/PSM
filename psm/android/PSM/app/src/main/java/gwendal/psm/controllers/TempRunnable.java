package gwendal.psm.controllers;

import android.view.View;
import android.widget.LinearLayout;

import gwendal.psm.MainActivity;

/**
 * Created by gwendal on 24/03/15.
 */
public class TempRunnable implements Runnable {

    private MainActivity main;
    private View view;
    private LinearLayout.LayoutParams params;

    public TempRunnable(MainActivity main, View view, LinearLayout.LayoutParams params) {
        this.main = main;
        this.view = view;
        this.params = params;
    }

    @Override
    public void run() {
        main.getLayout().addView(this.view, params);
    }
}
