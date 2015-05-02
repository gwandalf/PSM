package gwendal.psm.controllers;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import gwendal.psm.R;

/**
 * Created by gwendal on 02/05/15.
 */
public class ItemTextView {

    /**
     * Text view.
     */
    public TextView textView;

    /**
     * Parameters.
     */
    public LinearLayout.LayoutParams params;

    /**
     * Constructor.
     * @param parentContext Context of the TextView.
     * @param text TextView content.
     */
    public ItemTextView(Context parentContext, String text) {
        this.textView = new TextView(parentContext, null, R.attr.style);
        this.params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.params.setMargins(16, 16, 16, 16);
    }
}
