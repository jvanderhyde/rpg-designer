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
import javax.swing.DefaultListModel;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
/**
 *
 * @author Fran
 */
public class Actor implements MapObject{
    private String name;
    private int begHP, increaseHP,  increaseXP, begSP, increaseSP;
    private SpriteSheet spriteSheet;
    private float locX, locY;
    private String imagePath;
    private int type;
    private DefaultListModel skills;
    private int tileNum;
    private Direction directionOfMovement;
    
    public enum Direction{UP, DOWN, LEFT, RIGHT, NONE}   
    public Actor()
    {
        name="";
        this.directionOfMovement= Direction.NONE;
    }
    
    @Override
    public void setLocation(float x, float y)
    {
        locX=x;
        locY=y;
    }
    
    @Override
    public float getLocX()
    {
        return locX;
    }
    
    @Override
    public float getLocY()
    {
        return locY;
    }
    
    public void move (double x, double y)
    {
        locX+=x;
        locY+=y;
        
    }
    
    public void setDirection(Direction d)
    {
        directionOfMovement = d;
        switch (directionOfMovement){
            case UP:
                if(locY%32<1.3||locY%32>30.7)
                    directionOfMovement = Direction.NONE;
                break;
            case DOWN:
                if(locY%32<1.3||locY%32>30.7)
                    directionOfMovement = Direction.NONE;
                break;
            case LEFT:
                if(locX%32<1.3||locX%32>30.7)
                    directionOfMovement = Direction.NONE;
                break;
            case RIGHT:
                if(locX%32<1.3||locX%32>30.7)
                    directionOfMovement = Direction.NONE;
                break;
        }
    }
    
    public Direction getDirection()
    {
        return directionOfMovement;
    }
    
    public String getImagePath()
    {
        return imagePath;
    }
    
    public void setImagePath(String path)
    {
        imagePath = path;
    }
    @Override
    public String toString()
    {
        return name;
    }
    
    /*
     * Gets the image of the sprite facing forward
     */
    public Image getMainSprite() 
    {
        BufferedImage sheetImage=null;
        try {
            sheetImage = ImageIO.read(new File(this.imagePath));
        } catch (IOException ex) {
            Logger.getLogger(iMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sheetImage.getSubimage(32, 64, 32, 32);
    }
    @Override
    public org.newdawn.slick.Image getSlickImage() throws SlickException 
    {
        org.newdawn.slick.Image sheetImage=null;
        sheetImage = new org.newdawn.slick.Image(imagePath);
        
        return sheetImage.getSubImage(32, 64, 32, 32);
    }
    
    public SpriteSheet getSpriteSheet()
    {
        return spriteSheet;
    }
    
    public void setSpriteSheet(SpriteSheet s)
    {
        spriteSheet = s;
    }
    
    public DefaultListModel getSkillsList()
    {
        return skills;
    }
    
    public void setSkillsList(DefaultListModel l)
    {
        skills= l;
    }
    public String getName()
    {
        return name;
    }
    
    public void setName(String newName)
    {
        name = newName;       
    }
    
    public int getBegHP()
    {
        return begHP;
    }
    
    public void setBegHP(int num)
    {
        begHP= num;
    }
    public int getIncreaseHP()
    {
        return increaseHP;
    }
    
    public void setIncreaseHP(int num)
    {
        increaseHP= num;
    }
    

    public void setIncreaseXP(int num)
    {
        increaseXP= num;
    }
    
    public int getIncreaseXP()
    {
        return increaseXP;
    }
    
    
    
    public int getBegSP()
    {
        return begSP;
    }
    
    public void setBegSP(int num)
    {
        begSP= num;
    }
    public int getIncreaseSP()
    {
        return increaseSP;
    }
    
    public void setIncreaseSP(int num)
    {
        increaseSP= num;
    }
    
    public void setType(int num)
    {
        type = num;
    }
    
    public int getType()
    {
        return type;
    }

    @Override
    public Image getImage() {
        return getMainSprite();
    }
    
    @Override
    public int getTile() {
        
        int numberx = 0;
        int numbery = 0;

        //First solve which tile in the x range it is
        numberx=(int)Math.floor(locX/32+0.5);
        //Now solve for the tile in which the y range
        numbery=(int)Math.floor(locY/32+0.5);

        tileNum = (numbery*50 + numberx);

        return tileNum;
    }

    @Override
    public void setTile(int tileNumber) {
        tileNum=tileNumber;
    }
    
}
