package rpgdesigner;

import java.awt.Color;
import java.awt.Graphics2D;
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
    private List<Event> events = new ArrayList();
    private List<Object> objectsOnMap = new ArrayList();
    
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
        for(int i=0; i<2500; i++){
            blocks.add(new Block(false));
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Tile> getLayer1(){
        return layer1;
    }
    
    public void setLayer1(List<Tile> layer) {
        this.layer1 = layer;
    }
    
    public List<Tile> getLayer2(){
        return layer2;
    }
    
    public void setLayer2(List<Tile> layer) {
        this.layer2 = layer;
    }
    
    public List<Tile> getLayer3(){
        return layer3;
    }
    
    public void setLayer3(List<Tile> layer) {
        this.layer3 = layer;
    }
    
    public List<Event> getEvents() {
        return this.events;
    }
    
    public void setEvents(List<Event> eventList) {
        this.events = eventList;
    }
    
    public List<Block> getBlocks() {
        return this.blocks;
    }
    
    public void setBlocks(List<Block> blockList) {
        this.blocks = blockList;
    }
    
    public boolean checkForBlock(int x, int y){
        int tileNumber = getTileNumber(x, y);
        if(blocks.get(tileNumber).getIsBlocked())
            return true;
        return false;
    }
    
    public List<Object> getObjectsOnMap(){
        return this.objectsOnMap;
    }
    
    public void setObjects(List<Object> objectsOnMap){
        this.objectsOnMap = objectsOnMap;
    }
    
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
    
    public int checkForEvent(int x, int y) {
        return 0;
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
