package rpgdesigner;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author james
 * 
 * This class is responsible for creating the map editor tab.  
 */
public class iMap extends JPanel implements iListableObject{
    JFrame frame = new JFrame();
    JPanel tileView = new JPanel();
    
    JLabel tilesetLabel = new JLabel();
            
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
    private BufferedImage currentTile;
    private BufferedImage selectionImage;
    private java.util.List<Tile> layer1 = new ArrayList();
    private java.util.List<Tile> layer2 = new ArrayList();
    private java.util.List<Tile> layer3 = new ArrayList();
    private java.util.List<Tile> events = new ArrayList();
    
    TileViewMouseListener tileViewListener = new TileViewMouseListener();
    private JPanel tileSelectionPanel;
    private JLabel selectionImageLabel;
    private JPanel mapBody;
    private JLayeredPane mapLayers;
    private JPanel layer1Panel;
    private JPanel layer2Panel;
    private JPanel layer3Panel;
    
   /*
    * This is the constructor, it always requires a map.  If the map is blank create a new Map without 
    * any parameters and pass it in.  
    */ 
    @Override
    public String toString()
    {
        return workingMap.toString();
    }
    
    @Override
    public void reset() {
        workingMap = new Map();
        tfName.setText("Enter Map Name...");
        setObject(workingMap);
    }
    
