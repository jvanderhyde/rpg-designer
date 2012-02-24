package rpgdesigner;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author james
 */
public class Event {
    private String name;
    //private Item key;
    Image icon;
    Boolean onActionKey;
    //ArrayList<command> actions;
    public Event() {
        name ="";
        onActionKey = true;
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
