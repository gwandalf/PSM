package model;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by gwendal on 02/05/15.
 * Group list.
 * Singleton.
 */
public final class GroupList extends ObservableList<ContactGroup> implements Serializable {

    /**
     * Only Instance of this class.
     */
    public static final GroupList INSTANCE = new GroupList();

    /**
     * Save file name.
     */
    public static final String FILE_NAME = "Save.ser";

    /**
     * Active Group.
     */
    private ContactGroup active;

    /**
     * Constructor.
     */
    private GroupList() {
        super();
    }

    /**
     * Saves the Group list in the specified file.
     * @param saveFile Save file.
     */
    public void save(OutputStream saveFile) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(saveFile);
        os.writeObject(this);
        os.close();
        saveFile.close();
    }

    /**
     * Loads the saved groupList from the specified file.
     * @param saveFile Save file.
     */
    public void load(FileInputStream saveFile) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(saveFile);
        GroupList groupList = (GroupList) objectInputStream.readObject();
        for(ContactGroup group : groupList.list) {
            add(group);
            Log.d("FACTORY", "group added");
        }
        objectInputStream.close();
        saveFile.close();
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
