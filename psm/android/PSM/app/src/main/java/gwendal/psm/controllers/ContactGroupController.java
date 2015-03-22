package gwendal.psm.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import gwendal.psm.MainActivity;
import gwendal.psm.listeners.OpenGroupListener;
import model.ContactGroup;

/**
 * Created by gwendal on 12/03/15.
 * Makes the link between a ContactGroup and the view.
 */
public class ContactGroupController {

    /**
     * View.
     */
    private View view;

    /**
     * Model.
     */
    private ContactGroup model;

    /**
     * Constructor.
     * Add the view to the MainActivity and sets it up (listener).
     * @param model model.
     * @param main Parent activity.
     */
    public ContactGroupController(ContactGroup model, MainActivity main) {
        this.model = model;
        this.view = new TextView(main);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        main.getLayout().addView(this.view, params);
        OpenGroupListener open = new OpenGroupListener(main, this.model);
        this.view.setOnClickListener(open);
    }

    /**
     * Gets the view.
     * @return the view.
     */
    public View getView() {
        return view;
    }

    /**
     * Gets the model.
     * @return the model.
     */
    public ContactGroup getModel() {
        return model;
    }

    /**
     * Changes the model.
     * @param model Model to set.
     */
    public void setModel(ContactGroup model) {
        this.model = model;
    }

}
