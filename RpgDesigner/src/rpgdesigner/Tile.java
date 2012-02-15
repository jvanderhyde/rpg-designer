/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.Image;
import java.awt.image.BufferedImage;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author james
 */
class Tile {
    //private SpriteSheet tileset; I am going to not use this anymore
    private int id;
    private boolean block;
    private Event event;
    private Image tileImage;
    private String tilesetName;
    
    public Tile(SpriteSheet tileset, int id, boolean block, Event event) {
        //this.tileset = tileset;
        this.id = id;
        this.block = block;
        this.event = event;
    }
    
    public Tile(int id) {
       this.id = id;
    }
    
    public void setTilesetName(String tileset) {
        this.tilesetName = tileset;
    }
    
    public void setBlock(boolean block) {
        this.block = block;
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
    
    public boolean getBlock() {
        return this.block;
    }
    
    public Event getEvent() {
        return this.event;
    }
    
    public Image getTileImage() {
        return this.tileImage;
    }
}
