package model;

import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by gwendal on 03/05/15.
 */
public class GroupListStub extends ObservableList<ContactGroup> {

    /**
     * Active Group.
     */
    private ContactGroup active;

    public GroupListStub() {
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
    public void load(FileInputStream saveFile) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(saveFile);
        GroupListStub groupList = (GroupListStub) objectInputStream.readObject();
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
