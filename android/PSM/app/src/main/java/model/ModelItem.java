package model;

import java.util.Observer;

/**
 * Created by gwendal on 03/05/15.
 */
public interface ModelItem {
    String getName();
    void addObserver(Observer observer);
}
