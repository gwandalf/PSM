package model;

import java.io.Serializable;
import java.util.Observer;

/**
 * Created by gwendal on 03/05/15.
 */
public interface ModelItem extends Serializable {
    String getName();
    void addObserver(Observer observer);
}
