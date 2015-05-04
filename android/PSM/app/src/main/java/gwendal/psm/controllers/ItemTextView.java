package gwendal.psm.controllers;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import gwendal.psm.R;
import gwendal.psm.listeners.DestroyItemListener;
import gwendal.psm.views.ItemView;
import model.ModelItem;
import model.ObservableList;

/**
 * Created by gwendal on 02/05/15.
 * Item View template.
 */
public class ItemTextView implements Observer {

    /**
     * Item view.
     */
    public ItemView view;

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
        this.view = new ItemView(parentContext);
        this.view.textView.setText(observed.getName());
        this.observed = observed;
        this.observed.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        String arg = (String)data;
        if("name".equals(arg)) {
            this.view.textView.setText(this.observed.getName());
        }
    }
}
