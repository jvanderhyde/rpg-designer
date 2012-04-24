/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Fran
 */
public interface MapObject {
    
    public void setLocation(float x, float y);
    public Image getImage();
    public float getLocY();
    public float getLocX();
    public int getTile();
    public void setTile(int tileNumber);
    public org.newdawn.slick.Image getSlickImage() throws SlickException ;
}
