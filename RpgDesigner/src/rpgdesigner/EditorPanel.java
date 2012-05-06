package rpgdesigner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author james
 * This class extends JPanel to create a layered editor for maps
 */
public class EditorPanel extends JPanel{
    private List<Tile> layer1Tiles;
    private List<Tile> layer2Tiles;
    private List<Tile> layer3Tiles;
    private List<Block> blockList;
    private List<MapObject> objectList;
    private BufferedImage grid;
    
    public EditorPanel(List<Tile> layer1Tiles, List<Tile> layer2Tiles, List<Tile> layer3Tiles, 
            List<Block> blockTiles, List<MapObject> objectList) {
        this.layer1Tiles = layer1Tiles;
        this.layer2Tiles = layer2Tiles;
        this.layer3Tiles = layer3Tiles;
        this.blockList = blockTiles;
        this.objectList = objectList;
        grid = new BufferedImage(1600,1600,BufferedImage.TRANSLUCENT);
        Graphics2D gridToDraw = grid.createGraphics();
        gridToDraw.setColor(Color.red);
        for (int x=0; x<=1600; x+=32) {gridToDraw.drawLine(x, 0, x, 1600);}
        for (int y=0; y<=1600; y+=32) {gridToDraw.drawLine(0, y, 1600, y);}
    }
    
    //Setter Methods for EditorPanel
    public void setLayer1Tiles(List<Tile> layerTiles) {
        this.layer1Tiles = layerTiles;
    }
    
    public void setLayer2Tiles(List<Tile> layerTiles) {
        this.layer2Tiles = layerTiles;
    }
    
    public void setLayer3Tiles(List<Tile> layerTiles) {
        this.layer3Tiles = layerTiles;
    }
    
    public void setBlockList(List<Block> blockTiles) {
        this.blockList = blockTiles;
    }
    
    public void setObjectList(List<MapObject> objectList) {
        this.objectList = objectList;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        int i = 0;
        for(int y=0; y<1600; y+=32) {
            for(int x=0; x<1600; x+=32) {
                g.drawImage(layer1Tiles.get(i).getTileImage(), x, y, null);
                g.drawImage(layer2Tiles.get(i).getTileImage(), x, y, null);
                g.drawImage(layer3Tiles.get(i).getTileImage(), x, y, null);
                i++;
            }
        }  
        for (int j = 0; j<objectList.size(); j++)
        {
            MapObject o = objectList.get(j);
            int x = (int)o.getLocX()/32;
            int y = (int)o.getLocY()/32;
            g.drawImage(o.getImage(), x*32, y*32, null);
        }
        for(Block b : blockList) 
        {
            int x = (int)b.getLocX()/32;
            int y = (int)b.getLocY()/32;
            g.drawImage(b.getBlockImage(), x*32, y*32, null);
        }
        
        //Draw a grid on top
        g.drawImage(grid, 0, 0, null);
    }  
}
