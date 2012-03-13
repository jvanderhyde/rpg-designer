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
    private int SIZEX=1600; //The maps are 50x50 tiles, and each is 32x32 pixels 
    private int SIZEY=1600; //making a total image size of 1600x1600 for each layer
    private int SIZEXTILE=50;
    private int SIZEYTILE=50;
    private int id;
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
    
    public Map(int id) {
        this.id = id;
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
    
    public boolean checkForBlock(int x, int y){
        return false;
    }
    
    public int checkForEvent(int x, int y) {
        return 0;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
