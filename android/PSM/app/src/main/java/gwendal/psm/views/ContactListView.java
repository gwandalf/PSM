package gwendal.psm.views;

import android.content.Context;
import android.util.AttributeSet;

import gwendal.psm.listeners.DestroyItemListener;
import model.ModelItem;

/**
 * Created by gwendal on 05/05/15.
 */
public class ContactListView extends ObserverList {

    /**
     * Constructor.
     * @param context Context.
     */
    public ContactListView(Context context) {
        super(context);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     */
    public ContactListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     * @param defStyle Styled attributes.
     */
    public ContactListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ItemView makeItemView(ModelItem modelItem) {
        ItemView groupView = new ItemView(getContext());
        groupView.textView.setText(modelItem.getName());
        DestroyItemListener listener = new DestroyItemListener(modelItem, this.source);
        groupView.destroy.setOnClickListener(listener);
        modelItem.addObserver(groupView);
        return groupView;
    }
}
