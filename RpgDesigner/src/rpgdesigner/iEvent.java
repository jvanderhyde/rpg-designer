/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Fran
 */
public class iEvent extends JPanel implements iListableObject
{
    private JTextField tfName;
    private JRadioButton rbOnActionKey, rbOnTouch;
    private JButton btnRequiredItem, btnNPC;
    private JList actions;//table?
    private JTabbedPane actionTabs;
    private Event event;
    private JComboBox cbCommandType;
    private JPanel pCommandOptions;
    private JList posseEventsList, NPCEventsList, environmentEventsList;
    private DefaultListModel listModelActions;
    private JFrame mainFrame;
    private List<Object> actorList;
    private JLabel reqItemImg, npcImg;
    private String actorImgPath, itemImgPath;
    private Actor assignedNPC;
    JPanel pNPC ;
        JPanel pItem ;
    //private JPanel pImages;
    private boolean invalidInput;
    
    @Override
    public String toString()
    {
        return event.getName();
    }
    @Override
    public void reset() {
        invalidInput = false;
        event = new Event();
        setObject(event);
        tfName.setColumns(20);
        //tfName.setText("Enter name...");
        rbOnActionKey.setSelected(true);
        listModelActions.removeAllElements();
        
        //reset images
        npcImg = new JLabel();
        pItem.remove(reqItemImg);
        pNPC.remove(npcImg);
        reqItemImg = new JLabel();
        npcImg.setPreferredSize(new Dimension(75,75));
        reqItemImg.setPreferredSize(new Dimension(75,75));
        pItem.add(reqItemImg, BorderLayout.NORTH);
        //pImages.removeAll();
        
    }
    
    public iEvent(JFrame frame, Event e, List<Object> actorList) 
    {
        this.actorList = actorList;
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
        btnNPC.addActionListener(new EventActionListener());
        cbCommandType = new JComboBox();
        cbCommandType.addItem("Posse");
        cbCommandType.addItem("Environment");
        cbCommandType.addItem("NPC");
        JPanel pButtons = new JPanel();
        pButtons.add(btnRequiredItem);
        pButtons.add(btnNPC);
        //pButtons.add(cbCommandType);
        pNPC = new JPanel(new BorderLayout());
        pItem = new JPanel(new BorderLayout());
        //pImages = new JPanel(new BorderLayout());
        npcImg = new JLabel();
        pNPC.add(npcImg, BorderLayout.NORTH);
        pNPC.add(btnNPC, BorderLayout.SOUTH);
        reqItemImg = new JLabel();
        npcImg.setPreferredSize(new Dimension(75,75));
        reqItemImg.setPreferredSize(new Dimension(75,75));
        
        pItem.add(reqItemImg, BorderLayout.NORTH);
        pItem.add(btnRequiredItem, BorderLayout.SOUTH);
        //pImages.add(reqItemImg, BorderLayout.WEST);
        //pImages.add(npcImg, BorderLayout.EAST);
        
        JPanel pRBs = new JPanel();
        pRBs.add(rbOnActionKey);
        pRBs.add(rbOnTouch);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbOnActionKey);
        bg.add(rbOnTouch);
        JPanel pCenter = new JPanel(new BorderLayout());
        pCenter.add(pItem, BorderLayout.WEST);
        pCenter.add(pNPC, BorderLayout.EAST);
        pCenter.add(pRBs, BorderLayout.SOUTH);
        JPanel pSettings = new JPanel(new BorderLayout());
        pSettings.add(pCenter, BorderLayout.CENTER);
        //pSettings.add(pButtons, BorderLayout.EAST);
        JPanel pWest = new JPanel(new BorderLayout());
        
        JPanel pName = new JPanel();
        pName.add(new JLabel("Name: "));
        pName.add(tfName);
        pWest.add(pName, BorderLayout.NORTH);
        pSettings.add(pWest, BorderLayout.WEST);
        //pSettings.add(cbCommandType, BorderLayout.EAST);
        
        pCommandOptions = new JPanel(new CardLayout());
        
