/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

/**
 *
 * @author james
 * This class should make it easy to interpret what the events are doing for the 
 * game engine as well as better organize the events.  It should be pretty straight
 * forward.  To set the correct settings for type and action on the events, use the 
 * actual Action object.  So for an NPC speech you can do: 
 * Action action1 = new Action(action1.TYPE_NPC, action1.SPEECH);
 */
public class Action {

    public enum Category{
		POSSE, ENVIRONMENT,NPC
	}
    
    //The following are the different types of actions
    public enum Type{
        //Posse Actions
        BATTLE,MOVE ,ADDCHARACTER , REMOVECHARACTER,
        WARP , GIVEITEM ,TAKEITEM ,GIVESKILL, 
        TAKESKILL , 
        //Evironment Actions
        CHANGEMUSIC ,
        //NPC Actions
        MOVE_NPC ,SHOP ,
        SPEECH 
    }
    private Category category;
    private Type type;
    
    
    //This variable keeps track of the main setting.  For speech input what should be said
    //for movement it should be a direction.  
    private String setting = null;  
    //This variable keeps track of a value needed.  For speech or other actions that don't
    //need it leave it at 0, for something like movement keep track of the amount of spaces 
    private int value = 0;
    
    //This is the string that will be displayed for the particular item in the list
    //Example: Move up 5
    private String displayedValue;
    
    public Action(Category c, Type t) {
        category= c;
        type = t;
    }
    
    @Override
    public String toString()
    {
        return displayedValue;
    }
    
    //Getters
    public String getDisplayedValue()
    {
        return displayedValue;
    }
    
    public Category getActionCategory(String value) {
        return Category.valueOf(value);
    }
    
    public Type getActionType(String value) {
        return Type.valueOf(value);
    }
    
    public String getSetting() {
        return this.setting;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public Type getType(){
        return type;
    }
    
    public Category getCategory(){
        return category;
    }
    
    //Setters
    public void setDisplayedValue(String s)
    {
        displayedValue = s;
    }
    
    public void setSetting(String setting) {
        this.setting = setting;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
}


