/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

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
        JFrame frame = new JFrame ("Another frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        iActor fActor = new iActor();
        
        frame.getContentPane().add(fActor);
        frame.pack();
        //frame.setSize(500, 500);
        frame.setVisible(true);
        // TODO code application logic here
    }
}
