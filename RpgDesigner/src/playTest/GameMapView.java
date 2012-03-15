/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playTest;

import javax.swing.JFrame;
import org.newdawn.slick.*;
import rpgdesigner.Game;
import rpgdesigner.Map;

/**
 *
 * @author james
 */
public class GameMapView extends BasicGame{
    Game game;
    Image player;
    Map workingMap;
    
    public GameMapView(Game game) {
        super(game.getGameName());
        this.game = game;
        workingMap = (Map)game.getMapList().get(0);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        //render the first layer
        int i = 0;
        for(int y=0; y<1600; y+=32) {
            for(int x=0; x<1600; x+=32) {
                workingMap.getLayer1().get(i).getSlickImage().draw(x, y);
                i++;
            }
        }
    }
    
}
