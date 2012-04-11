/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playTest;

import java.awt.Dimension;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.SlickException;
import rpgdesigner.Game;

/**
 *
 * @author james
 */
public class GameInterface {

    public GameInterface(Game game) throws SlickException {
        AppGameContainer slickGame = new AppGameContainer(new GameMapView(game));
        slickGame.setDisplayMode(1146, 750, false);
        slickGame.start();
    }
    
    public static void main(final Game game) { 
        try {
            GameInterface di = new GameInterface(game);
        } catch (SlickException ex) {
            Logger.getLogger(GameInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
