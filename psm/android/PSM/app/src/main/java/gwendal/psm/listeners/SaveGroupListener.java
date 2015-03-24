package gwendal.psm.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        String name = this.cga.getGroupName().getText().toString();
        this.cga.getObserved().setName(name);
        Intent data = new Intent();
        data.putExtra(ContactGroupActivity.CONTACT_GROUP, this.cga.getObserved());
        this.cga.setResult(Activity.RESULT_OK, data);
        this.cga.finish();
    }
}
