package model;

import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by gwendal on 03/05/15.
 */
public class GroupList extends ObservableList<ContactGroup> {

    /**
     * Active Group.
     */
    private ContactGroup active;

    public GroupList() {
        super();
    }

    /**
     * Saves the Group list in the specified file.
     * @param saveFile Save file.
     */
    public void save(OutputStream saveFile) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(saveFile);
        Log.d("FACTORY", "list count = " + this.getList().size());
        os.writeObject(this);
        os.close();
        saveFile.close();
    }

    /**
     * Loads the saved groupList from the specified file.
     * @param saveFile Save file.
     */
    public static GroupList load(FileInputStream saveFile) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(saveFile);
        GroupList groupList = (GroupList) objectInputStream.readObject();
        objectInputStream.close();
        saveFile.close();
        return groupList;
    }

    /**
     * Gets the active group.
     * @return The active group.
     */
    public ContactGroup getActive() {
        return this.active;
    }

    /**
     * Sets the active group.
     * @param active The new active group.
     */
    public void setActive(ContactGroup active) {
        this.active = active;
        setChanged();
        notifyObservers("active");
    }

}
