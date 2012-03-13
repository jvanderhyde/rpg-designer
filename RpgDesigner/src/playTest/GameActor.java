/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playTest;

import org.newdawn.slick.SpriteSheet;
import rpgdesigner.*;

/**
 *
 * @author Fran
 */
public class GameActor {
    
    private String name;
    private int currentHP, maxHP, increaseHP, currentXP,  xpForLvlUp, currentSP, increaseSP;
    private SpriteSheet spriteSheet;
    private int locX, locY;
    private int type;
    private Skill[] skills;
    
    public GameActor(Actor a)
    {
        name = a.getName();
        currentHP = a.getBegHP();
        maxHP = a.getBegHP();
        increaseHP = a.getIncreaseHP();
        currentXP = 0;
        xpForLvlUp = a.getIncreaseXP();
        currentSP = a.getBegSP();
        increaseSP = a.getIncreaseSP();
        spriteSheet = a.getSpriteSheet();
        locX =0;
        locY=0;
        type = a.getType();
        skills = (Skill[])a.getSkillsList().toArray();
    }
    
    public int getType()
    {
        return type;
    }
    
    public Skill[] getSkills()
    {
        return skills;
    }
    public int getLocX()
    {
        return locX;
    }
    
    public int getLocY()
    {
        return locY;
    }
    
    public int getSP()
    {
        return currentSP;
    }
    
    public int getMaxHP ()
    {
        return maxHP;
    }
    
    public int getXP()
    {
        return currentXP;
    }
    
    public String getName()
    {
        return name;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    
    public int getHP()
    {
        return currentHP;
    }
    
    public void move (int x, int y)
    {
        locX+=x;
        locY+=y;
    }
    
    public void levelUP(int numLevels)
    {
        maxHP+=increaseHP*numLevels;
        currentHP = maxHP;
        currentSP += increaseSP;
    }
    
}
