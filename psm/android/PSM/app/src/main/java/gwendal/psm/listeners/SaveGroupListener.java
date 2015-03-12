package gwendal.psm.listeners;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import gwendal.psm.DialogFactory;
import gwendal.psm.MainActivity;
import model.ContactGroup;

/**
 * Created by gwendal on 15/02/15.
 */
public class SaveGroupListener extends LaunchedListener {

    /**
     * Constructor.
     * @param launcher Current activity.
     */
    public SaveGroupListener(Context launcher) {
        super(launcher);
    }

    @Override
    public void onClick(View v) {
        try {
            this.contactGroup.setName(tv.getText().toString());
            MainActivity.register(this.contactGroup);
            this.activity.finish();
        } catch (IOException e) {
            DialogFactory.showErrorDialog("Le groupe ne peut pas être enregistré.", this.activity);
        }
    }
}
