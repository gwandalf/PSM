package gwendal.psm.listeners;

import android.util.Log;
import android.view.View;

import model.ModelItem;
import model.ObservableList;

/**
 * Created by gwendal on 04/05/15.
 * Destroy a ModelItem.
 */
public class DestroyItemListener implements View.OnClickListener {

    /**
     * Item to destroy.
     */
    private ModelItem item;

    /**
     * Item container.
     */
    private ObservableList itemContainer;

    /**
     * Constructor.
     * @param item Item to destroy.
     * @param itemContainer Item container.
     */
    public DestroyItemListener(ModelItem item, ObservableList itemContainer) {
        this.item = item;
        this.itemContainer = itemContainer;
        Log.d("SOURCE", "Listener, this.itemContainer == null is " + (this.itemContainer == null));
    }

    @Override
    public void onClick(View v) {
        this.itemContainer.remove(this.item);
    }
}
