package rpgdesigner;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


/**
 *
 * @author james
 * 
 * This class holds all the data of a map in the game, as well as has a class for
 * drawing the map with both a grid and without.  
 */
public class Map {
    private String name;
    private int SIZEX=1600; //The maps are 50x50 tiles, and each is 32x32 pixels 
    private int SIZEY=1600; //making a total image size of 1600x1600 for each layer
    private int SIZEXTILE=50;
    private int SIZEYTILE=50;
    private List<Tile> layer1 = new ArrayList();
    private List<Tile> layer2 = new ArrayList();
    private List<Tile> layer3 = new ArrayList();
    private List<Tile> events = new ArrayList();
    
    public Map(File mapZip) {
        
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    
    public Map() {
        BufferedImage blankImage = new BufferedImage(32,32,BufferedImage.TYPE_INT_RGB);
        BufferedImage blankImageWithTransparency = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
        for(int i=0; i<2500; i++){
            Tile tileToAdd = new Tile(i);
            tileToAdd.setImage(blankImage);
            layer1.add(tileToAdd);
        }
        for(int i=0; i<2500; i++){
            Tile tileToAdd = new Tile(i);
            tileToAdd.setImage(blankImageWithTransparency);
            layer2.add(tileToAdd);
        }
        for(int i=0; i<2500; i++){
            Tile tileToAdd = new Tile(i);
            tileToAdd.setImage(blankImageWithTransparency);
            layer3.add(tileToAdd);
        }
    }
    
    public List getLayer1(){
        return layer1;
    }
    
    public List getLayer2(){
        return layer2;
    }
    
    public List getLayer3(){
        return layer3;
    }
    
    public boolean checkForBlock(int x, int y){
        return false;
    }
    
    public int checkForEvent(int x, int y) {
        return 0;
    }
    
    public JLayeredPane generateMap() {
        JLayeredPane generatedMap = new JLayeredPane();
        generatedMap.setBounds(0, 0, 1600, 1600);
        GridLayout layoutForMaps = new GridLayout(50,50,0,0);
        JPanel layer1Panel = new JPanel();
        layer1Panel.setLayout(layoutForMaps);
        layer1Panel.setBounds(0, 0, 1600, 1600);
        JPanel layer2Panel = new JPanel();
        layer2Panel.setLayout(layoutForMaps);
        layer2Panel.setBounds(0, 0, 1600, 1600);
        layer2Panel.setOpaque(false);
        JPanel layer3Panel = new JPanel();
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
        generatedMap.setAlignmentX(0);
        generatedMap.setAlignmentY(0);
        generatedMap.add(layer1Panel);
        generatedMap.add(layer2Panel);
        generatedMap.add(layer3Panel);
        generatedMap.setLayer(layer3Panel, 3, 0);
        generatedMap.setLayer(layer2Panel, 2, 0);
        generatedMap.setLayer(layer1Panel, 1, 0);
        
        return generatedMap;
    }
    
    public JLayeredPane generateMapWithGrid() {
        JLayeredPane generatedMap = new JLayeredPane();
        generatedMap.setBounds(0, 0, 1600, 1600);
        GridLayout layoutForMaps = new GridLayout(50,50,0,0);
        JPanel layer1Panel = new JPanel();
        layer1Panel.setLayout(layoutForMaps);
        layer1Panel.setBounds(0, 0, 1600, 1600);
        JPanel layer2Panel = new JPanel();
        layer2Panel.setLayout(layoutForMaps);
        layer2Panel.setBounds(0, 0, 1600, 1600);
        layer2Panel.setOpaque(false);
        JPanel layer3Panel = new JPanel();
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
        generatedMap.setAlignmentX(0);
        generatedMap.setAlignmentY(0);
        generatedMap.add(layer1Panel);
        generatedMap.add(layer2Panel);
        generatedMap.add(layer3Panel);
        generatedMap.add(mapGridLabel);
        generatedMap.setLayer(mapGridLabel, 4, 0);
        generatedMap.setLayer(layer3Panel, 3, 0);
        generatedMap.setLayer(layer2Panel, 2, 0);
        generatedMap.setLayer(layer1Panel, 1, 0);
        
        return generatedMap;
    }
    
}
