package gwendal.psm;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.view.View;

import gwendal.psm.listeners.OpenGroupListener;
import model.ContactGroup;

/**
 * Created by gwendal on 13/03/15.
 * TODO : check the generic type
 */
public class OpenGroupListenerTest extends ActivityInstrumentationTestCase2 {

    /**
     * Constructor.
     * @param activityClass Activity Under test.
     */
    public OpenGroupListenerTest(Class activityClass) {
        super(activityClass);
    }

    public void testOnClick() {
        //Context ctx = getContext();
        ContactGroup group = new ContactGroup("group");
        //OpenGroupListener openGroup = new OpenGroupListener(ctx, group);
       // View button = new View(ctx);
       // openGroup.onClick(button);
        //TODO
    }
}
