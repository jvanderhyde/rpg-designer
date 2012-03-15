/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playTest;

import javax.swing.JFrame;
import org.newdawn.slick.*;
import rpgdesigner.Actor;
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
    Actor actor1;
    
    public GameMapView(Game game) throws SlickException {
        super(game.getGameName());
        this.game = game;
        workingMap = (Map)game.getMapList().get(0);
        
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        if (!game.getActorList().isEmpty())
        {
        actor1 = (Actor)game.getActorList().get(0);
        player = new Image(actor1.getImagePath());
        }
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        //double delta = 1;
        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_UP))
        {
            //sprite = up;
            //player.update(delta);
            // The lower the delta the slowest the sprite will animate.
            actor1.move(0, delta * 0.1f);
            //y -= delta * 0.1f;
        }
        else if (input.isKeyDown(Input.KEY_DOWN))
        {
            actor1.move(0, -delta * 0.1f);
        }
        else if (input.isKeyDown(Input.KEY_RIGHT))
        {
            actor1.move(delta * 0.1f, 0);
        }
        else if (input.isKeyDown(Input.KEY_LEFT))
        {
            actor1.move(-delta * 0.1f, 0);
        }
        
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
        //render actor image
        player.draw(actor1.getLocX(), actor1.getLocY());
        //render 2nd and 3rd layer
        i=0;
        for(int y=0; y<1600; y+=32) {
            for(int x=0; x<1600; x+=32) {
                workingMap.getLayer2().get(i).getSlickImage().draw(x, y);
                workingMap.getLayer3().get(i).getSlickImage().draw(x, y);
                i++;
            }
            
        }
        
    }
    
}
