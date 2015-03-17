package gwendal.psm;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.ContactGroup;

/**
 * Created by gwendal on 15/02/15.
 * List of contact groups saved in memory.
 */
public class ContactGroupListController extends ArrayList<ContactGroup> {

    //Current context.
    private Context ctx;

    //Support layout.
    private LinearLayout layout;

    /**
     * Constructor.
     * Loads the saved files.
     * @param ctx Context (Activity) of the data files.
     * @throws Exception in case of any problem while reading the data files.
     */
    public ContactGroupListController(Context ctx, LinearLayout layout) throws Exception {
        super();
        this.ctx = ctx;
        this.layout = layout;
        for(String fileName : this.ctx.fileList()) {
            FileInputStream fis = this.ctx.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            ContactGroup cg = (ContactGroup) is.readObject();
            is.close();
            fis.close();
            this.add(cg);
            TextView tv = new TextView(this.ctx);
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tv.setText(cg.getName());
            this.layout.addView(tv);
        }
    }

    /**
     * Registers the specified ContactGroup.
     * @param cg ContactGroup to register.
     */
    public void register(ContactGroup cg) throws IOException {

    }

}
