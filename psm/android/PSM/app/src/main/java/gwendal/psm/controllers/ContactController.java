package gwendal.psm.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import model.Contact;

/**
 * Created by gwendal on 12/03/15.
 * Makes the link between the model and the view.
 */
public class ContactController {

    /**
     * Model.
     */
    private Contact model;

    /**
     * View.
     */
    private View view;

    /**
     * Constructor. Initializes the model with the specified data.
     * @param parent Parent Activity.
     * @param data Data retrieved from the contacts app.
     */
    public ContactController(Context parent, Intent data) {
        //TODO
    }

    /**
     * Constructor.
     * @param parent
     * @param model
     */
    public ContactController(Context parent, Contact model) {

    }

    public Contact getModel() {
        return model;
    }

    public void setModel(Contact model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }
}
