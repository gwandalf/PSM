package gwendal.psm.controllers;

import android.content.Intent;

import java.util.HashMap;
import java.util.Observable;

import gwendal.psm.ContactGroupActivity;
import gwendal.psm.listeners.OpenGroupListener;
import model.ContactGroup;
import model.GroupList;

/**
 * Created by gwendal on 02/05/15.
 * List of the group buttons.
 * Singleton.
 */
public final class GroupViewList extends ObserverList<ContactGroup> {

    /**
     * Only instance.
     */
    public static final GroupViewList INSTANCE = new GroupViewList();

    /**
     * Contact views.
     */
    public HashMap<ContactGroup, ContactViewList> contactViews;

    /**
     * Active Contact view list.
     */
    public ContactViewList active;

    /**
     * Constructor.
     */
    private GroupViewList() {
        super();
        this.contactViews = new HashMap<ContactGroup, ContactViewList>();
    }

    @Override
    public void update(Observable observable, Object data) {
        super.update(observable, data);
        String arg = (String)data;
        if("active".equals(arg)) {
            this.active = this.contactViews.get(GroupList.INSTANCE.getActive());
            Intent intent = new Intent(this.parentContext, ContactGroupActivity.class);
            this.parentContext.startActivity(intent);
        }
    }

    @Override
    protected void handleAdd() {
        super.handleAdd();
        ContactGroup modelItem = this.observed.getAddedItem();
        ContactViewList contactViewList = new ContactViewList(modelItem);
        this.contactViews.put(modelItem, contactViewList);
        OpenGroupListener openGroup = new OpenGroupListener(modelItem);
        ItemTextView viewItem = this.modelViewMap.get(modelItem);
        viewItem.textView.setOnClickListener(openGroup);
    }

    @Override
    protected void handleRemove() {
        super.handleRemove();
        ContactGroup modelItem = this.observed.getRemovedItem();
        this.contactViews.remove(modelItem);
    }
}
