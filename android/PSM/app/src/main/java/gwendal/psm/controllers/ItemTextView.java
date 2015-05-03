package gwendal.psm.controllers;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import gwendal.psm.R;
import model.ModelItem;

/**
 * Created by gwendal on 02/05/15.
 * Item View template.
 */
public class ItemTextView implements Observer {

    /**
     * Text view.
     */
    public TextView textView;

    /**
     * Parameters.
     */
    public LinearLayout.LayoutParams params;

    /**
     * Observed model item.
     */
    public ModelItem observed;

    /**
     * Constructor.
     * @param parentContext Context of the TextView.
     * @param observed Observed Model Item.
     */
    public ItemTextView(Context parentContext, ModelItem observed) {
        this.observed = observed;
        this.observed.addObserver(this);
        this.textView = new TextView(parentContext, null, R.attr.style);
        this.params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.params.setMargins(16, 16, 16, 16);
        this.textView.setText(observed.getName());
    }

    @Override
    public void update(Observable observable, Object data) {
        String arg = (String)data;
        if("name".equals(arg)) {
            this.textView.setText(this.observed.getName());
        }
    }
}
