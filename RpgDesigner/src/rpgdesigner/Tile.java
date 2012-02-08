/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author james
 */
class Tile {
    //private SpriteSheet tileset; I am going to not use this anymore
    private int X;
    private int Y;
    private boolean block;
    private Event event;
    
    public Tile(SpriteSheet tileset, int x, int y, boolean block, Event event) {
        //this.tileset = tileset;
        this.X = x;
        this.X = x;
        this.block = block;
        this.event = event;
    }
    
    public Tile() {
        
    }
    
    public void setTileset(SpriteSheet tileset) {
        //this.tileset = tileset;
    }
    
    public void setX(int x) {
        this.X = x;
    }
    
    public void setY(int y) {
        this.Y = y;
    }
    
    public void setBlock(boolean block) {
        this.block = block;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
//    public SpriteSheet getTileset() {
//        return this.tileset;
//    }
    
    public int getX() {
        return this.X;
    }
    
    public int getY() {
        return this.Y;
    }
    
    public boolean getBlock() {
        return this.block;
    }
    
    public Event getEvent() {
        return this.event;
    }
}