    public iMap(JFrame frame, Map workingMap) {
        this.frame = frame;
        this.workingMap = workingMap;
        this.setLayout(new BorderLayout());
        JPanel topSection = new JPanel();
        topSection.setLayout(new BorderLayout());
        ActionListener buttonActions = new IMapListener();
        FocusListener textBoxListener = new IMapListener();
        MouseListener mapViewMouseListener = new MapViewMouseListener();
        layer1 = workingMap.getLayer1();
        layer2 = workingMap.getLayer2();
        layer3 = workingMap.getLayer3();
        
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
        File folder = new File("Resources/Tilesets");
        File[] tileSetsFolder = folder.listFiles();
        for(File f : tileSetsFolder)
            cbTileset.addItem(f.getName());
        JLabel tilesetSelectLabel = new JLabel("Tilesets:");
        cbTilesetPanel.add(tilesetSelectLabel, BorderLayout.NORTH);
        cbTileset.addItemListener(new TileViewActionListener());
        cbTilesetPanel.add(cbTileset, BorderLayout.SOUTH);
        tilesetPanelHolder.add(cbTilesetPanel, BorderLayout.CENTER);
        topSection.add(controls, BorderLayout.WEST);
        topSection.add(tilesetPanelHolder, BorderLayout.EAST);
        
        //This code creates the view of the map being edited 
        mapBody = new JPanel();
        mapLayers = new JLayeredPane();
        mapLayers.setBounds(0, 0, 1600, 1600);
        GridLayout layoutForMaps = new GridLayout(50,50,0,0);
        layer1Panel = new JPanel();
        layer1Panel.setLayout(layoutForMaps);
        layer1Panel.setBounds(0, 0, 1600, 1600);
        layer2Panel = new JPanel();
        layer2Panel.setLayout(layoutForMaps);
        layer2Panel.setBounds(0, 0, 1600, 1600);
        layer2Panel.setOpaque(false);
        layer3Panel = new JPanel();
        layer3Panel.setLayout(layoutForMaps);
        layer3Panel.setBounds(0, 0, 1600, 1600);
        layer3Panel.setOpaque(false);
        for(int i=0; i<layer1.size(); i++){
            ImageIcon icon = new ImageIcon(layer1.get(i).getTileImage());
            JLabel labelForImage = new JLabel(icon);
            layer1Panel.add(labelForImage);
        }
        for(int i=0; i<layer2.size(); i++){
            ImageIcon icon = new ImageIcon(layer2.get(i).getTileImage());
            JLabel labelForImage = new JLabel(icon);
            layer2Panel.add(labelForImage);
        }
        for(int i=0; i<layer3.size(); i++){
            ImageIcon icon = new ImageIcon(layer3.get(i).getTileImage());
            JLabel labelForImage = new JLabel(icon);
            layer3Panel.add(labelForImage);
        }
        BufferedImage grid = new BufferedImage(1600,1600,BufferedImage.TRANSLUCENT);
        Graphics2D gridToDraw = grid.createGraphics();
        gridToDraw.setColor(Color.red);
        for (int x=0; x<=1600; x+=32) {gridToDraw.drawLine(x, 0, x, 1600);}
        for (int y=0; y<=1600; y+=32) {gridToDraw.drawLine(0, y, 1600, y);}
        ImageIcon mapGrid = new ImageIcon(grid);
        JLabel mapGridLabel = new JLabel(mapGrid);
        mapGridLabel.setBounds(0, 0, 1600, 1600);
        mapLayers.setAlignmentX(0);
        mapLayers.setAlignmentY(0);
        mapLayers.add(layer1Panel);
        mapLayers.add(layer2Panel);
        mapLayers.add(layer3Panel);
        mapLayers.add(mapGridLabel);
        mapLayers.setLayer(mapGridLabel, 4, 0);
        mapLayers.setLayer(layer3Panel, 3, 0);
        mapLayers.setLayer(layer2Panel, 2, 0);
        mapLayers.setLayer(layer1Panel, 1, 0);
        mapLayers.addMouseListener(mapViewMouseListener);
        mapLayers.setPreferredSize(new Dimension(1600,1600));
        JScrollPane mapViewScroll = new JScrollPane(mapLayers);
        mapViewScroll.setPreferredSize(new Dimension(800, 500));
        
        //This code sets up the view of the tilesets for selecting images to place in tiles
        //on the map
        tileView = new JPanel();
        tileView.setLayout(new OverlayLayout(tileView));
        cbTileset.setSelectedIndex(1);
        try {
            currentTileset = ImageIO.read(new File("Resources/Tilesets/"+(String)cbTileset.getSelectedItem()));
        } catch (IOException ex) {
            Logger.getLogger(iMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon tileset = new ImageIcon(currentTileset);
        tilesetLabel = new JLabel();
        tilesetLabel.setIcon(tileset);
        BufferedImage gridTileSets = new BufferedImage(tileset.getIconWidth(),tileset.getIconHeight(),BufferedImage.TRANSLUCENT);
        Graphics2D gridToDraw2 = gridTileSets.createGraphics();
        gridToDraw2.setColor(Color.red);
        for (int x=0; x<=tileset.getIconWidth(); x+=32) {gridToDraw2.drawLine(x, 0, x, tileset.getIconHeight());}
        for (int y=0; y<=tileset.getIconHeight(); y+=32) {gridToDraw2.drawLine(0, y, tileset.getIconWidth(), y);}
        ImageIcon tileSetGrid = new ImageIcon(gridTileSets);
        JLabel tileGridLabel = new JLabel(tileSetGrid);
        tileSelectionPanel = new JPanel();
        tileSelectionPanel.setLayout(null);
        tileSelectionPanel.setPreferredSize(tileGridLabel.getPreferredSize());
        selectionImage = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
        Graphics2D tileSelection = selectionImage.createGraphics();
        tileSelection.setPaint(Color.blue);
        tileSelection.drawRect(0, 0, 31, 31);
        tileSelection.drawRect(1,1,29,29);
        ImageIcon selectionImageIcon = new ImageIcon(selectionImage);
        selectionImageLabel = new JLabel(selectionImageIcon);
        selectionImageLabel.setBounds(0*32+1, 0*32+1, 32, 32);
        tileSelectionPanel.add(selectionImageLabel);
        tileSelectionPanel.setOpaque(false);
        tileView.add(tileSelectionPanel);
        tileView.add(tileGridLabel);
        tileView.add(tilesetLabel);
        tileView.addMouseListener(tileViewListener);
        JScrollPane tilesetScroll = new JScrollPane();
        tilesetScroll.getViewport().add(tileView);
        tilesetScroll.setPreferredSize(new Dimension(300, 500));
        
        //Place everything together
        mapBody.add(mapViewScroll, BorderLayout.WEST);
        mapBody.add(tilesetScroll, BorderLayout.EAST);
        topSection.add(mapBody, BorderLayout.SOUTH);
        
        JPanel bottomSection = new JPanel();
        bottomSection.setLayout(new BorderLayout());
        
        add(topSection, BorderLayout.NORTH);
        add(bottomSection, BorderLayout.SOUTH);
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void saveObject() {
        workingMap.setName(tfName.getText());
    }

    @Override
    public void setObject(Object o) {
        workingMap = (Map) o;
        tfName.setText(workingMap.getName());
        layer1 = workingMap.getLayer1();
        layer2 = workingMap.getLayer2();
        layer3 = workingMap.getLayer3();
        layer1Panel.removeAll();
        layer2Panel.removeAll();
        layer3Panel.removeAll();
        for(int i=0; i<layer1.size(); i++){
            ImageIcon icon = new ImageIcon(layer1.get(i).getTileImage());
            JLabel labelForImage = new JLabel(icon);
            layer1Panel.add(labelForImage);
        }
        for(int i=0; i<layer2.size(); i++){
            ImageIcon icon = new ImageIcon(layer2.get(i).getTileImage());
            JLabel labelForImage = new JLabel(icon);
            layer2Panel.add(labelForImage);
        }
        for(int i=0; i<layer3.size(); i++){
            ImageIcon icon = new ImageIcon(layer3.get(i).getTileImage());
            JLabel labelForImage = new JLabel(icon);
            layer3Panel.add(labelForImage);
        }
        layer1Panel.repaint();
        layer2Panel.repaint();
        layer3Panel.repaint();
        mapLayers.repaint();
        frame.pack();
    }

    @Override
    public Object getObject() {
        return workingMap;
    }

    @Override
    public boolean hasInvalidInput() {
        //add logic if there if the input needs to be verified before it can be saved
        //For example, in iActor I set invalidInput to false if soemthing other than
        //an int is entered in a box that should only accept ints
        return true;
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
            if(currentTool == PLACETOOL) {
                    int tileNumber = getTileNumber(e.getX(), e.getY());
                    Tile newTile = new Tile(tileNumber);
                    newTile.setImage(currentTile);
                    if(btnLayer1.isSelected()) {
                        layer1.set(tileNumber, newTile);
                        layer1Panel.removeAll();
                        for(int i=0; i<layer1.size(); i++){
                            ImageIcon icon = new ImageIcon(layer1.get(i).getTileImage());
                            JLabel labelForImage = new JLabel(icon);
                            layer1Panel.add(labelForImage);
                        }
                        layer1Panel.repaint();
                    }
                    else if(btnLayer2.isSelected()) {
                        layer2.set(tileNumber, newTile);
                        layer2Panel.removeAll();
                        for(int i=0; i<layer2.size(); i++){
                            ImageIcon icon = new ImageIcon(layer2.get(i).getTileImage());
                            JLabel labelForImage = new JLabel(icon);
                            layer2Panel.add(labelForImage);
                        }
                        layer2Panel.repaint();
                    }
                    else {
                        layer3.set(tileNumber, newTile);
                        layer3Panel.removeAll();
                        for(int i=0; i<layer3.size(); i++){
                            ImageIcon icon = new ImageIcon(layer3.get(i).getTileImage());
                            JLabel labelForImage = new JLabel(icon);
                            layer3Panel.add(labelForImage);
                        }
                        layer3Panel.repaint();
                    }
            }else if(currentTool == FILLTOOL){
                    Tile newTile = new Tile();
                    newTile.setImage(currentTile);
                    if(btnLayer1.isSelected()) {
                        for(int i=0; i < layer1.size(); i++)
                            layer1.set(i, newTile);
                        layer1Panel.removeAll();
                        for(int i=0; i<layer1.size(); i++){
                            ImageIcon icon = new ImageIcon(layer1.get(i).getTileImage());
                            JLabel labelForImage = new JLabel(icon);
                            layer1Panel.add(labelForImage);
                        }
                    }
                    else if(btnLayer2.isSelected()) {
                        for(int i=0; i < layer2.size(); i++)
                            layer2.set(i, newTile);
                        layer2Panel.removeAll();
                        for(int i=0; i<layer2.size(); i++){
                            ImageIcon icon = new ImageIcon(layer2.get(i).getTileImage());
                            JLabel labelForImage = new JLabel(icon);
                            layer2Panel.add(labelForImage);
                        }
                        layer2Panel.repaint();
                    }
                    else {
                        for(int i=0; i < layer3.size(); i++)
                            layer3.set(i, newTile);
                        layer3Panel.removeAll();
                        for(int i=0; i<layer3.size(); i++){
                            ImageIcon icon = new ImageIcon(layer3.get(i).getTileImage());
                            JLabel labelForImage = new JLabel(icon);
                            layer3Panel.add(labelForImage);
                        }
                        layer3Panel.repaint();
                    }
            } else if(currentTool == ERASETOOL) {
                
            }
//            layer1Panel.repaint();
//            layer2Panel.repaint();
//            layer3Panel.repaint();
            mapLayers.repaint();
            frame.pack();
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
            int number;
            int numberx = 0;
            int numbery = 0;
            
            //First solve which tile in the x range it is
            for(int i=0; i < x; i = i+32) {
                numberx++;
            }
            numberx--;
            //Now solve for the tile in which the y range
            for(int i=0; i < y; i = i+32) {
                numbery++;
            }
            numbery--;
            
            number = (numbery*50 + numberx);
            
            return number;
        }
        
    }
    
    private class TileViewActionListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                currentTileset = ImageIO.read(new File("Resources/Tilesets/"+(String)cbTileset.getSelectedItem()));
            } catch (IOException ex) {
                Logger.getLogger(iMap.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageIcon tileset = new ImageIcon(currentTileset);
            
            tilesetLabel.setIcon(tileset);
            tilesetLabel.repaint();
        }
        
    }
    
    private class TileViewMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int xTile = e.getX()/32;
            int yTile = e.getY()/32;
            currentTile = currentTileset.getSubimage(xTile*32, yTile*32, 32, 32);
            tileSelectionPanel.removeAll();
            ImageIcon selectionImageIcon = new ImageIcon(selectionImage);
            selectionImageLabel = new JLabel(selectionImageIcon);
            selectionImageLabel.setBounds(xTile*32+1, yTile*32+1, 32, 32);
            tileSelectionPanel.add(selectionImageLabel);
            tileSelectionPanel.repaint();
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
    }
}
