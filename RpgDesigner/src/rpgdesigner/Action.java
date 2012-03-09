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
 * Action action1 = new Action(action1.TYPE_NPC, action1.ACTION_SPEECH);
 */
public class Action {
    private int type;
    //The following are the different types of actions which can be assigned to the type
    public int TYPE_POSSY = 0;
    public int TYPE_ENVIRONMENT = 1;
    public int TYPE_NPC = 2;
    
    private int action;
    //The following are the different actions that can be performed
    //Actions for Possy 0-19
    public int ACTION_BATTLE = 0;
    public int ACTION_MOVE = 1;
    public int ACTION_ADDCHARACTER = 2;
    public int ACTION_REMOVECHARACTER = 3;
    public int ACTION_WARP = 4;
    public int ACTION_GIVEITEM = 5;
    public int ACTION_TAKEITEM = 6;
    public int ACTION_GIVESKILL = 7;
    public int ACTION_TAKESKILL = 8;
    //Actions for Environment 20-39
    public int ACTION_CHANGEMUSIC = 20;
    //Actions for NPC's 40-59
    public int ACTION_MOVE_NPC = 40;
    public int ACTION_SHOP = 41;
    public int ACTION_SPEECH = 42;
    
    //This variable keeps track of the main setting.  For speech input what should be said
    //for movement it should be a direction.  
    private String setting = null;  
    //This variable keeps track of a value needed.  For speech or other actions that don't
    //need it leave it at 0, for something like movement keep track of the amount of spaces 
    private int value = 0;
    
    public Action(int type, int action) {
        this.type = type;
        this.action = action;
    }
    
    public String getSetting() {
        return this.setting;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setSetting(String setting) {
        this.setting = setting;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
}
