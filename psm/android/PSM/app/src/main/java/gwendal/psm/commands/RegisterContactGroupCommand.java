package gwendal.psm.commands;

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
public class RegisterContactGroupCommand implements View.OnClickListener {

    //Current Activity.
    private Activity activity;

    //Name field.
    private TextView tv;

    /**
     * Contact Group to register.
     */
    private ContactGroup contactGroup;

    /**
     * Constructor.
     * @param activity Current activity.
     * @param tv Name field.
     * @param contactGroup Contact Group to register.
     */
    public RegisterContactGroupCommand(Activity activity, TextView tv, ContactGroup contactGroup) {
        this.activity = activity;
        this.tv = tv;
        this.contactGroup = contactGroup;
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
