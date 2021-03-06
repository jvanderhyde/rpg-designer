package rpgdesigner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author james
 * 
 * This class holds all the data of a map in the game, as well as has a class for
 * drawing the map with both a grid and without.  
 */
public class Map {
    private String name;
    private List<Tile> layer1 = new ArrayList();
    private List<Tile> layer2 = new ArrayList();
    private List<Tile> layer3 = new ArrayList();
    private List<Block> blocks = new ArrayList();
    private List<MapObject> objectsOnMap = new ArrayList();
    
    //Creates a blank Map
    public Map() {
        BufferedImage blankImage = new BufferedImage(32,32,BufferedImage.TYPE_INT_RGB);
        BufferedImage blankImageWithTransparency = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
        for(int i=0; i<2500; i++){
            Tile tileToAdd = new Tile();
            tileToAdd.setImage(blankImage, null, -1, -1);
            layer1.add(tileToAdd);
        }
        for(int i=0; i<2500; i++){
            Tile tileToAdd = new Tile();
            tileToAdd.setImage(blankImageWithTransparency, null, -1, -1);
            layer2.add(tileToAdd);
            layer3.add(tileToAdd);
        }
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    
    //This method checks the map for objects and events and returns it if there is one
    public Object checkForObject(int x, int y)
    {
        for (Object o: objectsOnMap)
        {
            Actor a = (Actor)o;
            if(a.getLocX()==x&&a.getLocY()==y)
            {
                return a;
            }
        }
        return false;
    }
    
    //Getter methods for the Map class
    public String getName() {
        return this.name;
    }
    
    public List<Tile> getLayer1(){
        return layer1;
    }
    
    public List<Tile> getLayer2(){
        return layer2;
    }
    
    public List<Tile> getLayer3(){
        return layer3;
    }
    
    public List<Block> getBlocks() {
        return this.blocks;
    }
    
    public List<MapObject> getObjectsOnMap(){
        return this.objectsOnMap;
    }
    
    //The setter methods for the Map class
    public void setName(String name) {
        this.name = name;
    }
    
    public void setLayer1(List<Tile> layer) {
        this.layer1 = layer;
    }
    
    public void setLayer2(List<Tile> layer) {
        this.layer2 = layer;
    }
    
    public void setLayer3(List<Tile> layer) {
        this.layer3 = layer;
    }
    
    public void setBlocks(List<Block> blockList) {
        this.blocks = blockList;
    }
    
    public void setObjects(List<MapObject> objectsOnMap){
        this.objectsOnMap = objectsOnMap;
    }
}
