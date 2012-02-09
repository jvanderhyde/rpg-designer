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
import java.awt.image.BufferedImage;
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
    private JComboBox cbDBlock;
    private JComboBox cbEvents;
    private JComboBox cbTileSet;
    private Map workingMap;
    private Image workingLayer;
    private JButton copy, cut;
    
   /*
    * This is the constructor, it always requires a map.  If the map is blank create a new Map without 
    * any parameters and pass it in.  
    */ 
    public iMap(Map workingMap) {
        this.workingMap = workingMap;
        this.setLayout(new BorderLayout());
        ActionListener buttonActions = new IMapListener();
        FocusListener textBoxListener = new IMapListener();
        
        //This is the name field for the map
        JPanel nameField = new JPanel();
        tfName = new JTextField("Enter Map Name...");
        tfName.setForeground(Color.gray);
        tfName.setColumns(15);
        tfName.addFocusListener(textBoxListener);
        nameField.add(tfName);
        add(nameField, BorderLayout.WEST);
        
        //This panel is used to hold the different buttons for the map editor
        JPanel controls = new JPanel();
        controls.setLayout(new BorderLayout());
        JPanel mapButtons = new JPanel();
        mapButtons.setLayout(new GridLayout(2,15));
        btnPlaceTile = new JButton("Place");
        btnPlaceTile.addActionListener(buttonActions);
        btnFillTile = new JButton("Fill");
        btnFillTile.addActionListener(buttonActions);
        btnFillSquare = new JButton("Selection");
        btnFillSquare.addActionListener(buttonActions);
        btnEraseTile = new JButton("Eraser");
        btnEraseTile.addActionListener(buttonActions);
        copy = new JButton("Copy");
        copy.addActionListener(buttonActions);
        cut = new JButton("Cut");
        cut.addActionListener(buttonActions);
        btnBlock = new JButton("Block");
        btnBlock.addActionListener(buttonActions);
        btnLayer1 = new JButton("Layer 1");
        btnLayer1.addActionListener(buttonActions);
        btnLayer2 = new JButton("Layer 2");
        btnLayer2.addActionListener(buttonActions);
        btnLayer3 = new JButton("Layer 3");
        btnLayer3.addActionListener(buttonActions);
        btnLayerEvents = new JButton("Event");
        btnLayerEvents.addActionListener(buttonActions);
        JLabel blankLabel = new JLabel("");
        cbEvents = new JComboBox();
        cbEvents.addItem("Event 1");
        cbEvents.addItem("Event 2");
        cbEvents.addItem("Event 3...");
        mapButtons.add(btnPlaceTile);
        mapButtons.add(btnFillTile);
        mapButtons.add(btnFillSquare);
        mapButtons.add(btnEraseTile);
        mapButtons.add(copy);
        mapButtons.add(cut);
        mapButtons.add(btnBlock);
        mapButtons.add(btnLayer1);
        mapButtons.add(btnLayer2);
        mapButtons.add(btnLayer3);
        mapButtons.add(btnLayerEvents); 
        mapButtons.add(cbEvents);
        mapButtons.add(new JLabel(" "));
        mapButtons.add(new JLabel(" "));
        controls.add(mapButtons, BorderLayout.WEST);
        add(controls, BorderLayout.CENTER);
        
        //This code creates the view of the map being edited as well as the 
        //tilesetview
        JPanel mapBody = new JPanel();
        JScrollPane mapViewScroll = new JScrollPane();
        JPanel mapView = new JPanel();
        mapView.setLayout(new OverlayLayout(mapView));
        ImageIcon layer1 = new ImageIcon(workingMap.getLayer1());
        ImageIcon layer2 = new ImageIcon(workingMap.getLayer2());
        ImageIcon layer3 = new ImageIcon(workingMap.getLayer3());
        BufferedImage grid = new BufferedImage(1600,1600,1);
        Graphics2D gridToDraw = grid.createGraphics();
        gridToDraw.setColor(Color.red);
        for (int x=0; x<1600; x+=32) {gridToDraw.drawLine(x, 0, x, 1600);}
        for (int y=0; y<1600; y+=32) {gridToDraw.drawLine(0, y, 1600, y);}
        ImageIcon mapGrid = new ImageIcon(grid);
        JLabel layer1Label = new JLabel(layer1);
        JLabel layer2Label = new JLabel(layer2);
        JLabel layer3Label = new JLabel(layer3);
        JLabel mapGridLabel = new JLabel(mapGrid);
        mapView.add(mapGridLabel);
        mapView.add(layer1Label);
        mapView.add(layer2Label);
        mapView.add(layer3Label);
        mapViewScroll.getViewport().add(mapView);
        mapViewScroll.setPreferredSize(new Dimension(800, 500));
        JPanel tileView = new JPanel();
        ImageIcon tileset = new ImageIcon(new BufferedImage(800,500,1));
        JLabel tilesetLabel = new JLabel();
        tilesetLabel.setIcon(tileset);
        tileView.add(tilesetLabel);
        JScrollPane tilesetScroll = new JScrollPane();
        tilesetScroll.getViewport().add(tileView);
        tilesetScroll.setPreferredSize(new Dimension(300, 500));
        mapBody.add(mapViewScroll, BorderLayout.WEST);
        mapBody.add(tilesetScroll, BorderLayout.EAST);
        add(mapBody, BorderLayout.SOUTH);
    }
    
    private class IMapListener implements FocusListener, ActionListener {

        public IMapListener() {
        }

        @Override
        public void focusGained(FocusEvent e) {
            if(tfName.getText().equals("Enter Map Name...")) {
                tfName.setText("");
                tfName.setForeground(Color.black);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(tfName.getText().equals("")) {
                tfName.setText("Enter Map Name...");
                tfName.setForeground(Color.gray);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //This code checks which button was clicked and performs the correct operation
            if(e.getActionCommand().equals("Place")) {
                System.out.println("Placing a tile now.");
            } 
            else if(e.getActionCommand().equals("Fill")) {
                
            }
            else if(e.getActionCommand().equals("Selection")) {
                
            }
            else if(e.getActionCommand().equals("Eraser")) {
                
            }
            else if(e.getActionCommand().equals("Copy")) {
                
            }
            else if(e.getActionCommand().equals("Cut")) {
                
            }
            else if(e.getActionCommand().equals("Layer1")) {
                
            }
            else if(e.getActionCommand().equals("Layer2")) {
                
            }
            else if(e.getActionCommand().equals("Layer3")) {
                
            } else {
                
            }
        }
    }
}
