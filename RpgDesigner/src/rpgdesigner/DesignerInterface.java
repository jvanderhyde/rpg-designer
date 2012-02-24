package rpgdesigner;

import java.awt.BorderLayout;
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
    
    
    iObjectList ActorList, MapList;
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
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //iActor fActor = new iActor(frame);
            iMap iMap = new iMap(new Map());
            iSettings iSettings = new iSettings();
            
            iActor[] ia = new iActor[0];
            iMap[] im = new iMap[0];
            
            
           
            ActorList = new iObjectList(ia, frame, ObjectType.ACTOR);
            MapList = new iObjectList(im, frame, ObjectType.MAP);
            //Lets add our tabs
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            JPanel actorTab = new JPanel();
            actorTab.add(ActorList);
            tabbedPane.addTab("Actors", actorTab);
            JPanel mapTab = new JPanel();
            mapTab.add(MapList);
            tabbedPane.addTab("Map Editor", mapTab);
            JPanel settingsTab = new JPanel();
            settingsTab.add(iSettings);
            tabbedPane.addTab("Settings", settingsTab);
            tabbedPane.addTab("Events", new iEvent(new Event()));

            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
            
            frame.setSize(600, 400);
            
            //frame.getContentPane().add(new JButton("Edit"), BorderLayout.SOUTH);
            frame.pack();
            //frame.setSize(500, 500);
            frame.setVisible(true);
            // TODO code application logic here
            
            
    
        
    }
            
    
    public static void main(String[] args) {
        DesignerInterface di = new DesignerInterface(); 
    }
}
