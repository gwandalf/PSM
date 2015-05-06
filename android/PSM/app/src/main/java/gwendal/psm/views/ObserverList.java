package gwendal.psm.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
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
     * Params.
     */
    public static final LayoutParams PARAMS = initParams();

    /**
     * Source list.
     */
    protected ObservableList source;

    /**
     * Map of correspondence between the elements and their observers.
     */
    public HashMap<ModelItem, ItemView> modelViewMap;

    /**
     * Constructor.
     * @param context Context.
     */
    public ObserverList(Context context) {
        super(context);
        this.modelViewMap = new HashMap<ModelItem, ItemView>();
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     */
    public ObserverList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.modelViewMap = new HashMap<ModelItem, ItemView>();
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     * @param defStyle Styled attributes.
     */
    public ObserverList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.modelViewMap = new HashMap<ModelItem, ItemView>();
    }

    /**
     * Initializes the params.
     * @return The params.
     */
    private static LayoutParams initParams() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 16, 16, 16);
        return params;
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
            this.source = null;
        } else if(source != this.source) {
            this.source = source;
            source.addObserver(this);
            for(Object o : source.getList()) {
                ModelItem item = (ModelItem)o;
                ItemView component;
                if(this.modelViewMap.containsKey(item)) {
                    component = (ItemView)this.modelViewMap.get(item);
                } else {
                    component = makeItemView(item);
                    this.modelViewMap.put(item, component);
                }
                addView(component, PARAMS);
            }
        }
        Log.d("SOURCE", "this.source == null is " + (this.source == null));
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
        addView(view, PARAMS);
        this.modelViewMap.put(item, view);
    }

    /**
     * Handles a 'remove' event.
     * Removes the view representing the removed item.
     */
    protected void handleRemove() {
        ModelItem item = this.source.getRemovedItem();
        ItemView view = (ItemView)this.modelViewMap.get(item);
        removeView(view);
        this.modelViewMap.remove(item);
    }
}
