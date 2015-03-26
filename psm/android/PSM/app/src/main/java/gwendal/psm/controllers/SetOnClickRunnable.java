package gwendal.psm.controllers;

import android.view.View;
import android.widget.TextView;

import gwendal.psm.MainActivity;
import gwendal.psm.listeners.OpenGroupListener;
import model.ContactGroup;

/**
 * Created by gwendal on 24/03/15.
 */
public class SetOnClickRunnable implements Runnable {

    private TextView view;
    private ContactGroupController model;
    private MainActivity main;

    public  SetOnClickRunnable(TextView view, ContactGroupController model, MainActivity main) {
        this.view = view;
        this.model = model;
        this.main = main;
    }

    @Override
    public void run() {

    }
}
