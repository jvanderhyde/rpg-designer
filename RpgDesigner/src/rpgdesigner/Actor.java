/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;
import org.newdawn.slick.SpriteSheet;
/**
 *
 * @author Fran
 */
public class Actor {
    private String name;
    private int begHP, increaseHP,  increaseXP, begSP, increaseSP;
    private SpriteSheet spriteSheet;
    //Skill[] skills;
    private int locX, locY;
    private int type;
    
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
    
    /** This should go in the game Actor class
    public void moveRight(int distance)
    {
        locX+=distance;
        //change sprite
        spriteSheet.getSprite(3, 3);
    }
     * */
}
