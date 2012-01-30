/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author james
 */
public class DesignerInterface {
    
    public static void main(String[] args) {
        
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

            iActor fActor = new iActor();
            iMap iMap = new iMap();
            
            //Lets add our tabs
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            JPanel actorTab = new JPanel();
            actorTab.add(fActor);
            tabbedPane.addTab("Actors", actorTab);
            JPanel mapTab = new JPanel();
            mapTab.add(iMap);
            tabbedPane.addTab("Map Editor", mapTab);

            frame.getContentPane().add(tabbedPane);
            frame.pack();
            //frame.setSize(500, 500);
            frame.setVisible(true);
            // TODO code application logic here
    }
}
