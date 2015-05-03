package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by gwendal on 02/05/15.
 * Observable list.
 */
public class ObservableList<T> extends Observable {

    /**
     * Item list.
     */
    protected ArrayList<T> list;

    /**
     * Last added item.
     */
    private T addedItem;

    /**
     * Last removed item.
     */
    private T removedItem;

    /**
     * Constructor.
     */
    public ObservableList() {
        super();
        this.list = new ArrayList<T>();
    }

    /**
     * Adds the specified item and notifies the observers with arg = {"add", item}.
     * @param item Item to add.
     */
    public void add(T item) {
        this.list.add(item);
        setChanged();
        this.addedItem = item;
        notifyObservers("add");
    }

    /**
     * Removes the specified item and notifies the observers with arg = {"remove", item}.
     * @param item Item to remove.
     */
    public void remove(T item) {
        this.list.remove(item);
        setChanged();
        this.removedItem = item;
        notifyObservers("remove");
    }

    /**
     * Gets the last added item.
     * @return The last added item.
     */
    public T getAddedItem() {
        return this.addedItem;
    }

    /**
     * Gets the last removed item.
     * @return The last removed item.
     */
    public T getRemovedItem() {
        return this.removedItem;
    }

    /**
     * Gets the List.
     * @return The list.
     */
    public ArrayList<T> getList() {
        return this.list;
    }
}
