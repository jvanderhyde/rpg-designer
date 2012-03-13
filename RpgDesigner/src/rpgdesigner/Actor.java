/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;
import javax.swing.DefaultListModel;
import org.newdawn.slick.SpriteSheet;
/**
 *
 * @author Fran
 */
public class Actor {
    private String name;
    private int begHP, increaseHP,  increaseXP, begSP, increaseSP;
    private SpriteSheet spriteSheet;
    
    private String imagePath;
    //Skill[] skills;
    private int type;
    private DefaultListModel skills;
    
    public Actor()
    {
        name="";
        
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
