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
public class GroupList {

    /**
     * Only Instance of this class.
     */
    public static GroupListStub INSTANCE;
}
