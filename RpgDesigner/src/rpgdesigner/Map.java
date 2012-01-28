/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.util.List;

/**
 *
 * @author james
 */
public class Map {
    private String name;
    private int sizex;
    private int sizey;
    private List<Tile> layer1;
    private List<Tile> layer2;
    private List<Tile> layer3;
    
    public void generateLayer1(){
        
    }
    
    public void generateLayer2(){
        
    }
    
    public void generateLayer3(){
        
    }
    
    public boolean checkForBlock(int x, int y){
        return false;
    }
    
    public int checkForEvent(int x, int y) {
        return 0;
    }
    
}
