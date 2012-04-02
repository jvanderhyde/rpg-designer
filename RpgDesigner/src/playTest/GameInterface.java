/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playTest;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
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
        gameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        CanvasGameContainer slickGame = new CanvasGameContainer(new GameMapView(game));
        slickGame.setPreferredSize(new Dimension(1146, 750));
        gameFrame.setSize(new Dimension(1146, 750));
        gameFrame.getContentPane().add(slickGame);
        gameFrame.setVisible(true);
        gameFrame.repaint();
        gameFrame.pack();
        slickGame.start();
        slickGame.getContainer().setAlwaysRender(true);
        gameFrame.repaint();
        gameFrame.pack();
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
