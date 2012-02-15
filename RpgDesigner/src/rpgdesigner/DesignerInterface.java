/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.*;

import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author james
 */
public class DesignerInterface {
    
    
    iObjectList ActorList;
    
    
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
            iMap iMap = new iMap();
            
            Actor[] a = new Actor[3];
            iActor[] ia = new iActor[3];
            //make fake actors
            a[0]= new Actor();
            a[0].setName("buttercup");
            a[0].setType(1);
            ia[0] = new iActor(frame, a[0]);
            
            a[1]= new Actor();
            a[1].setName("bob");
            a[1].setType(1);
            ia[1] = new iActor(frame, a[1]);
            
            a[2]= new Actor();
            a[2].setName("larry");
            a[2].setType(1);
            ia[2] = new iActor(frame, a[2]);
            
            
            
            ActorList = new iObjectList(a, frame);
            
            //Lets add our tabs
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            JPanel actorTab = new JPanel();
            actorTab.add(ActorList);
            tabbedPane.addTab("Actors", actorTab);
            JPanel mapTab = new JPanel();
            mapTab.add(iMap);
            tabbedPane.addTab("Map Editor", mapTab);

            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
            
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
