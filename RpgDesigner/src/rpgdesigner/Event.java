package rpgdesigner;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.DefaultListModel;
import org.newdawn.slick.SlickException;

/**
 *
 * @author james
 */
public class Event implements MapObject{
    private String name;
    //private Item key;
    Image icon;
    Boolean onActionKey;
    DefaultListModel eventListModel;
    int locX, locY;
    int tileNum;
    //ArrayList<command> actions;
    public Event()  {
        name ="";
        onActionKey = true;
    }
    
    public DefaultListModel getEventListModel()
    {
        
        return eventListModel;
    }
    
    public Object[] getActions(){
        return eventListModel.toArray();
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

    @Override
    public void setLocation(int x, int y) {
        locX=x;
        locY=y;
    }

 
    @Override
    public float getLocY() {
        return locY;
    }

    @Override
    public float getLocX() {
        return locX;
    }

   
    
    @Override
    public Image getImage() {
        BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("E", 10, 10);
        return image;
    }

    @Override
    public org.newdawn.slick.Image getSlickImage() throws SlickException {
        org.newdawn.slick.Image image=null;
        //returna blank image
        image = new org.newdawn.slick.Image(0,0);
        
        return image;
    }

    @Override
    public int getTile() {
        return tileNum;
    }

    @Override
    public void setTile(int tileNumber) {
        tileNum=tileNumber;
    }

    
}
