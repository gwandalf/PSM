package gwendal.psm;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by gwendal on 27/03/15.
 * Utility functions for tests.
 */
public class Utils {

    /**
     * Performs a click from the UI Thread.
     * @param activity Running activity.
     * @param v View to be clicked.
     */
    public static void performClick(Activity activity, View v) {
        activity.runOnUiThread(new ViewRunnable(v) {
            @Override
            public void run() {
                v.performClick();
            }
        });
    }

    /**
     * Runnable containing a view.
     */
    public abstract static class ViewRunnable implements Runnable {

        /**
         * Touched view.
         */
        protected View v;

        /**
         * Constructor.
         * @param v Touched view.
         */
        public ViewRunnable(View v) {
            this.v = v;
        }
    }

}
