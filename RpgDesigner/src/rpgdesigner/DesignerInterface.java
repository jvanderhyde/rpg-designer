package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import playTest.GameInterface;
import rpgdesigner.iObjectList.ObjectType;

/**
 *
 * @author james
 * 
 * This class is the main interface class that puts all of the other interface
 * classes together in their own tabs.  
 */
public class DesignerInterface {
    
    
    iObjectList ActorList, MapList, EventList, ItemList;
    iSettings iSettings;
    Game game;
    JFrame frame;
    JOptionPane popup;
    
    public DesignerInterface() throws IOException
    {
        try {
                //Lets change the gui to match the operating system
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RpgDesigner.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(RpgDesigner.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(RpgDesigner.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(RpgDesigner.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            frame = new JFrame ("RPG Designer");
            BufferedImage logo = ImageIO.read(new File("Resources/logo.png"));
            frame.setIconImage(logo);
            game = new Game();
            
            //The Top Menu
            JMenuBar menu = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenuItem saveMenu = new JMenuItem("Save");
            saveMenu.setEnabled(false);
            JMenuItem saveAsMenu = new JMenuItem("Save As");
            saveAsMenu.setEnabled(false);
            JMenuItem exportMenu = new JMenuItem("Export");
            exportMenu.setEnabled(false);
            JMenuItem playTestMenu = new JMenuItem("Play Test");
            playTestMenu.setActionCommand("playtest");
            playTestMenu.addActionListener(new menuListener());
            JMenuItem exitMenu = new JMenuItem("Exit");
            exitMenu.setActionCommand("exit");
            exitMenu.addActionListener(new menuListener());
            fileMenu.add(saveMenu);
            fileMenu.add(saveAsMenu);
            fileMenu.add(exportMenu);
            fileMenu.add(playTestMenu);
            fileMenu.add(exitMenu);
            JMenu editMenu = new JMenu("Edit");
            JMenuItem copyMenu = new JMenuItem("Copy");
            copyMenu.setEnabled(false);
            JMenuItem cutMenu = new JMenuItem("Cut");
            cutMenu.setEnabled(false);
            JMenuItem pasteMenu = new JMenuItem("Paste");
            pasteMenu.setEnabled(false);
            editMenu.add(copyMenu);
            editMenu.add(cutMenu);
            editMenu.add(pasteMenu);
            JMenu helpMenu = new JMenu("Help");
            JMenuItem licenseMenu = new JMenuItem("License");
            licenseMenu.setEnabled(false);
            JMenuItem aboutMenu = new JMenuItem("About");
            aboutMenu.setActionCommand("about");
            aboutMenu.addActionListener(new menuListener());
            helpMenu.add(licenseMenu);
            helpMenu.add(aboutMenu);
            menu.add(fileMenu);
            menu.add(editMenu);
            menu.add(helpMenu);
            
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            game.actorList =  new ArrayList();
            game.eventList = new ArrayList();
            game.mapList = new ArrayList();
            game.itemList = new ArrayList();
           
            ActorList = new iObjectList(game.actorList, frame, ObjectType.ACTOR,  game);
            EventList = new iObjectList(game.eventList, frame, ObjectType.EVENT,   game);
            MapList = new iObjectList(game.mapList, frame, ObjectType.MAP,  game);
            ItemList = new iObjectList(game.itemList, frame, ObjectType.ITEM,  game);
            iSettings = new iSettings(game);
            
            //Lets add our tabs
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            JPanel actorTab = new JPanel();
            actorTab.add(ActorList);
            tabbedPane.addTab("Actors", actorTab);
            JPanel mapTab = new JPanel();
            mapTab.add(MapList);
            tabbedPane.addTab("Map Editor", mapTab);
            JPanel eventTab = new JPanel();
            eventTab.add(EventList);
            tabbedPane.addTab("Events", eventTab);
            JPanel itemTab = new JPanel();
            itemTab.add(ItemList);
            tabbedPane.addTab("Items", itemTab);
            JPanel settingsTab = new JPanel();
            settingsTab.add(iSettings);
            tabbedPane.addTab("Settings", settingsTab);

            
            frame.getContentPane().setLayout(new BorderLayout());
            JPanel content = new JPanel(new BorderLayout());
            content.add(tabbedPane, BorderLayout.NORTH);
            frame.getContentPane().add(menu, BorderLayout.NORTH);
            frame.getContentPane().add(content);
//            JButton test = new JButton("Print Actors");
//            test.addActionListener(new testListener());
//            frame.getContentPane().add(test, BorderLayout.SOUTH);
            frame.setPreferredSize(new Dimension(1146, 750));
            frame.pack();
            frame.setVisible(true);
    }
            
    public static void main(String[] args) { 
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DesignerInterface di = new DesignerInterface();
                } catch (IOException ex) {
                    Logger.getLogger(DesignerInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
    
    private class menuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("playtest")) {
                if(game.mapList.isEmpty())
                    JOptionPane.showMessageDialog(frame, "You must have at least 1 Map to playtest", 
                            "Cannot Continue", JOptionPane.ERROR_MESSAGE);
                else if (game.actorList.isEmpty())
                    JOptionPane.showMessageDialog(frame, "You must have at least 1 actor to playtest", 
                            "Cannot Continue", JOptionPane.ERROR_MESSAGE);    
                    GameInterface.main(game);
            } else if(e.getActionCommand().equals("exit")) {
                System.exit(0);
            } else if(e.getActionCommand().equals("license")) {
                
            } else if(e.getActionCommand().equals("about")) {
                Object toShow = "-RPG Designer-\n" + "Created by: Francine Wolfe and James Harris \n"
                        + "Version: Prototype";
                JOptionPane.showMessageDialog(frame, toShow, "About", JOptionPane.INFORMATION_MESSAGE);
            } else
                System.out.println("Not Yet Implemented");
        }
    }
}
