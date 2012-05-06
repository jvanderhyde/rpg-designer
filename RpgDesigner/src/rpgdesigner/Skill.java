/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

/**
 *
 * @author Fran
 */
public class Skill {
    String imagePath, name;
    int SPUsed, lvlReq, damage;
    
    public Skill() {
        
    }
    
    public Skill(String skillName, String path, int SP, int lvl, int dmg)
    {
        name = skillName;
        imagePath = path;
        SPUsed = SP;
        lvlReq = lvl;
        damage = dmg;
        
    }
    
    public String getImagePath()
    {
        return imagePath;
    }
    
    public void setImagePath(String path)
    {
        imagePath= path;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String newName)
    {
        name = newName;
    }
    
    public int getSPUsed()
    {
        return SPUsed;
    }
    
    public void setSPUsed(int SP)
    {
        SPUsed = SP;
    }
    
    public int getLvlReq()
    {
        return lvlReq;
    }
    
    public void setlvlReq(int level)
    {
        lvlReq = level;
    }
    public int getDamage()
    {
        return damage;
    }
    
    public void setdamage(int d)
    {
        damage = d;
    }
    
}
