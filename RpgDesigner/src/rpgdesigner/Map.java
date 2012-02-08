/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import org.newdawn.slick.ImageBuffer;


/**
 *
 * @author james
 * SOME NOTES(for later): I was thinking today, 2/2/2012 that when implementing the 
 * maps in the game that it would be nice to have it scroll by larger section that 
 * one row or column while moving to tiles from offscreen.  
 * Some Notes(for now): Use BufferedImage to create a new image if there is not yet
 * an image for each of the layers(a new map).  This class also has lots of tools
 * for cutting out sections of images, and I think overlaying images.  Subimage will
 * be particularly useful.  I also need MouseListener implemented
 */
public class Map {
    private String name;
    private int SIZEX=1600; //The maps are 50x50 tiles, and each is 32x32 pixels 
    private int SIZEY=1600; //making a total image size of 1600x1600 for each layer
    private int SIZEXTILE=25;
    private int SIZEYTILE=25;
    private Image layer1;
    private Image layer2;
    private Image layer3;
    private List<Tile> events;
    
    public Map(URL mapUrl) {
        
    }
    
    public Map() {
        BufferedImage blankImage = new BufferedImage(SIZEX,SIZEY,1);
        layer1 = blankImage;
        layer2 = blankImage;
        layer3 = blankImage;;
        
    }
    
    public Image getLayer1(){
        return layer1;
    }
    
    public Image getLayer2(){
        return layer2;
    }
    
    public Image getLayer3(){
        return layer3;
    }
    
    public boolean checkForBlock(int x, int y){
        return false;
    }
    
    public int checkForEvent(int x, int y) {
        return 0;
    }
    
}
