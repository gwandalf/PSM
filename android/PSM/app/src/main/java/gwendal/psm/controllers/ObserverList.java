package gwendal.psm.controllers;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.ModelItem;
import model.ObservableList;

/**
 * Created by gwendal on 02/05/15.
 * Observer list.
 */
public class ObserverList<T extends ModelItem> implements Observer {

    /**
     * Parent context.
     */
    public Context parentContext;

    /**
     * Parent layout.
     */
    public LinearLayout parentLayout;

    /**
     * Observed object.
     */
    public ObservableList<T> observed;

    /**
     * Model view map.
     */
    public HashMap<T, ItemTextView> modelViewMap;

    /**
     * Constructor.
     */
    public ObserverList() {
        super();
        this.modelViewMap = new HashMap<T, ItemTextView>();
    }

    @Override
    public void update(Observable observable, Object data) {
        String arg = (String)data;
        if("add".equals(arg)) {
            handleAdd();
        } else if("remove".equals(arg)) {
            handleRemove();
        }
    }

    /**
     * Handles an 'add' event.
     * Adds a text view representing the added item in the parent layout.
     */
    protected void handleAdd() {
        T modelItem = this.observed.getAddedItem();
        ItemTextView viewItem = new ItemTextView(this.parentContext, modelItem);
        this.modelViewMap.put(modelItem, viewItem);
        this.parentLayout.addView(viewItem.textView, viewItem.params);
    }

    /**
     * Handles a 'remove' event.
     * Removes the view representing the removed item.
     */
    protected void handleRemove() {
        T modelItem = this.observed.getRemovedItem();
        ItemTextView viewItem = this.modelViewMap.get(modelItem);
        this.parentLayout.removeView(viewItem.textView);
        this.modelViewMap.remove(modelItem);
    }
}