        DefaultListModel listModelPosse = new DefaultListModel();
        posseEventsList = new JList(listModelPosse); 
        listModelPosse.addElement("Battle");
        listModelPosse.addElement("Give Item");
        listModelPosse.addElement("Give Skill");
        listModelPosse.addElement("Remove Character");
        listModelPosse.addElement("Take Item");
        listModelPosse.addElement("Take Skill");
        listModelPosse.addElement("Add Character");
        listModelPosse.addElement("Warp to Location");
        listModelPosse.addElement("Move");
        posseEventsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        pCommandOptions.add(posseEventsList, "Posse");
        posseEventsList.addMouseListener(new EventMouseListener());
        
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
        event.setAssignedNPC(assignedNPC);
    }

    @Override
    public void setObject(Object o) {
        event = (Event)o;
        tfName.setText(event.getName());
        if (event.onActionKey())
            rbOnActionKey.setSelected(true);
        else 
            rbOnTouch.setSelected(true);
        listModelActions = event.getEventListModel();
        if(listModelActions== null)
            listModelActions=new DefaultListModel();
        actions.setModel(listModelActions);
        showNpcImage(event.getAssignedNPC());
    }

    @Override
    public Object getObject() {
        return event;
    }

    @Override
    public boolean hasInvalidInput() {
        return invalidInput;
    }
    
    private void showNpcImage(Actor a) {
            assignedNPC =a;
            if(assignedNPC!=null)
            {
                actorImgPath = assignedNPC.getImagePath();
                BufferedImage myPicture;
                try {
                    myPicture = ImageIO.read(new File(actorImgPath));
                    pNPC.remove(npcImg);
                    npcImg = new JLabel(new ImageIcon( myPicture ));
                    npcImg.setPreferredSize(new Dimension(75,75));
                    pNPC.add(npcImg, BorderLayout.NORTH);
                    mainFrame.pack();
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            }
            else
            {
                actorImgPath="";
                pNPC.remove(npcImg);
                npcImg = new JLabel();
                npcImg.setPreferredSize(new Dimension(75,75));
                pNPC.add(npcImg, BorderLayout.NORTH);
                mainFrame.pack();
            }
            
        }

    
    
    
    
    private class EventItemListener implements ItemListener
    {

        @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(pCommandOptions.getLayout());
        cl.show(pCommandOptions, (String)evt.getItem());
    }
        
    }
    
    private class EventMouseListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            Action a;
            if (e.getClickCount()>1)
                
                if (e.getSource()==NPCEventsList)
                {
                    
                    
                    if (NPCEventsList.getSelectedValue().equals("Speech"))
                    {
                        String inputValue;
                        a = new Action(Action.Category.NPC, Action.Type.SPEECH);
                        inputValue = JOptionPane.showInputDialog("What should the NPC say?"); 
                        a.setSetting(inputValue);
                        a.setDisplayedValue("Say "+inputValue);
                        listModelActions.addElement(a);
                    }
                    else if (NPCEventsList.getSelectedValue().equals("Move"))
                    {
                        iActionDialog ied = new iActionDialog(mainFrame,Action.Category.NPC,
                                Action.Type.MOVE_NPC);
                        a = ied.getValue();
                        if (a!=(rpgdesigner.Action)null)
                            listModelActions.addElement(a);
                        //System.out.println(inputValue);
                    }
                }
                else if (e.getSource()==posseEventsList)
                    if (posseEventsList.getSelectedValue().equals("Move"))
                    {
                        iActionDialog ied = new iActionDialog(mainFrame,Action.Category.POSSE,
                                Action.Type.MOVE);
                        a = ied.getValue();
                        if (a!=(rpgdesigner.Action)null)
                            listModelActions.addElement(a);
                        //System.out.println(inputValue);
                    }
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
    
    private class EventActionListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()== btnNPC)
            {
                List<Actor> actors = new ArrayList();
                for (Object a : actorList)
                {
                    Actor actor = (Actor)a;
                    
                    //don't include playable characters
                    if (actor.getType()!=0)
                    {
                        actors.add(actor);
                    }
                }
                Object[] possibilities = actors.toArray();
                Actor a = (Actor)JOptionPane.showInputDialog(
                mainFrame,
                "Choose an Actor",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                null);
                showNpcImage(a);
            }
        }

        
        
    }
}
