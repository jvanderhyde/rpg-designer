/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playTest;

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
        JFrame gameFrame = new JFrame(game.getGameName());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        CanvasGameContainer slickGame = new CanvasGameContainer(new GameMapView(game));
        gameFrame.add(slickGame);
        gameFrame.setVisible(true);
        slickGame.start();
    }
    
    public static void main(final Game game) { 
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GameInterface di = new GameInterface(game);
                } catch (SlickException ex) {
                    Logger.getLogger(GameInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
