/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playTest;

import org.newdawn.slick.*;
import rpgdesigner.Actor;
import rpgdesigner.Game;
import rpgdesigner.Map;
import rpgdesigner.Tile;

/**
 *
 * @author james
 */
public class GameMapView extends BasicGame{
    Game game;
    Image player;
    Map workingMap;
    Actor actor1;
    Image layer1;
    Image layer2;
    Image layer3;
    SpriteSheet sheet;
    
    public GameMapView(Game game) throws SlickException {
        super(game.getGameName());
        this.game = game;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        workingMap = (Map)game.getMapList().get(0);
        layer1 = new Image(1600,1600);
        Graphics layer1Graphics = layer1.getGraphics();
        int i = 0;
        for(int y = 0; y < 1600; y+=32) {
            for(int x = 0; x < 1600; x+=32) {
                layer1Graphics.drawImage(
                        workingMap.getLayer1().get(i).getSlickImage(), x, y);
                i++;
            }
        }
        layer2 = new Image(1600,1600);
        Graphics layer2Graphics = layer2.getGraphics();
        i = 0;
        for(int y = 0; y < 1600; y+=32) {
            for(int x = 0; x < 1600; x+=32) {
                layer2Graphics.drawImage(
                        workingMap.getLayer2().get(i).getSlickImage(), x, y);
                i++;
            }
        }
        layer3 = new Image(1600,1600);
        Graphics layer3Graphics = layer3.getGraphics();
        i = 0;
        for(int y = 0; y < 1600; y+=32) {
            for(int x = 0; x < 1600; x+=32) {
                layer3Graphics.drawImage(
                        workingMap.getLayer3().get(i).getSlickImage(), x, y);
                i++;
            }
        }
        if (!game.getActorList().isEmpty())
        {
        actor1 = (Actor)game.getActorList().get(0);
        Image sheetImage = new Image(actor1.getImagePath());
        sheet = new SpriteSheet(sheetImage, 32, 32);
        player = sheet.getSprite(1, 1);
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
            actor1.move(0, -delta * 0.1d);
            player = sheet.getSprite(1, 0);
            //y -= delta * 0.1f;
        }
        else if (input.isKeyDown(Input.KEY_DOWN))
        {
            player = sheet.getSprite(1, 2);
            actor1.move(0, delta * 0.1d);
        }
        else if (input.isKeyDown(Input.KEY_RIGHT))
        {
            player = sheet.getSprite(1, 1);
            actor1.move(delta * 0.1d, 0);
        }
        else if (input.isKeyDown(Input.KEY_LEFT))
        {
            player = sheet.getSprite(1, 3);
            actor1.move(-delta * 0.1d, 0);
        }
        
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        //render the first layer
        layer1.draw();
        //render actor image
        player.draw(actor1.getLocX(), actor1.getLocY());
        //render 2nd and 3rd layer
        layer2.draw();
        layer3.draw();
    }
    
}
