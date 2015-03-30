package gwendal.psm.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import gwendal.psm.MainActivity;
import gwendal.psm.R;
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
    private TextView view;

    /**
     * Model.
     */
    private ContactGroup model;

    /**
     * MainActivity instance.
     */
    private MainActivity main;

    /**
     * Constructor.
     * Add the view to the MainActivity.
     * @param main Parent activity.
     */
    public ContactGroupController(MainActivity main) {
        this.main = main;
        this.view = new TextView(main, null, R.attr.style);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 16, 16, 16);
        main.getLayout().addView(this.view, params);
        OpenGroupListener open = new OpenGroupListener(main, this);
        this.view.setOnClickListener(open);
    }

    /**
     * Constructor.
     * Add the view to the MainActivity and sets it up (listener).
     * @param model model.
     * @param main Parent activity.
     */
    public ContactGroupController(ContactGroup model, MainActivity main) {
        this.main = main;
        this.model = model;
        this.view = new TextView(main, null, R.attr.style);
        this.view.setText(this.model.getName());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 16, 16, 16);
        main.getLayout().addView(this.view, params);
        OpenGroupListener open = new OpenGroupListener(main, this);
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
     * Changes the model and Triggers the changes to the view.
     * @param model Model to set.
     */
    public void setModel(ContactGroup model) {
        this.model = model;
        this.view.setText(this.model.getName());
        OpenGroupListener open = new OpenGroupListener(this.main, this);
        this.view.setOnClickListener(open);
    }

}
