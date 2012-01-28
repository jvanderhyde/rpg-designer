/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author james
 */
public class iMap extends JPanel{
    private JTextField tfName;
    private JButton btnPlaceTile;
    private JButton btnFillTile;
    private JButton btnFillSquare;
    private JButton btnEraseTile;
    private JButton btnLayer1;
    private JButton btnLayer2;
    private JButton btnLayer3;
    private JButton btnLayerEvents;
    private JButton btnBlock;
    private JButton btnDBlock;
    private JComboBox cbDBlock;
    private JComboBox cbEvents;
    private JComboBox cbTileSet;
    private Map workingMap;
    
    public iMap() {
        this.setLayout(new BorderLayout());
        
        tfName = new JTextField("Input a map name");
        tfName.setColumns(20);
        add(tfName, BorderLayout.WEST);
        
        JPanel mapButtons = new JPanel();
        mapButtons.setLayout(new GridLayout(3,20));
    }
}
