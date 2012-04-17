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
    private BufferedImage tileImage;
    private String tilesetName;
    private int tilesetX;
    private int tilesetY;
    
    public Tile() {
        
    }
    
    public Tile(BufferedImage tileImage, String tilesetName, int tilesetx, int tilesety) {
        this.tileImage = tileImage;
        this.tilesetName = tilesetName;
        this.tilesetX = tilesetx;
        this.tilesetY = tilesety;
    }
    
    public void setTilesetName(String tileset) {
        this.tilesetName = tileset;
    }
    
    public void setTilesetNumber(int number) {
        
    }
    
    public void setImage(BufferedImage image, String tilesetName, int tilesetx, int tilesety) {
        this.tileImage = image;
        if(tilesetName != null)
            this.tilesetName = tilesetName;
        else
            this.tilesetName = "null";
        this.tilesetX = tilesetx;
        this.tilesetY = tilesety;
        
    }
    
    public String getTilesetName() {
        return this.tilesetName;
    }
    
    public int getTilesetX() {
        return this.tilesetX;
    }
    
    public int getTilesetY() {
        return this.tilesetY;
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
