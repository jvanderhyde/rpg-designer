package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JRadioButton rbSaveEvent, rbSaveAnywhere;
    Game game;
    
    //When opening a previously saved project, us this method and pass in the game
    public iSettings(Game game) {
        
    }
    
    //When creating a new project use this method with default settings
    public iSettings() {
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
        
        //Initialize the global variables to the correct settings
        tbName = new JTextField(20);
        tbDescription = new JTextArea(5, 20);
        tbDescription.setLineWrap(true);
        tbDescription.setWrapStyleWord(true);
        tbAuthor = new JTextField(20);
        cbLoseEvent = new JComboBox(); //TODO: These guys need the list of events when that variable exists
        cbLoseEvent.addItem(new Event());
        cbWinEvent = new JComboBox(); //TODO: These guys need the list of events when that variable exists
        cbWinEvent.addItem(new Event());
        ButtonGroup saveStyleGroup = new ButtonGroup();
        rbSaveEvent = new JRadioButton("Save on Event");
        rbSaveAnywhere = new JRadioButton("Save Anywhere");
        saveStyleGroup.add(rbSaveEvent);
        saveStyleGroup.add(rbSaveAnywhere);
        
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
        
        //Now lets wrap it all up nicely into the iSettingsPanel
        iSettingsPanel.add(nameInfoPanel);
        iSettingsPanel.add(authorInfoPanel);
        iSettingsPanel.add(descInfoPanel);
        iSettingsPanel.add(saveInfoPanel);
        iSettingsPanel.add(winEventInfoPanel);
        iSettingsPanel.add(loseEventInfoPanel);
        
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
        }
    
    }
}
