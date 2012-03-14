package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.MenuListener;
import org.newdawn.slick.SlickException;
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
    
    
    iObjectList ActorList, MapList, EventList;
    iSettings iSettings;
    Game game;
    JFrame frame;
    JOptionPane popup;
    
    public DesignerInterface()
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
           
            ActorList = new iObjectList(game.actorList, frame, ObjectType.ACTOR, null, game);
            EventList = new iObjectList(game.eventList, frame, ObjectType.EVENT,  game.actorList, game);
            MapList = new iObjectList(game.mapList, frame, ObjectType.MAP, null, game);
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
            JPanel eventsTab = new JPanel();
            eventsTab.add(EventList);
            tabbedPane.addTab("Events", eventsTab);
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
                DesignerInterface di = new DesignerInterface(); 
            }
        });
    }
    
    private class testListener  implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(game.actorList.size());
            for (Object a: game.actorList)
                System.out.println(a);
            for (Object a: game.eventList)
                System.out.println(a);
            for(Object a: game.mapList)
                System.out.println(a);
        }
        
    }
    
    private class menuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("playtest")) {
                if(game.mapList.isEmpty())
                    JOptionPane.showMessageDialog(frame, "You must have at least 1 Map to playtest", 
                            "Cannot Continue", JOptionPane.ERROR_MESSAGE);
                else
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
