package gwendal.psm.views;

import android.content.Context;
import android.util.AttributeSet;
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
public abstract class ObserverList extends LinearLayout implements Observer {

    /**
     * Source list.
     */
    protected ObservableList source;

    /**
     * Constructor.
     * @param context Context.
     */
    public ObserverList(Context context) {
        super(context);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     */
    public ObserverList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     * @param defStyle Styled attributes.
     */
    public ObserverList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Makes an ItemView observing the specified model item.
     * @param modelItem Model item.
     * @return An ItemView observing the specified model item.
     */
    public abstract ItemView makeItemView(ModelItem modelItem);

    /**
     * Gets the source list.
     * @return The source list.
     */
    public ObservableList getSource() {
        return this.source;
    }

    /**
     * Sets the source list.
     * @param source Source List.
     */
    public void setSource(ObservableList source) {
        if(source == null) {
            removeAllViews();
        } else if(source != this.source) {
            source.addObserver(this);
            for(Object o : source.getList()) {
                ModelItem item = (ModelItem)o;
                ItemView component;
                if(this.source.modelViewMap.containsKey(item)) {
                    component = (ItemView)this.source.modelViewMap.get(item);
                } else {
                    component = makeItemView(item);
                    this.source.modelViewMap.put(item, component);
                }
                addView(component);
            }
        }
        this.source = source;
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
        ModelItem item = this.source.getAddedItem();
        ItemView view = makeItemView(item);
        this.source.modelViewMap.put(item, view);
    }

    /**
     * Handles a 'remove' event.
     * Removes the view representing the removed item.
     */
    protected void handleRemove() {
        ModelItem item = this.source.getRemovedItem();
        ItemView view = (ItemView)this.source.modelViewMap.get(item);
        removeView(view);
        this.source.modelViewMap.remove(item);
    }
}
