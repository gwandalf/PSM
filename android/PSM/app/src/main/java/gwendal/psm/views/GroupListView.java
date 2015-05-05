package gwendal.psm.views;

import android.content.Context;
import android.util.AttributeSet;

import gwendal.psm.listeners.DestroyItemListener;
import gwendal.psm.listeners.OpenGroupListener;
import model.ContactGroup;
import model.ModelItem;

/**
 * Created by gwendal on 05/05/15.
 */
public class GroupListView extends ContactListView {

    /**
     * Constructor.
     * @param context Context.
     */
    public GroupListView(Context context) {
        super(context);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     */
    public GroupListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     * @param defStyle Styled attributes.
     */
    public GroupListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ItemView makeItemView(ModelItem modelItem) {
        ItemView view = super.makeItemView(modelItem);
        OpenGroupListener listener = new OpenGroupListener((ContactGroup)modelItem);
        view.textView.setOnClickListener(listener);
        return view;
    }
}
