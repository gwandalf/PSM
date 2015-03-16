package gwendal.psm.listeners;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import gwendal.psm.ContactGroupActivity;
import gwendal.psm.DialogFactory;
import gwendal.psm.MainActivity;
import model.ContactGroup;

/**
 * Created by gwendal on 15/02/15.
 */
public class SaveGroupListener extends GroupListener {

    /**
     * Constructor.
     * @param cga Current activity.
     */
    public SaveGroupListener(ContactGroupActivity cga) {
        super(cga);
    }

    @Override
    public void onClick(View v) {
        //TODO copy fields.
        this.cga.finish();
    }
}
