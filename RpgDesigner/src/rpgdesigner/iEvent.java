/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author Fran
 */
public class iEvent extends JPanel
{
    JTextField tfName;
    JRadioButton rbOnActionKey, rbOnTouch;
    JButton btnRequiredItem, btnNPC;
    JList actions;//table?
    JTabbedPane actionTabs;
    Event event;
    
    public iEvent(Event e) 
    {
        tfName = new JTextField(e.getName());
        rbOnActionKey= new JRadioButton("On Action");
        rbOnTouch = new JRadioButton("On Touch");
        if (e.onActionKey())
            rbOnActionKey.setSelected(true);
        else 
            rbOnTouch.setSelected(true);
        btnRequiredItem = new JButton("Required Item");
        btnNPC = new JButton ("Assigned NPC");
        
        JPanel pButtons = new JPanel();
        pButtons.add(btnRequiredItem);
        pButtons.add(btnNPC);
        
        JPanel pRBs = new JPanel();
        pRBs.add(rbOnActionKey);
        pRBs.add(rbOnTouch);
        
        JPanel pSettings = new JPanel(new BorderLayout());
        pSettings.add(pRBs, BorderLayout.SOUTH);
        pSettings.add(pButtons, BorderLayout.EAST);
        pSettings.add(tfName, BorderLayout.NORTH);
        pSettings.add(new JLabel("image goes here"), BorderLayout.WEST);
        
        this.add(pSettings);
    }
    public Event getEvent()
    {
        return event;
    }
    
}
