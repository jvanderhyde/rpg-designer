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
    private Image icon;
    private Boolean onActionKey;
    private DefaultListModel eventListModel;
    private float locX, locY;
    private int tileNum;
    private Actor assignedNPC;
    private boolean hasOccured;
    
    public Event()  {
        name ="";
        onActionKey = true;
        hasOccured = false;
    }

    public boolean HasOccured() {
        return hasOccured;
    }

    public void setHasOccured(boolean hasOccured) {
        this.hasOccured = hasOccured;
    }

    public Actor getAssignedNPC() {
        return assignedNPC;
    }

    public void setAssignedNPC(Actor assignedNPC) {
        this.assignedNPC = assignedNPC;
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
    public void setLocation(float x, float y) {
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

   
    /*
     * For events, an 'E' is drawn on the map where the event is added in the 
     * map editor
     */
    @Override
    public Image getImage() {
        BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("E", 12, 20);
        return image;
    }

    /*
     * Nothing should be drawn during play-test where the event is, so we return
     * a blank image
     */
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
