package rpgdesigner;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author james
 */
public class Event {
    private String name;
    //private Item key;
    Image icon;
    Boolean onActionKey;
    DefaultListModel eventListModel;
    //ArrayList<command> actions;
    public Event() {
        name ="new event";
        onActionKey = true;
    }
    
    public DefaultListModel getEventListModel()
    {
        return eventListModel;
    }
    
    public void setEventListModel(DefaultListModel list)
    {
        eventListModel = list;
    }
    
    @Override
    public String toString()
    {
        return getName();
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Image getIcon()
    {
        return icon;
    }
    
    public void setIcon(Image icon)
    {
        this.icon = icon;
    }
    
    public boolean onActionKey()
    {
        return onActionKey;
    }
    
    public void setOnActionKey(Boolean b)
    {
        onActionKey = b;
    }
}
