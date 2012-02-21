package rpgdesigner;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 *
 * @author james
 * 
 * This class is responsible for creating the map editor tab.  
 */
public class iMap extends JPanel implements iListableObject{
    private JTextField tfName;
    private JToggleButton btnPlaceTile;
    private JToggleButton btnFillTile;
    private JToggleButton btnFillSquare;
    private JToggleButton btnEraseTile;
    private JToggleButton btnLayer1;
    private JToggleButton btnLayer2;
    private JToggleButton btnLayer3;
    private JToggleButton btnLayerEvents;
    private JToggleButton btnBlock;
    private JComboBox cbEvents;
    private JComboBox cbTileset;
    private JToggleButton btnCopy, btnCut;
    
    private int currentTool = 0;
    private final int PLACETOOL = 1;
    private final int FILLTOOL = 2;
    private final int SELECTIONTOOL = 3;
    private final int ERASETOOL = 4;
    private final int EVENTTOOL = 5;
    private final int BLOCKTOOL = 6;
    private final int COPYTOOL = 7;
    private final int CUTTOOL = 8; 
    
    private Map workingMap;
    private BufferedImage currentTileset;
    private Game game;
    
   /*
    * This is the constructor, it always requires a map.  If the map is blank create a new Map without 
    * any parameters and pass it in.  
    */ 
    public String toString()
    {
        return workingMap.toString();
    }
    public iMap(Map workingMap) {
        this.workingMap = workingMap;
        this.setLayout(new BorderLayout());
        JPanel topSection = new JPanel();
        topSection.setLayout(new BorderLayout());
        ActionListener buttonActions = new IMapListener();
        FocusListener textBoxListener = new IMapListener();
        MouseListener mapViewMouseListener = new MapViewMouseListener();
        
        //This is the name field for the map
        JPanel nameHolder = new JPanel();
        nameHolder.setLayout(new BorderLayout());
        JPanel nameField = new JPanel();
        tfName = new JTextField("Enter Map Name...");
        tfName.setForeground(Color.gray);
        tfName.setColumns(15);
        tfName.addFocusListener(textBoxListener);
        nameField.add(tfName);
        nameHolder.add(nameField, BorderLayout.WEST);
        topSection.add(nameHolder, BorderLayout.NORTH);
        
        //This panel is used to hold the different buttons for the map editor
        JPanel controls = new JPanel();
        controls.setLayout(new BorderLayout());
        JPanel mapButtons = new JPanel();
        mapButtons.setLayout(new GridLayout(2,15));
        btnPlaceTile = new JToggleButton("Place");
        btnPlaceTile.addActionListener(buttonActions);
        btnFillTile = new JToggleButton("Fill");
        btnFillTile.addActionListener(buttonActions);
        btnFillSquare = new JToggleButton("Selection");
        btnFillSquare.addActionListener(buttonActions);
        btnEraseTile = new JToggleButton("Eraser");
        btnEraseTile.addActionListener(buttonActions);
        btnCopy = new JToggleButton("Copy");
        btnCopy.addActionListener(buttonActions);
        btnCut = new JToggleButton("Cut");
        btnCut.addActionListener(buttonActions);
        btnBlock = new JToggleButton("Block");
        btnBlock.addActionListener(buttonActions);
        btnLayer1 = new JToggleButton("Layer1");
        btnLayer1.addActionListener(buttonActions);
        btnLayer2 = new JToggleButton("Layer2");
        btnLayer2.addActionListener(buttonActions);
        btnLayer3 = new JToggleButton("Layer3");
        btnLayer3.addActionListener(buttonActions);
        btnLayerEvents = new JToggleButton("Event");
        btnLayerEvents.addActionListener(buttonActions);
        cbEvents = new JComboBox();
        cbEvents.addItem("Event 1");
        cbEvents.addItem("Event 2");
        cbEvents.addItem("Event 3...");
        mapButtons.add(btnPlaceTile);
        mapButtons.add(btnFillTile);
        mapButtons.add(btnFillSquare);
        mapButtons.add(btnEraseTile);
        mapButtons.add(btnCopy);
        mapButtons.add(btnCut);
        mapButtons.add(btnBlock);
        mapButtons.add(btnLayer1);
        mapButtons.add(btnLayer2);
        mapButtons.add(btnLayer3);
        mapButtons.add(btnLayerEvents); 
        mapButtons.add(cbEvents);
        mapButtons.add(new JLabel(" "));
        mapButtons.add(new JLabel(" "));
        controls.add(mapButtons, BorderLayout.CENTER);
        JPanel tilesetPanelHolder = new JPanel();
        tilesetPanelHolder.setLayout(new BorderLayout());
        JPanel cbTilesetPanel = new JPanel();
        cbTilesetPanel.setLayout(new BorderLayout());
        this.cbTileset = new JComboBox();
        cbTileset.addItem("Hello");
        cbTileset.addItem("Hello12345678910");
        cbTileset.addItem("I am a tileset dawg!");
        JLabel tilesetSelectLabel = new JLabel("Tilesets:");
        cbTilesetPanel.add(tilesetSelectLabel, BorderLayout.NORTH);
        cbTilesetPanel.add(cbTileset, BorderLayout.SOUTH);
        tilesetPanelHolder.add(cbTilesetPanel, BorderLayout.CENTER);
        topSection.add(controls, BorderLayout.WEST);
        topSection.add(tilesetPanelHolder, BorderLayout.EAST);
        
        //This code creates the view of the map being edited 
        JPanel mapBody = new JPanel();
        JLayeredPane mapLayers = workingMap.generateMapWithGrid();
        mapLayers.addMouseListener(mapViewMouseListener);
        mapLayers.setPreferredSize(new Dimension(1600,1600));
        JScrollPane mapViewScroll = new JScrollPane(mapLayers);
        mapViewScroll.setPreferredSize(new Dimension(800, 500));
        
        //This code sets up the view of the tilesets for selecting images to place in tiles
        //on the map
        JPanel tileView = new JPanel();
        tileView.setLayout(new OverlayLayout(tileView));
        ImageIcon tileset = new ImageIcon(new BufferedImage(800,500,1));
        JLabel tilesetLabel = new JLabel();
        tilesetLabel.setIcon(tileset);
        BufferedImage gridTileSets = new BufferedImage(tileset.getIconWidth(),tileset.getIconHeight(),BufferedImage.TRANSLUCENT);
        Graphics2D gridToDraw2 = gridTileSets.createGraphics();
        gridToDraw2.setColor(Color.red);
        for (int x=0; x<=tileset.getIconWidth(); x+=32) {gridToDraw2.drawLine(x, 0, x, tileset.getIconHeight());}
        for (int y=0; y<=tileset.getIconHeight(); y+=32) {gridToDraw2.drawLine(0, y, tileset.getIconWidth(), y);}
        ImageIcon tileSetGrid = new ImageIcon(gridTileSets);
        JLabel tileGridLabel = new JLabel(tileSetGrid);
        tileView.add(tileGridLabel);
        tileView.add(tilesetLabel);
        JScrollPane tilesetScroll = new JScrollPane();
        tilesetScroll.getViewport().add(tileView);
        tilesetScroll.setPreferredSize(new Dimension(300, 500));
        
        //Place everything together
        mapBody.add(mapViewScroll, BorderLayout.WEST);
        mapBody.add(tilesetScroll, BorderLayout.EAST);
        topSection.add(mapBody, BorderLayout.SOUTH);
        
        JPanel bottomSection = new JPanel();
        bottomSection.setLayout(new BorderLayout());
        JButton save = new JButton("Save");
        save.addActionListener(buttonActions);
        bottomSection.add(save, BorderLayout.WEST);
        
        add(topSection, BorderLayout.NORTH);
        add(bottomSection, BorderLayout.SOUTH);  
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void saveObject() {
        throw new UnsupportedOperationException("Not supported yet.  ");
    }
    
    private class IBlockDirections extends JPanel {
        /*
         * TODO:  This code should implement a pop-up panel that allows switching
         * which directions are blocked.  The importance of this function
         * is pretty low at this point, though I would like to get it done.
         */
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
                //flip off any other buttons not needed
                btnFillTile.setSelected(false);
                btnFillSquare.setSelected(false);
                btnEraseTile.setSelected(false);
                btnBlock.setSelected(false);
                btnCopy.setSelected(false);
                btnCut.setSelected(false);
                btnLayerEvents.setSelected(false);
                //if no layer is selected select the first
                if(!btnLayer1.isSelected() && !btnLayer2.isSelected() && !btnLayer3.isSelected())
                    btnLayer1.setSelected(true);
                currentTool = PLACETOOL;
            } 
            else if(e.getActionCommand().equals("Fill")) {
                //flip off any other buttons not needed
                btnPlaceTile.setSelected(false);
                btnFillSquare.setSelected(false);
                btnEraseTile.setSelected(false);
                btnBlock.setSelected(false);
                btnCopy.setSelected(false);
                btnCut.setSelected(false);
                btnLayerEvents.setSelected(false);
                //if no layer is selected select the first
                if(!btnLayer1.isSelected() && !btnLayer2.isSelected() && !btnLayer3.isSelected())
                    btnLayer1.setSelected(true);
                currentTool = FILLTOOL;
            }
            else if(e.getActionCommand().equals("Selection")) {
                //flip off any other buttons not needed
                btnPlaceTile.setSelected(false);
                btnFillTile.setSelected(false);
                btnEraseTile.setSelected(false);
                btnBlock.setSelected(false);
                btnCopy.setSelected(false);
                btnCut.setSelected(false);
                btnLayerEvents.setSelected(false);
                //if no layer is selected select the first
                if(!btnLayer1.isSelected() && !btnLayer2.isSelected() && !btnLayer3.isSelected())
                    btnLayer1.setSelected(true);
                currentTool = SELECTIONTOOL;
            }
            else if(e.getActionCommand().equals("Eraser")) {
                //flip off any other buttons not needed
                btnPlaceTile.setSelected(false);
                btnFillTile.setSelected(false);
                btnFillSquare.setSelected(false);
                btnBlock.setSelected(false);
                btnCopy.setSelected(false);
                btnCut.setSelected(false);
                btnLayerEvents.setSelected(false);
                //if no layer is selected select the first
                if(!btnLayer1.isSelected() && !btnLayer2.isSelected() && !btnLayer3.isSelected())
                    btnLayer1.setSelected(true);
                currentTool = ERASETOOL;
            }
            else if(e.getActionCommand().equals("Copy")) {
                //flip off any other buttons not needed
                btnPlaceTile.setSelected(false);
                btnFillTile.setSelected(false);
                btnFillSquare.setSelected(false);
                btnEraseTile.setSelected(false);
                btnBlock.setSelected(false);
                btnCut.setSelected(false);
                btnLayerEvents.setSelected(false);
                //if no layer is selected select the first
                if(!btnLayer1.isSelected() && !btnLayer2.isSelected() && !btnLayer3.isSelected())
                    btnLayer1.setSelected(true);
                currentTool = COPYTOOL;
            }
            else if(e.getActionCommand().equals("Cut")) {
                //flip off any other buttons not needed
                btnPlaceTile.setSelected(false);
                btnFillTile.setSelected(false);
                btnFillSquare.setSelected(false);
                btnEraseTile.setSelected(false);
                btnBlock.setSelected(false);
                btnCopy.setSelected(false);
                btnLayerEvents.setSelected(false);
                //if no layer is selected select the first
                if(!btnLayer1.isSelected() && !btnLayer2.isSelected() && !btnLayer3.isSelected())
                    btnLayer1.setSelected(true);
                currentTool = CUTTOOL;
            }
            else if(e.getActionCommand().equals("Block")) {
                //flip off any other buttons not needed
                btnLayer1.setSelected(true);
                btnLayer2.setSelected(false);
                btnLayer3.setSelected(false);
                btnPlaceTile.setSelected(false);
                btnFillTile.setSelected(false);
                btnFillSquare.setSelected(false);
                btnEraseTile.setSelected(false);
                btnCopy.setSelected(false);
                btnCut.setSelected(false);
                btnLayerEvents.setSelected(false);
                currentTool = BLOCKTOOL;
            }
            else if(e.getActionCommand().equals("Layer1")) {
                //flip off any other layers not needed
                btnLayer2.setSelected(false);
                btnLayer3.setSelected(false);
                btnLayerEvents.setSelected(false);
                //if no tool is selected, select fill
                if(!btnPlaceTile.isSelected() && !btnFillTile.isSelected() && !btnFillSquare.isSelected()
                        && !btnEraseTile.isSelected() && !btnCopy.isSelected() 
                        && !btnCut.isSelected())
                    btnPlaceTile.setSelected(true);
            }
            else if(e.getActionCommand().equals("Layer2")) {
                //flip off any other layers not needed
                btnBlock.setSelected(false);
                btnLayer1.setSelected(false);
                btnLayer3.setSelected(false);
                btnLayerEvents.setSelected(false);
                //if no tool is selected, select fill
                if(!btnPlaceTile.isSelected() && !btnFillTile.isSelected() && !btnFillSquare.isSelected()
                        && !btnEraseTile.isSelected() && !btnCopy.isSelected() 
                        && !btnCut.isSelected())
                    btnPlaceTile.setSelected(true);
            }
            else if(e.getActionCommand().equals("Layer3")) {
                //flip off any other layers not needed
                btnBlock.setSelected(false);
                btnLayer1.setSelected(false);
                btnLayer2.setSelected(false);
                btnLayerEvents.setSelected(false);
                //if no tool is selected, select fill
                if(!btnPlaceTile.isSelected() && !btnFillTile.isSelected() && !btnFillSquare.isSelected()
                        && !btnEraseTile.isSelected() && !btnCopy.isSelected() 
                        && !btnCut.isSelected())
                    btnPlaceTile.setSelected(true);
            } 
            else if(e.getActionCommand().equals("Event")) {
                //flip off any other layers and buttons not needed
                btnLayer1.setSelected(true);
                btnLayer2.setSelected(false);
                btnLayer3.setSelected(false);
                btnPlaceTile.setSelected(false);
                btnFillTile.setSelected(false);
                btnFillSquare.setSelected(false);
                btnEraseTile.setSelected(false);
                btnBlock.setSelected(false);
                btnCopy.setSelected(false);
                btnCut.setSelected(false);
                currentTool = EVENTTOOL;
            } else if(e.getActionCommand().equals("Save")) {
                //save the map
            } else {
                
            }
        }
    }
    
    private class MapViewMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            //mouse click seems to respond twice because it also counts as a mouse pressed
            //so I am going to implement mousepressed only.  
        }

        @Override
        public void mousePressed(MouseEvent e) {
            switch(currentTool) {
                case(PLACETOOL):
                    System.out.println(getTileNumber(e.getX(), e.getY()));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
        private int getTileNumber(int x, int y) {
            int multiple = 0;
            int number;
            int numberx = 0;
            int numbery = 0;
            
            //First solve which tile in the x range it is
            for(int i=0; i < x; i = i+32) {
                multiple = i;
                numberx++;
            }
            numberx--;
            //Now solve for the tile in which the y range
            for(int i=0; i < y; i = i+32) {
                multiple = i;
                numbery++;
            }
            numbery--;
            
            number = (numbery*50 + numberx);
            
            return number;
        }
        
    }
}
