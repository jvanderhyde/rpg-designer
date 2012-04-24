/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Fran
 */
public class Item implements MapObject {
    
    String name, imagePath;
    public enum itemType {SUSTENANCE, KEY, EQUIPMENT}
    itemType type;
    int increaseXP, increaseHP, increaseSP;
    float locX, locY;
    int tileNum;

    @Override
    public Image getImage() {
        BufferedImage image=null;
        try {
            image = ImageIO.read(new File(this.imagePath));
        } catch (IOException ex) {
            Logger.getLogger(iMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    @Override
    public org.newdawn.slick.Image getSlickImage() throws SlickException {
        org.newdawn.slick.Image image=null;
        image = new org.newdawn.slick.Image(imagePath);
        
        return image;
    }
    
    
    @Override
    public String toString(){
        return name;
    }
            
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
  public int getIncreaseXP(){
        return increaseXP;
    }
    public int getIncreaseHP(){
        return increaseHP;
    }
    public int getIncreaseSP(){
        return increaseSP;
    }
    public void setIncreaseXP(int xp){
        increaseXP = xp;
    }
    public void setIncreaseSP(int sp){
        increaseSP = sp;
    }
    public void setIncreaseHP(int hp){
        increaseHP = hp;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getImagePath(){
        return imagePath;
    }
    public void setImagePath(String path){
        imagePath = path;
    }
    public void setType(itemType type){
        this.type = type;
    }
    
    public itemType getType(){
        return type;
    }
    
    public void setLocation(float x, float y){
        locX=x;
        locY=y;
    }
    
    public float getLocX(){
        return locX;
    }
    public float getLocY(){
        return locY;
    }
    
    @Override
    public int getTile() {
        return tileNum;
    }

    @Override
    public void setTile(int tileNumber) {
        tileNum=tileNumber;
    }
// </editor-fold>
    
    
    
    
    
}
