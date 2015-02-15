package gwendal.psm;

import android.content.Context;
import android.view.View;

/**
 * Created by gwendal on 15/02/15.
 * Add a Contact Group.
 */
public class AddContactGroupCommand implements View.OnClickListener {

    //Current Context.
    private Context ctx;

    /**
     * Constructor.
     * @param ctx Current context.
     */
    public AddContactGroupCommand(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onClick(View v) {
        ContactGroupActivity.launchOnContactGroup(null, this.ctx);
    }
}
