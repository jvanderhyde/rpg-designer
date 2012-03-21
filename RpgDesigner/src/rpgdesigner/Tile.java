/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.image.BufferedImage;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author james
 */
public class Tile {
    //private SpriteSheet tileset; I am going to not use this anymore
    private int id;
    private Event event;
    private BufferedImage tileImage;
    private String tilesetName;
    
    public Tile(String tileset, int id, Event event) {
        //this.tileset = tileset;
        this.id = id;
        this.event = event;
    }
    
    public Tile() {
        
    }
    
    public void setTilesetName(String tileset) {
        this.tilesetName = tileset;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    public void setImage(BufferedImage image) {
        this.tileImage = image;
    }
    
    public String getTilesetName() {
        return this.tilesetName;
    }

    public Event getEvent() {
        return this.event;
    }
    
    public java.awt.Image getTileImage() {
        return this.tileImage;
    }
    
    public Image getSlickImage() throws SlickException {
        ImageBuffer buf = new ImageBuffer(tileImage.getWidth(), tileImage.getHeight());
        int x,y,argb;
        for (y = 0;y < tileImage.getHeight();y++) {
            for (x = 0;x < tileImage.getWidth();x++) {
                argb = tileImage.getRGB(x,y);
                buf.setRGBA(x,y,(argb>>16)&0xff,(argb>>8)&0xff,argb&0xff,(argb>>24)&0xff);
            }
        }
        return buf.getImage();
    }
}
