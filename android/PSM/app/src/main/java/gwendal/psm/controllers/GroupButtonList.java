package gwendal.psm.controllers;

import android.content.Intent;
import android.widget.Button;

import java.util.HashMap;
import java.util.Observable;

import gwendal.psm.ContactGroupActivity;
import model.ContactGroup;
import model.GroupList;

/**
 * Created by gwendal on 02/05/15.
 * List of the group buttons.
 * Singleton.
 */
public final class GroupButtonList extends ObserverList {

    /**
     * Only instance.
     */
    public static final GroupButtonList INSTANCE = new GroupButtonList();

    /**
     * Contact views.
     */
    public HashMap<ContactGroup, ContactViewList> contactViews;

    /**
     * Group views.
     */
    public HashMap<ContactGroup, ItemTextView> views;

    /**
     * Active Contact view list.
     */
    public ContactViewList active;

    /**
     * Constructor.
     */
    private GroupButtonList() {
        super();
        this.contactViews = new HashMap<ContactGroup, ContactViewList>();
        this.views = new HashMap<ContactGroup, ItemTextView>();
    }

    @Override
    public void update(Observable observable, Object data) {
        String arg = (String)data;
        if("add".equals(arg)) {
            ContactGroup group = GroupList.INSTANCE.getAddedItem();
            ContactViewList list = new ContactViewList(group);
            this.contactViews.put(group, list);
            ItemTextView groupView = new ItemTextView(this.parentContext, group.getName());
            this.views.put(group, groupView);
            this.parentLayout.addView(groupView.textView, groupView.params);
        } else if("remove".equals(arg)) {
            ContactGroup group = GroupList.INSTANCE.getRemovedItem();
            ItemTextView view = this.views.get(group);
            this.parentLayout.removeView(view.textView);
            this.contactViews.remove(group);
            this.views.remove(view);
        } else if("active".equals(arg)) {
            this.active = this.contactViews.get(GroupList.INSTANCE.getActive());
            Intent intent = new Intent(this.parentContext, ContactGroupActivity.class);
            this.parentContext.startActivity(intent);
        }
    }
}
