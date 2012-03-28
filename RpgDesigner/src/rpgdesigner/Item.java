/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

/**
 *
 * @author Fran
 */
public class Item  {
    
    String name, imagePath;
    public enum itemType {SUSTENANCE, KEY, EQUIPMENT}
    itemType type;
    int increaseXP, increaseHP, increaseSP;
    
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
// </editor-fold>
    
    
    
    
    
}
