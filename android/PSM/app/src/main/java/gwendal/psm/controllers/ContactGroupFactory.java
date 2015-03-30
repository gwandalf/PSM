package gwendal.psm.controllers;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import gwendal.psm.MainActivity;
import model.ContactGroup;

/**
 * Created by gwendal on 21/03/15.
 * Factory charged of the creation, serialization and loading of ContactGroups.
 */
public class ContactGroupFactory implements Serializable {

    /**
     * Name of the serialization file of the instance of the factory.
     */
    public static final String FILE_NAME = "ContactGroupFactory.ser";

    /**
     * Prefix of the ContactGroup files.
     */
    public static final String PREFIX = "ContactGroup_";

    /**
     * Extension of the ContactGroup files.
     */
    public static final String EXT = ".ser";

    /**
     * Next Id.
     */
    private int nextId;

    /**
     * Constructor.
     */
    public ContactGroupFactory() {
        this.nextId = 0;
    }

    public void saveInstance(MainActivity main) throws IOException {
        FileOutputStream fos = main.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(this);
        os.close();
        fos.close();
    }

    /**
     * Loads an instance of ContactGroupFactory from a binary file.
     * @param main MainActivity.
     * @return An instance of ContactGroupFactory from a binary file.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ContactGroupFactory loadInstance(MainActivity main) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = main.openFileInput(FILE_NAME);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ContactGroupFactory res = (ContactGroupFactory) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return res;
    }

    /**
     * Makes a ContactGroup with id = nextId.
     * @param name Group name.
     * @return ContactGroup.
     */
    public ContactGroup makeContactGroup(String name) {
        ContactGroup res = new ContactGroup(this.nextId, name);
        this.nextId++;
        return res;
    }

    /**
     * Serializes the specified ContactGroup in the file system of the MainActivity.
     * @param cg ContactGroup.
     * @param main MainActivity.
     * @throws IOException
     */
    public void save(ContactGroup cg, MainActivity main) throws IOException {
        FileOutputStream fos = main.openFileOutput(PREFIX + cg.getId() + EXT, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(cg);
        os.close();
        fos.close();
    }

    /**
     * Loads the set of ContactGroup from the MainActivity file list.
     * @param main MainActivity.
     * @return The set of ContactGroup from the MainActivity file list.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ContactGroupControllerList load(MainActivity main) throws IOException, ClassNotFoundException {
        ContactGroupControllerList res = new ContactGroupControllerList();
        String[] fileList = main.fileList();
        Log.d("FACTORY", "NextId = " + this.nextId);
        for(int i = 0 ; i < fileList.length ; i++) {
            String fileName = fileList[i];
            Log.d("FACTORY", "file : " + fileName);
            if(fileName.startsWith(PREFIX)) {
                Log.d("FACTORY", "inside if");
                FileInputStream fileInputStream = main.openFileInput(fileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                ContactGroup cg = (ContactGroup) objectInputStream.readObject();
                ContactGroupController ctrl = new ContactGroupController(cg, main);
                res.add(ctrl);
                objectInputStream.close();
                fileInputStream.close();
            }
        }
        return res;
    }

}
