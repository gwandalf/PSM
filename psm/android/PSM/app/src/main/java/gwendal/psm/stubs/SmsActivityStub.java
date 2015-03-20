package gwendal.psm.stubs;

import android.content.Context;
import android.content.Intent;

import model.ContactGroup;

/**
 * Created by gwendal on 12/03/15.
 * SMS activity delegate.
 */
public class SmsActivityStub {

    /**
     * Launches the SMS activity on the specified ContactGroup, from the specified launcher Context.
     * @param group ContactGroup.
     * @param launcher Launcher Context.
     * @return The intent sent to the SMS activity.
     */
    public static Intent launchOnGroup(ContactGroup group, Context launcher) {
        return null;
    }
}
