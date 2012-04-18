package rpgdesigner;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author james
 * 
 * This class is responsible for creating the map editor tab.
 * TODO:  Rewrite map to load without ImageIcons in future.  
 */
public class iMap extends JPanel implements iListableObject{
    JFrame frame = new JFrame();
    TilesetPanel tileView;
    
    JLabel tilesetLabel = new JLabel();
            
    private JTextField tfName;
    private JToggleButton btnPlaceTile;
    private JToggleButton btnFillTile;
    private JToggleButton btnFillSquare;
    private JToggleButton btnEraseTile;
    private JToggleButton btnLayer1;
    private JToggleButton btnLayer2;
    private JToggleButton btnLayer3;
    private JToggleButton btnBlock;
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
    private final int OBJECTTOOL = 9;
    
    private Map workingMap;
    private BufferedImage currentTileset;
    private Tile currentTile;
    private List<Tile> layer1 = new ArrayList();
    private List<Tile> layer2 = new ArrayList();
    private List<Tile> layer3 = new ArrayList();
    private List<Block> blocks = new ArrayList();
    private List<MapObject> objectsOnMap = new ArrayList();
    private MapObject obj =null;
    
    TileViewMouseListener tileViewListener = new TileViewMouseListener();
    private EditorPanel mapBody;
    private JPanel objectPanel;
    private JButton btnAddObject;
    private JLabel lblAddObject;
    private Game game;
    private JScrollPane tilesetScroll;
    private JScrollPane mapViewScroll;
    private JPanel mapEditor;
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
        workingMap.setName("Map Name...");
        setObject(workingMap);
    }
    
    public iMap(JFrame frame, Map map, Game game) {
        this.game = game;
        this.frame = frame;
        workingMap = map;
        this.setLayout(new BorderLayout());
        JPanel topSection = new JPanel();
        topSection.setLayout(new BorderLayout());
        ActionListener buttonActions = new IMapListener();
        FocusListener textBoxListener = new IMapListener();
        MouseListener mapViewMouseListener = new MapViewMouseListener();
        layer1 = workingMap.getLayer1();
        layer2 = workingMap.getLayer2();
        layer3 = workingMap.getLayer3();
        blocks = workingMap.getBlocks();
        //This is the name field for the map
        JPanel nameHolder = new JPanel();
        nameHolder.setLayout(new BorderLayout());
        JPanel nameField = new JPanel();
        tfName = new JTextField("Map Name...");
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
        btnBlock.setToolTipText("<html>To add a block left click on a tile. <br />"
                                + "To change the block direction left click on a current block.<br />"
                                + "To remove a block right click on a tile.</html>");
        btnLayer1 = new JToggleButton("Layer1");
        btnLayer1.addActionListener(buttonActions);
        btnLayer2 = new JToggleButton("Layer2");
        btnLayer2.addActionListener(buttonActions);
        btnLayer3 = new JToggleButton("Layer3");
        btnLayer3.addActionListener(buttonActions);
        btnAddObject = new JButton("Add Object to Map");
        btnAddObject.addActionListener(buttonActions);
        mapButtons.add(btnPlaceTile);
        mapButtons.add(btnFillTile);
        mapButtons.add(btnFillSquare);
        mapButtons.add(btnEraseTile);
//        mapButtons.add(btnCopy);
//        mapButtons.add(btnCut);
        mapButtons.add(btnBlock);
        mapButtons.add(btnLayer1);
        mapButtons.add(btnLayer2);
        mapButtons.add(btnLayer3);
        lblAddObject = new JLabel();
        mapButtons.add(btnAddObject);
        //mapButtons.add(lblAddObject);
        //mapButtons.add(new JLabel(" "));
        //mapButtons.add(new JLabel(" "));
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
        
        mapEditor = new JPanel(new BorderLayout());
        //This code creates the view of the map being edited 
        mapBody = new EditorPanel(layer1, layer2, layer3, blocks, objectsOnMap);
        mapBody.addMouseListener(mapViewMouseListener);
        mapBody.setPreferredSize(new Dimension(1600,1600));
        mapViewScroll = new JScrollPane(mapBody);
        mapViewScroll.setPreferredSize(new Dimension(800, 500));
        
        //This code sets up the view of the tilesets for selecting images to place in tiles
        //on the map
        try {
            currentTileset = ImageIO.read(new File("Resources/Tilesets/"+(String)cbTileset.getSelectedItem()));
        } catch (IOException ex) {
            Logger.getLogger(iMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        tileView = new TilesetPanel(currentTileset);
        tileView.addMouseListener(tileViewListener);
        tilesetScroll = new JScrollPane();
        tilesetScroll.getViewport().add(tileView, null);
        tilesetScroll.setPreferredSize(new Dimension(300, 500));
        
        //Place everything together
        mapEditor.add(mapViewScroll, BorderLayout.WEST);
        mapEditor.add(tilesetScroll, BorderLayout.EAST);
        topSection.add(mapEditor, BorderLayout.SOUTH);
        
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
        workingMap.setLayer1(layer1);
        workingMap.setLayer2(layer2);
        workingMap.setLayer3(layer3);
        workingMap.setBlocks(blocks);
        workingMap.setObjects(objectsOnMap);
        File mapDir = new File("Game/Maps/"+workingMap.getName());
        mapDir.mkdir();
        BufferedImage layer1ImageFile = new BufferedImage(1600,1600,BufferedImage.TYPE_INT_ARGB);
        Graphics layer1ImageGraphics = layer1ImageFile.createGraphics();
        BufferedImage layer2ImageFile = new BufferedImage(1600,1600,BufferedImage.TYPE_INT_ARGB);
        Graphics layer2ImageGraphics = layer2ImageFile.createGraphics();
        BufferedImage layer3ImageFile = new BufferedImage(1600,1600,BufferedImage.TYPE_INT_ARGB);
        Graphics layer3ImageGraphics = layer3ImageFile.createGraphics();
        int i = 0;
        for(int y = 0; y < 1600; y+=32) {
            for(int x = 0; x < 1600; x+=32) {
                Tile tile1 = workingMap.getLayer1().get(i);
                Tile tile2 = workingMap.getLayer2().get(i);
                Tile tile3 = workingMap.getLayer3().get(i);
                Image layer1Image = tile1.getTileImage();
                Image layer2Image = tile2.getTileImage();
                Image layer3Image = tile3.getTileImage();
                layer1ImageGraphics.drawImage(layer1Image, x, y,null);
                layer2ImageGraphics.drawImage(layer2Image, x, y,null);
                layer3ImageGraphics.drawImage(layer3Image, x, y,null);
                i++;
            }
        }
        try {
            ImageIO.write(layer1ImageFile, "png", new File("Game/Maps/" + workingMap.getName() + "/layer1.png"));
            ImageIO.write(layer2ImageFile, "png", new File("Game/Maps/" + workingMap.getName() + "/layer2.png"));
            ImageIO.write(layer3ImageFile, "png", new File("Game/Maps/" + workingMap.getName() + "/layer3.png"));
        } catch (IOException ex) {
            Logger.getLogger(iMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setObject(Object o) {
        
        System.out.println("add code to iMap.setObject for objects added to map");
        workingMap = (Map) o;
        tfName.setText(workingMap.getName());
        layer1 = workingMap.getLayer1();
        layer2 = workingMap.getLayer2();
        layer3 = workingMap.getLayer3();
        blocks = workingMap.getBlocks();
        //mapBody = new EditorPanel(layer1, layer2, layer3, blocks, objectsOnMap);
        mapBody.setLayer1Tiles(layer1);
        mapBody.setLayer2Tiles(layer2);
        mapBody.setLayer3Tiles(layer3);
        mapBody.setBlockList(blocks);
        mapBody.repaint();
        tileView.repaint();
        mapViewScroll.repaint();
        tilesetScroll.repaint();
        frame.pack();
    }

    @Override
    public Object getObject() {
        return workingMap;
    }

    @Override
    public boolean hasInvalidInput() {
        if("Map Name...".equals(tfName.getText()) || "".equals(tfName.getText())) {
            tfName.setForeground(Color.red);
            tfName.repaint();
            frame.pack();
            return true;
        } else
            return false;
    }
    
    private class IMapListener implements FocusListener, ActionListener {

        public IMapListener() {
        }

        @Override
        public void focusGained(FocusEvent e) {
            if(tfName.getText().equals("Map Name...")) {
                tfName.setText("");
                tfName.setForeground(Color.black);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(tfName.getText().equals("")) {
                tfName.setText("Map Name...");
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
                //if no layer is selected select the first
                if(!btnLayer1.isSelected() && !btnLayer2.isSelected() && !btnLayer3.isSelected())
                    btnLayer1.setSelected(true);
                currentTool = CUTTOOL;
            }
            else if(e.getActionCommand().equals("Block")) {
                //flip off any other buttons not needed
                btnPlaceTile.setSelected(false);
                btnFillTile.setSelected(false);
                btnFillSquare.setSelected(false);
                btnEraseTile.setSelected(false);
                btnCopy.setSelected(false);
                btnCut.setSelected(false);
                currentTool = BLOCKTOOL;
            }
            else if(e.getActionCommand().equals("Layer1")) {
                //flip off any other layers not needed
                btnLayer2.setSelected(false);
                btnLayer3.setSelected(false);
                //if no tool is selected, select fill
                if(!btnPlaceTile.isSelected() && !btnFillTile.isSelected() && !btnFillSquare.isSelected()
                        && !btnEraseTile.isSelected() && !btnCopy.isSelected() 
                        && !btnCut.isSelected() && !btnBlock.isSelected())
                    btnPlaceTile.setSelected(true);
            }
            else if(e.getActionCommand().equals("Layer2")) {
                //flip off any other layers not needed
                btnLayer1.setSelected(false);
                btnLayer3.setSelected(false);
                //if no tool is selected, select fill
                if(!btnPlaceTile.isSelected() && !btnFillTile.isSelected() && !btnFillSquare.isSelected()
                        && !btnEraseTile.isSelected() && !btnCopy.isSelected() 
                        && !btnCut.isSelected() && !btnBlock.isSelected())
                    btnPlaceTile.setSelected(true);
            }
            else if(e.getActionCommand().equals("Layer3")) {
                //flip off any other layers not needed
                btnLayer1.setSelected(false);
                btnLayer2.setSelected(false);
                //if no tool is selected, select fill
                if(!btnPlaceTile.isSelected() && !btnFillTile.isSelected() && !btnFillSquare.isSelected()
                        && !btnEraseTile.isSelected() && !btnCopy.isSelected() 
                        && !btnCut.isSelected() && !btnBlock.isSelected())
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
            } else if(e.getActionCommand().equals("Add Object to Map")){
                //choose from items, events, actor
                //get list depending on result from first dialogue
                //JOptionDialog j = new JOptionDialog();
                JOptionPane p = new JOptionPane();
                String[] options = {"Actor","Item","Event"};
                String objectType = (String)JOptionPane.showInputDialog(frame, 
                        "pick an object type", "Add Object", JOptionPane.QUESTION_MESSAGE,
                        null, options, "Actor");
                System.out.println(objectType);
                //Actor selectedObject=null;
                if(objectType.equals("Actor"))
                    obj = (Actor)JOptionPane.showInputDialog(frame, "pick an actor", 
                            "Add Object", JOptionPane.QUESTION_MESSAGE, null, 
                            game.actorList.toArray(), null);
                else if(objectType.equals("Item"))
                    obj = (Item)JOptionPane.showInputDialog(frame, "pick an item", 
                            "Add Object", JOptionPane.QUESTION_MESSAGE, null, 
                            game.itemList.toArray(), null);
                else if(objectType.equals("Event"))
                    obj = (Event)JOptionPane.showInputDialog(frame, "pick an event", 
                            "Add Object", JOptionPane.QUESTION_MESSAGE, null, 
                            game.eventList.toArray(), null);
                //Object value = new Object();
                currentTool = OBJECTTOOL;
                lblAddObject.setText("Click the map to add "+ obj);
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
                    if(btnLayer1.isSelected()) {
                        layer1.set(tileNumber, currentTile);
                        mapBody.setLayer1Tiles(layer1);
                    }
                    else if(btnLayer2.isSelected()) {
                        layer2.set(tileNumber, currentTile);
                        mapBody.setLayer2Tiles(layer2);
                    }
                    else {
                        layer3.set(tileNumber, currentTile);
                        mapBody.setLayer3Tiles(layer3);
                    }
            }else if(currentTool == FILLTOOL){
                    if(btnLayer1.isSelected()) {
                        for(int i=0; i < layer1.size(); i++)
                            layer1.set(i, currentTile);
                        mapBody.setLayer1Tiles(layer1);
                    }
                    else if(btnLayer2.isSelected()) {
                        for(int i=0; i < layer2.size(); i++)
                            layer2.set(i, currentTile);
                        mapBody.setLayer1Tiles(layer2);
                    }
                    else {
                        for(int i=0; i < layer3.size(); i++)
                            layer3.set(i, currentTile);
                        mapBody.setLayer1Tiles(layer3);
                    }
            } else if(currentTool == ERASETOOL) {
                
            } else if(currentTool == BLOCKTOOL) {
                int tileNumber = getTileNumber(e.getX(), e.getY());
                if(e.getButton() == 1) {
                    if(blocks.get(tileNumber).getIsUnblocked()) {
                        blocks.set(tileNumber, new Block(true));
                    } else {
                        blocks.get(tileNumber).toggleBlock();
                    }
                    mapBody.setBlockList(blocks);
                } else if(e.getButton() == 3){
                    blocks.set(tileNumber, new Block(false));
                    mapBody.setBlockList(blocks);
                }
            } else if (currentTool == OBJECTTOOL){
                obj.setLocation(e.getX(), e.getY());
                obj.setTile(getTileNumber(e.getX(), e.getY()));
                objectsOnMap.add(obj);
                //ImageIcon icon = obj.getMainSprite();
                //ImageIcon icon = new ImageIcon(obj.getImagePath());
                //JLabel labelForImage = new JLabel(icon);
                
                //labelForImage.setBounds(e.getX(), e.getY(), 32, 32);
                //objectPanel.add(labelForImage);
                //objectPanel.repaint();
                
            }
            mapBody.repaint();
            mapViewScroll.repaint();
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
            tileView.setTileset(currentTileset);
            tileView.repaint();
            tilesetScroll.repaint();
            frame.pack();
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
            BufferedImage tileImage = currentTileset.getSubimage(xTile*32, yTile*32, 32, 32);
            currentTile = new Tile(tileImage, currentTileset.toString(), xTile*32, yTile*32);
            tileView.setSelecter(xTile*32, yTile*32);
            tileView.repaint();
            tilesetScroll.repaint();
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
    }
}
