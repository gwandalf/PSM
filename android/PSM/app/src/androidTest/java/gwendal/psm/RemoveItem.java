package gwendal.psm;

import android.test.ActivityInstrumentationTestCase2;

import gwendal.psm.views.GroupListView;
import gwendal.psm.views.ItemView;
import model.ApplicationManager;
import model.ContactGroup;

/**
 * Created by gwendal on 06/05/15.
 */
public class RemoveItem extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * Main activity.
     */
    public static MainActivity main;

    /**
     * Constructor.
     * @param activityClass Activity class.
     */
    public RemoveItem(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    /**
     * Tests the removing of an added group.
     */
    public void testRemoveGroup() {
        setActivityInitialTouchMode(false);
        main = getActivity();
        int groupCount = main.groupListView.getChildCount();
        Utils.addMyGroup(this, main);
        ContactGroup lastAdded = ApplicationManager.GROUP_LIST.getAddedItem();
        assertEquals(groupCount+1, main.groupListView.getChildCount());
        assertTrue(ApplicationManager.GROUP_LIST.getList().contains(lastAdded));
        ItemView groupView = (ItemView)main.groupListView.getChildAt(groupCount);
        Utils.performClick(main, groupView.destroy);
        assertFalse(ApplicationManager.GROUP_LIST.getList().contains(lastAdded));
        assertEquals(groupCount, main.groupListView.getChildCount());
    }
}
