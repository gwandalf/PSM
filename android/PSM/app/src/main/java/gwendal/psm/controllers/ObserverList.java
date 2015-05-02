package gwendal.psm.controllers;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.Observer;

/**
 * Created by gwendal on 02/05/15.
 * Observer list.
 */
public abstract class ObserverList implements Observer {

    /**
     * Parent context.
     */
    public Context parentContext;

    /**
     * Parent layout.
     */
    public LinearLayout parentLayout;

    /**
     * Constructor.
     */
    public ObserverList() {
        super();
    }
}
