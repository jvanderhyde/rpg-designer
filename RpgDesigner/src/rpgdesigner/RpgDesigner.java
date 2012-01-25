/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author james
 */
public class RpgDesigner {

    /**
     * @param args the command line arguments
     */
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
        JFrame frame = new JFrame ("RPG!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        iActor fActor = new iActor();
        
        frame.getContentPane().add(fActor);
        frame.pack();
        //frame.setSize(500, 500);
        frame.setVisible(true);
        // TODO code application logic here
    }
}
