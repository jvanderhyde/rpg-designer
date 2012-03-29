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
//import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
/**
 *
 * @author Fran
 */
public class Actor {
    private String name;
    private int begHP, increaseHP,  increaseXP, begSP, increaseSP;
    private SpriteSheet spriteSheet;
    private float locX, locY;
    private String imagePath;
    private int type;
    private DefaultListModel skills;
    private int tileX, tileY;
    
    public Actor()
    {
        name="";
        
    }
    
    public void setLocation(int x, int y)
    {
        locX=x;
        locY=y;
    }
    
    public void setTile(int x, int y)
    {
        tileX = x;
        tileY = y;
    }
    
    public float getLocX()
    {
        return locX;
    }
    
    public float getLocY()
    {
        return locY;
    }
    
    public int getNewLocTile(double x, double y)
    {
        int number;
        int numberx = 0;
        int numbery = 0;

        //First solve which tile in the x range it is
        for(int i=0; i < (int)locX+x; i = i+32) {
            numberx++;
        }
        numberx--;
        //Now solve for the tile in which the y range
        for(int i=0; i < (int)locY+y; i = i+32) {
            numbery++;
        }
        numbery--;

        number = (numbery*50 + numberx);

        return number;
    }
    
    public void move (double x, double y)
    {
        locX+=x;
        locY+=y;
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
    
    public Image getMainSprite() 
    {
        BufferedImage sheetImage=null;
        try {
            sheetImage = ImageIO.read(new File(this.imagePath));
        } catch (IOException ex) {
            Logger.getLogger(iMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sheetImage.getSubimage(32, 0, 32, 32);
//        SpriteSheet sheet = new SpriteSheet(sheetImage, 32, 32);
//        return sheet.getSprite(0, 0);
    }
    public org.newdawn.slick.Image getMainSlickSprite() throws SlickException 
    {
        org.newdawn.slick.Image sheetImage=null;
        sheetImage = new org.newdawn.slick.Image(imagePath);
        
        return sheetImage.getSubImage(32, 0, 32, 32);
//        SpriteSheet sheet = new SpriteSheet(sheetImage, 32, 32);
//        return sheet.getSprite(0, 0);
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
    
}
