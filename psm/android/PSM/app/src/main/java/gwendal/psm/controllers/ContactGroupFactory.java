package gwendal.psm.controllers;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gwendal.psm.MainActivity;
import model.ContactGroup;

/**
 * Created by gwendal on 21/03/15.
 * Factory charged of the creation, serialization and deserialization of a ContactGroup.
 */
public class ContactGroupFactory {

    /**
     * Name of the serialization file of the instance of the factory.
     */
    public static final String FILE_NAME = "ContactGroupFactory";

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

    //TODO : serialization, deserialization, make

}
