package model;

import java.io.Serializable;

/**
 * Created by gwendal on 15/02/15.
 * Contact Group.
 */
public class ContactGroup extends ObservableList<Contact> implements ModelItem {

    //Name.
    private String name;

    /**
     * Constructor.
     */
    public ContactGroup() {
        super();
        this.name = "(Sans nom)";
    }

    /**
     * Constructor.
     * @param name Name.
     */
    public ContactGroup(String name) {
        super();
        this.name = name;
    }

    /**
     * Opens this group.
     */
    public void open() {
        ApplicationManager.SELECTED_GROUP = this;
        setChanged();
        notifyObservers("open");
    }

    /**
     * Gets the name.
     * @return The name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers("name");
    }

}
