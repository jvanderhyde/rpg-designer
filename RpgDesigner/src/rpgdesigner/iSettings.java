package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author james
 * 
 * This class extends a JPanel to create everything in the settings tab of the software.
 * This tab allows for the user to set and change the name of the game, the author, 
 * a description, when his players can save the game, and events for Game Over.  
 */
public class iSettings extends JPanel{
    private JTextField tbName;
    private JTextArea tbDescription;
    private JTextField tbAuthor;
    private JComboBox cbLoseEvent;
    private JComboBox cbWinEvent;
    private JComboBox cbStartingMap;
    private JTextField tbStartingPositionX;
    private JTextField tbStartingPositionY;
    private JComboBox cbStartingActor1;
    private JComboBox cbStartingActor2;
    private JComboBox cbStartingActor3;
    private JRadioButton rbSaveEvent, rbSaveAnywhere;
    Game game;
    JFrame frame;
    private ButtonGroup saveStyleGroup;
    
    //When creating a new project use this method with default settings
    public iSettings(Game game, JFrame programFrame) {
        this.game = game;
        frame = programFrame;
        this.setLayout(new BorderLayout());
        JPanel iSettingsPanel = new JPanel();
        iSettingsPanel.setLayout(new BoxLayout(iSettingsPanel, BoxLayout.Y_AXIS));
        
        //Create the necessary labels
        JLabel nameField = new JLabel("Game Name: ");
        JLabel descriptionField = new JLabel("  Description: ");
        JLabel authorField = new JLabel("         Author: ");
        JLabel saveType = new JLabel("Save Style:     ");
        JLabel loseEventField = new JLabel("Losing Event: ");
        JLabel winEventField = new JLabel("Winning Event: ");
        JLabel startMapField = new JLabel("Starting Map: ");
        JLabel startPositionField = new JLabel("Starting Position(x,y): ");
        JLabel startPosseField = new JLabel("Starting Actors: \n"
                + "(the first will display when walking on map)");
        
        //Initialize the global variables to the correct settings
        tbName = new JTextField(20);
        tbDescription = new JTextArea(5, 20);
        tbDescription.setLineWrap(true);
        tbDescription.setWrapStyleWord(true);
        tbAuthor = new JTextField(20);
        cbLoseEvent = new JComboBox();
        cbWinEvent = new JComboBox();
        saveStyleGroup = new ButtonGroup();
        rbSaveEvent = new JRadioButton("Save on Event");
        rbSaveAnywhere = new JRadioButton("Save Anywhere");
        saveStyleGroup.add(rbSaveEvent);
        saveStyleGroup.add(rbSaveAnywhere);
        cbStartingMap = new JComboBox();
        tbStartingPositionX = new JTextField();
        tbStartingPositionY = new JTextField();
        tbStartingPositionX.setText("0");
        tbStartingPositionY.setText("0");
        cbStartingActor1 = new JComboBox();
        cbStartingActor2 = new JComboBox();
        cbStartingActor3 = new JComboBox();
        
        //Put the groups of elements that belong together in a JPanel for layout purposes
        JPanel nameInfoPanel = new JPanel();
        nameInfoPanel.add(nameField);
        nameInfoPanel.add(tbName);
        JPanel descInfoPanel = new JPanel();
        descInfoPanel.add(descriptionField);
        descInfoPanel.add(tbDescription);
        JPanel authorInfoPanel = new JPanel();
        authorInfoPanel.add(authorField);
        authorInfoPanel.add(tbAuthor);
        JPanel saveInfoPanel = new JPanel();
        saveInfoPanel.add(saveType);
        saveInfoPanel.add(rbSaveEvent);
        saveInfoPanel.add(rbSaveAnywhere);
        JPanel loseEventInfoPanel = new JPanel();
        loseEventInfoPanel.add(loseEventField);
        loseEventInfoPanel.add(cbLoseEvent);
        JPanel winEventInfoPanel = new JPanel();
        winEventInfoPanel.add(winEventField);
        winEventInfoPanel.add(cbWinEvent);
        JPanel mapInfoPanel = new JPanel(new BorderLayout());
        JPanel mapNamePanel = new JPanel();
        mapNamePanel.add(startMapField);
        mapNamePanel.add(cbStartingMap);
        JPanel mapStartPanel = new JPanel();
        mapStartPanel.add(startPositionField);
        mapStartPanel.add(tbStartingPositionX);
        mapStartPanel.add(tbStartingPositionY);
        mapInfoPanel.add(mapNamePanel, BorderLayout.CENTER);
        mapInfoPanel.add(mapStartPanel, BorderLayout.SOUTH);
        JPanel possePanel = new JPanel();
        possePanel.setLayout(new BoxLayout(possePanel, BoxLayout.Y_AXIS));
        possePanel.add(startPosseField);
        JPanel actorsPanel1 = new JPanel();
        JPanel actorsPanel2 = new JPanel();
        JPanel actorsPanel3 = new JPanel();
        actorsPanel1.add(new JLabel("Actor 1: "));
        actorsPanel1.add(cbStartingActor1);
        actorsPanel2.add(new JLabel("Actor 2: "));
        actorsPanel2.add(cbStartingActor2);
        actorsPanel3.add(new JLabel("Actor 3: "));
        actorsPanel3.add(cbStartingActor3);
        possePanel.add(actorsPanel1);
        possePanel.add(actorsPanel2);
        possePanel.add(actorsPanel3);
        
        
        //Now lets wrap it all up nicely into the iSettingsPanel
        iSettingsPanel.add(nameInfoPanel);
        iSettingsPanel.add(authorInfoPanel);
        iSettingsPanel.add(descInfoPanel);
        iSettingsPanel.add(saveInfoPanel);
        iSettingsPanel.add(winEventInfoPanel);
        iSettingsPanel.add(loseEventInfoPanel);
        iSettingsPanel.add(mapInfoPanel);
        iSettingsPanel.add(possePanel);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new iSettingsListener());
        
