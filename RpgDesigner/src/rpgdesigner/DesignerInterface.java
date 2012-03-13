package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
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
        
            JFrame frame = new JFrame ("RPG Designer");
            game = new Game();
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            game.actorList =  new ArrayList();
            game.eventList = new ArrayList();
            ArrayList<Object> alMap = new ArrayList();
           
            ActorList = new iObjectList(game.actorList, frame, ObjectType.ACTOR, null, game);
            //MapList = new iObjectList(im, frame, ObjectType.MAP);
            EventList = new iObjectList(game.eventList, frame, ObjectType.EVENT,  game.actorList, game);
            MapList = new iObjectList((ArrayList<Object>)alMap, frame, ObjectType.MAP, null, game);
            iSettings = new iSettings(new Game());
            //game.actorList = new ArrayList();
            //EventList = new iObjectList(ie, frame, ObjectType.EVENT);
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
            frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
            JButton test = new JButton("Print Actors");
            test.addActionListener(new testListener());
            frame.getContentPane().add(test, BorderLayout.SOUTH);
            frame.setPreferredSize(new Dimension(1146, 730));
            
            //frame.getContentPane().add(new JButton("Edit"), BorderLayout.SOUTH);
            frame.pack();
            //frame.setSize(500, 500);
            frame.setVisible(true);
            // TODO code application logic here
            
            
    
        
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
        }
        
    }
    
}
