/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 *
 * @author Fran
 */
public class iEvent extends JPanel implements ItemListener
{
    JTextField tfName;
    JRadioButton rbOnActionKey, rbOnTouch;
    JButton btnRequiredItem, btnNPC;
    JList actions;//table?
    JTabbedPane actionTabs;
    Event event;
    JComboBox cbCommandType;
    JPanel pCommandOptions;
    
    public iEvent(Event e) 
    {
        this.setLayout(new BorderLayout());
        if (e.getName().compareTo("")!=0)
            tfName = new JTextField(e.getName());
        else
            tfName = new JTextField("Enter Name");
        rbOnActionKey= new JRadioButton("On Action");
        rbOnTouch = new JRadioButton("On Touch");
        if (e.onActionKey())
            rbOnActionKey.setSelected(true);
        else 
            rbOnTouch.setSelected(true);
        btnRequiredItem = new JButton("Required Item");
        btnNPC = new JButton ("Assigned NPC");
        cbCommandType = new JComboBox();
        cbCommandType.addItem("Possy");
        cbCommandType.addItem("Environment");
        cbCommandType.addItem("NPC");
        JPanel pButtons = new JPanel();
        pButtons.add(btnRequiredItem);
        pButtons.add(btnNPC);
        //pButtons.add(cbCommandType);
        
        JPanel pRBs = new JPanel();
        pRBs.add(rbOnActionKey);
        pRBs.add(rbOnTouch);
        JPanel pCenter = new JPanel(new BorderLayout());
        pCenter.add(pButtons, BorderLayout.NORTH);
        pCenter.add(pRBs, BorderLayout.SOUTH);
        JPanel pSettings = new JPanel(new BorderLayout());
        pSettings.add(pCenter, BorderLayout.CENTER);
        //pSettings.add(pButtons, BorderLayout.EAST);
        JPanel pWest = new JPanel(new BorderLayout());
        
        JPanel pName = new JPanel();
        pName.add(new JLabel("Name: "));
        pName.add(tfName);
        pWest.add(pName, BorderLayout.NORTH);
        pWest.add(new JLabel("image goes here"), BorderLayout.SOUTH);
        pSettings.add(pWest, BorderLayout.WEST);
        pSettings.add(cbCommandType, BorderLayout.EAST);
        
        pCommandOptions = new JPanel(new CardLayout());
        
        DefaultListModel listModelPossy = new DefaultListModel();
        JList possyEventsList = new JList(listModelPossy); 
        listModelPossy.addElement("Battle");
        listModelPossy.addElement("Give Item");
        listModelPossy.addElement("Give Skill");
        listModelPossy.addElement("Remove Character");
        listModelPossy.addElement("Take Item");
        listModelPossy.addElement("Take Skill");
        listModelPossy.addElement("Add Character");
        listModelPossy.addElement("Warp to Location");
        listModelPossy.addElement("Move");
        possyEventsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        pCommandOptions.add(possyEventsList, "Possy");
        
        DefaultListModel listModelEnvironment = new DefaultListModel();
        JList environmentEventsList = new JList(listModelEnvironment);
        listModelEnvironment.addElement("Change Music");
        pCommandOptions.add(environmentEventsList, "Environment");
        
        
        DefaultListModel listModelNPC = new DefaultListModel();
        JList NPCEventsList = new JList(listModelNPC);
        listModelNPC.addElement("Move");
        listModelNPC.addElement("Shop");
        listModelNPC.addElement("Speech");
        pCommandOptions.add(NPCEventsList, "NPC");
        
        
        JPanel pCommands = new JPanel(new BorderLayout());
        pCommands.add(cbCommandType, BorderLayout.NORTH);
        cbCommandType.addItemListener(this);
        
        pCommandOptions.add(new JPanel(), "Blank");
        CardLayout cl = (CardLayout)(pCommandOptions.getLayout());
        //cl.show(pCommandOptions, "Blank");
        pCommands.add(pCommandOptions, BorderLayout.SOUTH);
        pSettings.add(pCommands, BorderLayout.EAST);
        this.add(pSettings, BorderLayout.NORTH);
        actions = new JList();
        this.add(actions, BorderLayout.SOUTH);
        
    }
    public Event getEvent()
    {
        return event;
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(pCommandOptions.getLayout());
        cl.show(pCommandOptions, (String)evt.getItem());
        System.out.println((String)evt.getItem());
    }

    
}
