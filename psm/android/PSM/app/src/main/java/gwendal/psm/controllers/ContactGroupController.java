package gwendal.psm.controllers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

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
     * Serialization file name.
     */
    private String fileName;

    /**
     * Constructor.
     * @param model model.
     * @param parent Parent activity.
     */
    public ContactGroupController(ContactGroup model, Context parent) {
        this.model = model;
        this.view = new TextView(parent);
    }

    /**
     * Constructor.
     * @param model model.
     * @param parent Parent activity.
     * @param fileName Serialization file name.
     */
    public ContactGroupController(ContactGroup model, Context parent, String fileName) {
        this.model = model;
        this.view = new TextView(parent);
        this.fileName = fileName;
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

    /**
     * Gets the fileName.
     * @return the fileName.
     */
    public String getFileName() { return this.fileName; }
}
