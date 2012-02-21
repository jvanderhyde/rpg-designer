/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import javax.swing.*;

/**
 *
 * @author Fran
 */
public class iEvent 
{
    JTextField tfName;
    JRadioButton rbOnActionKey;
    JButton btnRequiredItem;
    JList actions;
    Event event;
    
    public iEvent()
    {
        
    }
    public Event getEvent()
    {
        return event;
    }
    
}
