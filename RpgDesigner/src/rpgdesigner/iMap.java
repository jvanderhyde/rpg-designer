/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
        
        JPanel nameField = new JPanel();
        tfName = new JTextField("Enter Map Name...");
        tfName.setColumns(15);
        tfName.addFocusListener(new IMapListener());
        nameField.add(tfName);
        add(nameField, BorderLayout.WEST);
        
        JPanel controls = new JPanel();
        controls.setLayout(new BorderLayout());
        
        JPanel mapButtons = new JPanel();
        mapButtons.setLayout(new GridLayout(2,15));
        JButton copy, cut;
        btnPlaceTile = new JButton("Place");
        btnFillTile = new JButton("Fill");
        btnFillSquare = new JButton("Selection");
        btnEraseTile = new JButton("Eraser");
        copy = new JButton("Copy");
        cut = new JButton("Cut");
        btnBlock = new JButton("Block");
        btnDBlock = new JButton("DBlock");
        btnLayer1 = new JButton("Layer 1");
        btnLayer2 = new JButton("Layer 2");
        btnLayer3 = new JButton("Layer 3");
        btnLayerEvents = new JButton("Event");
        mapButtons.add(btnPlaceTile);
        mapButtons.add(btnFillTile);
        mapButtons.add(btnFillSquare);
        mapButtons.add(btnEraseTile);
        mapButtons.add(copy);
        mapButtons.add(cut);
        mapButtons.add(btnBlock);
        mapButtons.add(btnDBlock);
        mapButtons.add(btnLayer1);
        mapButtons.add(btnLayer2);
        mapButtons.add(btnLayer3);
        mapButtons.add(btnLayerEvents); 
        controls.add(mapButtons, BorderLayout.WEST);
        
        JPanel optionSelecters = new JPanel();
        optionSelecters.setLayout(new BorderLayout());
        cbEvents = new JComboBox();
            cbEvents.addItem("Event 1");
            cbEvents.addItem("Event 2");
            cbEvents.addItem("Event 3...");
        cbDBlock = new JComboBox();
            cbDBlock.addItem("UP");
            cbDBlock.addItem("DOWN");
            cbDBlock.addItem("LEFT");
            cbDBlock.addItem("RIGHT");
            cbDBlock.addItem("UP + DOWN");
            cbDBlock.addItem("UP + RIGHT");
            cbDBlock.addItem("UP + LEFT");
            cbDBlock.addItem("DOWN + RIGHT");
            cbDBlock.addItem("DOWN + LEFT");
            cbDBlock.addItem("RIGHT + LEFT");
            cbDBlock.addItem("UP + DOWN + RIGHT");
            cbDBlock.addItem("UP + DOWN + LEFT");
            cbDBlock.addItem("UP + RIGHT + LEFT");
            cbDBlock.addItem("DOWN + LEFT + RIGHT");
        optionSelecters.add(cbEvents, BorderLayout.NORTH);
        optionSelecters.add(cbDBlock, BorderLayout.SOUTH);
        controls.add(optionSelecters, BorderLayout.EAST);
        
        add(controls, BorderLayout.CENTER);
    }
    
    private class IMapListener implements FocusListener, ActionListener {

        public IMapListener() {
        }

        @Override
        public void focusGained(FocusEvent e) {
            if(tfName.getText().equals("Enter Map Name..."))
            tfName.setText("");
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(tfName.getText().equals("")) {
                tfName.setText("Enter Map Name...");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
