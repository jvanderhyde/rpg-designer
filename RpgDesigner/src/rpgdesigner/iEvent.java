/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author Fran
 */
public class iEvent extends JPanel implements iListableObject
{
    JTextField tfName;
    JRadioButton rbOnActionKey, rbOnTouch;
    JButton btnRequiredItem, btnNPC;
    JList actions;//table?
    JTabbedPane actionTabs;
    Event event;
    JComboBox cbCommandType;
    JPanel pCommandOptions;
    JList possyEventsList, NPCEventsList, environmentEventsList;
    DefaultListModel listModelActions;
    JFrame mainFrame;
    
    @Override
    public String toString()
    {
        return event.getName();
    }
    @Override
    public void reset() {
        tfName.setText(event.getName());
        
        if (event.onActionKey())
            rbOnActionKey.setSelected(true);
        else 
            rbOnTouch.setSelected(true);
       
        
    }
    
    public iEvent(JFrame frame, Event e) 
    {
        event = e;
        mainFrame = frame;
        this.setLayout(new BorderLayout());
        if (event.getName().compareTo("")!=0)
            tfName = new JTextField(event.getName());
        else
            tfName = new JTextField("Enter Name");
        rbOnActionKey= new JRadioButton("On Action");
        rbOnTouch = new JRadioButton("On Touch");
        if (event.onActionKey())
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
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbOnActionKey);
        bg.add(rbOnTouch);
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
        possyEventsList = new JList(listModelPossy); 
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
        possyEventsList.addMouseListener(new EventMouseListener());
        
        DefaultListModel listModelEnvironment = new DefaultListModel();
        environmentEventsList = new JList(listModelEnvironment);
        listModelEnvironment.addElement("Change Music");
        pCommandOptions.add(environmentEventsList, "Environment");
        
        
        DefaultListModel listModelNPC = new DefaultListModel();
        NPCEventsList = new JList(listModelNPC);
        listModelNPC.addElement("Move");
        listModelNPC.addElement("Shop");
        listModelNPC.addElement("Speech");
        NPCEventsList.addMouseListener(new EventMouseListener());
        pCommandOptions.add(NPCEventsList, "NPC");
        
        
        JPanel pCommands = new JPanel(new BorderLayout());
        pCommands.add(cbCommandType, BorderLayout.NORTH);
        cbCommandType.addItemListener(new EventItemListener());
        
        pCommandOptions.add(new JPanel(), "Blank");
        CardLayout cl = (CardLayout)(pCommandOptions.getLayout());
        //cl.show(pCommandOptions, "Blank");
        pCommands.add(pCommandOptions, BorderLayout.SOUTH);
        pSettings.add(pCommands, BorderLayout.EAST);
        this.add(pSettings, BorderLayout.NORTH);
        listModelActions = new DefaultListModel();
        actions = new JList(listModelActions);
        this.add(actions, BorderLayout.SOUTH);
        
    }
    
    public Event getEvent()
    {
        return event;
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void saveObject() {
        //event.setIcon();
        event.setName(this.tfName.getText());
        event.setOnActionKey(this.rbOnActionKey.isSelected());
        event.setEventListModel(listModelActions);
    }

    @Override
    public void setObject(Object o) {
        event = (Event)o;
        tfName.setText(event.getName());
        if (event.onActionKey())
            rbOnActionKey.setSelected(true);
        else 
            rbOnTouch.setSelected(true);
    }

    @Override
    public Object getObject() {
        return event;
    }

    
    
    
    
    private class EventItemListener implements ItemListener
    {

        @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(pCommandOptions.getLayout());
        cl.show(pCommandOptions, (String)evt.getItem());
        System.out.println((String)evt.getItem());
    }
        
    }
    
    private class EventMouseListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource()==NPCEventsList)
                if (e.getClickCount()>1)
                {
                    String inputValue;
                    if (NPCEventsList.getSelectedValue().equals("Speech"))
                    {
                        inputValue = JOptionPane.showInputDialog("What should the NPC say?"); 
                        listModelActions.addElement("Say " + inputValue);
                    }
                    else if (NPCEventsList.getSelectedValue().equals("Move"))
                    {
                        iEventDialog ied = new iEventDialog(mainFrame, "Move");
                        inputValue = ied.getValue();
                        listModelActions.addElement(inputValue);
                        System.out.println(inputValue);
                    }
                }
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
}
