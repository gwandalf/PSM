package gwendal.psm.controllers;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import gwendal.psm.listeners.DestroyItemListener;
import model.Contact;
import model.ContactGroup;
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

    /**
     * Forces this instance to create and add a view for all the contacts that weren't represented.
     */
    public void forceUpdate() {
        for(T item : this.observed.getList()) {
            ItemTextView viewItem;
            if(this.modelViewMap.containsKey(item)) {
                viewItem = this.modelViewMap.get(item);
            } else {
                viewItem = new ItemTextView(this.parentContext, item);
                this.modelViewMap.put(item, viewItem);
            }
            this.parentLayout.addView(viewItem.view.textView, viewItem.view.getParams());
            setListeners(viewItem);
        }
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
        this.parentLayout.addView(viewItem.view.textView, viewItem.view.getParams());
    }

    /**
     * Handles a 'remove' event.
     * Removes the view representing the removed item.
     */
    protected void handleRemove() {
        T modelItem = this.observed.getRemovedItem();
        ItemTextView viewItem = this.modelViewMap.get(modelItem);
        this.parentLayout.removeView(viewItem.view.textView);
        this.modelViewMap.remove(modelItem);
    }

    /**
     * Sets the listeners to the specified view Item.
     * @param viewItem View Item.
     */
    protected void setListeners(ItemTextView viewItem) {
        DestroyItemListener listener = new DestroyItemListener(viewItem.observed, this.observed);
        viewItem.view.destroy.setOnClickListener(listener);
    }
}