        this.add(iSettingsPanel, BorderLayout.NORTH);
        this.add(saveButton, BorderLayout.WEST);
    }
    
    private class iSettingsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            game.setGameName(tbName.getText());
            game.setDescription(tbDescription.getText());
            game.setAuthor(tbAuthor.getText());
            if(rbSaveAnywhere.isSelected()) 
                game.setIsSaveAnywhere(true);
            else
                game.setIsSaveAnywhere(false);
            game.setWinEvent((Event)cbWinEvent.getSelectedItem());
            game.setLoseEvent((Event)cbLoseEvent.getSelectedItem());
            game.setStartMap((Map)cbStartingMap.getSelectedItem());
            game.setStartPosition(tbStartingPositionX.getText(), tbStartingPositionY.getText());
            List<Actor> posseList = new ArrayList();
            posseList.add((Actor)cbStartingActor1.getSelectedItem());
            posseList.add((Actor)cbStartingActor2.getSelectedItem());
            posseList.add((Actor)cbStartingActor3.getSelectedItem());
            game.setStartPosse(posseList);
        }
    
    }
    
    public void updateMe() {
        cbStartingMap.removeAllItems();
        cbStartingActor1.removeAllItems();
        cbStartingActor2.removeAllItems();
        cbStartingActor3.removeAllItems();
        cbLoseEvent.removeAllItems();
        cbWinEvent.removeAllItems();
        for(Object m : game.mapList)
            cbStartingMap.addItem((Map)m);
        for(Object a : game.actorList) {
            cbStartingActor1.addItem(a);
            cbStartingActor2.addItem(a);
            cbStartingActor3.addItem(a);
        }
        for(Object e : game.eventList) {
            cbLoseEvent.addItem((Event)e);
            cbWinEvent.addItem((Event)e);
        }
        tbName.setText(game.getGameName());
        tbDescription.setText(game.getDescription());
        tbAuthor.setText(game.getAuthor());
        if(game.getLoseEvent() != null)
            cbLoseEvent.setSelectedItem(game.getLoseEvent());
        if(game.getWinEvent() != null)
            cbWinEvent.setSelectedItem(game.getWinEvent());
        rbSaveEvent = new JRadioButton("Save on Event");
        rbSaveAnywhere = new JRadioButton("Save Anywhere");
        if(game.getIsSaveAnywhere())
            saveStyleGroup.setSelected(rbSaveAnywhere.getModel(), true);
        else
            saveStyleGroup.setSelected(rbSaveEvent.getModel(), true);
        if(game.getStartMap() != null)
            cbStartingMap.setSelectedItem(game.getStartMap());
        tbStartingPositionX.setText(game.getStartPositionX());
        tbStartingPositionY.setText(game.getStartPositionY());
        if(game.getStartPosse() != null) {
            cbStartingActor1.setSelectedItem(game.getStartPosse().getActors().get(0));
            cbStartingActor2.setSelectedItem(game.getStartPosse().getActors().get(1));
            cbStartingActor3.setSelectedItem(game.getStartPosse().getActors().get(2));
        }
        this.repaint();
        frame.pack();
    }
}
