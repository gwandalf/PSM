package gwendal.psm;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

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
     * Constructor.
     * @param activity Current activity.
     */
    public RegisterContactGroupCommand(Activity activity, TextView tv) {
        this.activity = activity;
        this.tv = tv;
    }

    @Override
    public void onClick(View v) {
        try {
            ContactGroup cg = new ContactGroup(tv.getText().toString());
            MainActivity.register(cg);
            this.activity.finish();
        } catch (IOException e) {
            DialogFactory.showErrorDialog("Le groupe ne peut pas être enregistré.", this.activity);
        }
    }
}
