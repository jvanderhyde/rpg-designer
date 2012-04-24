/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playTest;

import java.util.List;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.InternalTextureLoader;
import rpgdesigner.*;

/**
 *
 * @author james
 */
public class GameMapView extends BasicGame{
    rpgdesigner.Game game;
    Map workingMap;
    Actor actor1;
    Image layer1;
    Image layer2;
    Image layer3;
    List<Block> currentMapBlocks;
    List<MapObject> objectsOnMap;
    SpriteSheet sheet;
    Animation up, left,down, right, spriteAnimation;
    int timeForText;
    String text;
    Actor movingNPC;
    int distanceLeftForNPC;
    String directionOfMovingNPC;
    
    public GameMapView(rpgdesigner.Game game) throws SlickException {
        super(game.getGameName());
        this.game = game;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        InternalTextureLoader.get().clear();
        SoundStore.get().clear();
        workingMap = (Map)game.getMapList().get(0);
        layer1 = new Image("Game/Maps/" + workingMap.getName() + "/layer1.png");
        layer2 = new Image("Game/Maps/" + workingMap.getName() + "/layer2.png");
        layer3 = new Image("Game/Maps/" + workingMap.getName() + "/layer3.png");
        int i = 0;
        setUpSprite(); 
        currentMapBlocks = workingMap.getBlocks();
        objectsOnMap = workingMap.getObjectsOnMap();
    }

    private void displayText(Graphics g) {
        g.setColor(Color.black);
        g.drawString(text, 500, 500);
        g.setColor(Color.white);
        timeForText--;
    }

    private void moveNPC() {
        if(directionOfMovingNPC.equals("up"))
            movingNPC.move(0, -1);
        else if(directionOfMovingNPC.equals("down"))
            movingNPC.move(0, 1);
        else if(directionOfMovingNPC.equals("left"))
            movingNPC.move(-1,0);
        else if(directionOfMovingNPC.equals("right"))
            movingNPC.move(1, 0);
        distanceLeftForNPC--;
    }

    private void setUpSprite() throws SlickException {
        actor1 = (Actor)game.getActorList().get(0);
        Image sheetImage = new Image(actor1.getImagePath());
        sheet = new SpriteSheet(sheetImage, 32, 32);
        
        Image [] movementUp = {sheet.getSprite(0, 0), sheet.getSprite(1, 0), sheet.getSprite(2, 0)};
        Image [] movementRight = {sheet.getSprite(0, 1), sheet.getSprite(1, 1), sheet.getSprite(2, 1)};
        Image [] movementDown = {sheet.getSprite(0, 2), sheet.getSprite(1, 2), sheet.getSprite(2, 2)};
        Image [] movementLeft = {sheet.getSprite(0, 3), sheet.getSprite(1, 3), sheet.getSprite(2, 3)};
        
        //change animation every 300 ms
        int [] duration = {300, 300, 300};
        
        up = new Animation(movementUp, duration, false);
        right = new Animation(movementRight, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);

        // Original orientation of the sprite. It will look right.
        spriteAnimation= up;
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        //double delta = 1;
        Input input = gc.getInput();
        boolean blockUp = false;
        boolean blockDown = false;
        boolean blockLeft = false;
        boolean blockRight = false;
        for(int i = 0; i < currentMapBlocks.size(); i++) {
            if(currentMapBlocks.get(i).getTile() == actor1.getTile() + 1) {
                if(currentMapBlocks.get(i).getIsBlockedLeft())
                    blockRight = true;
            }
            if(currentMapBlocks.get(i).getTile() == actor1.getTile() - 1) {
                if(currentMapBlocks.get(i).getIsBlockedRight())
                    blockLeft = true;
            }
            if(currentMapBlocks.get(i).getTile() == actor1.getTile() + 50) {
                if(currentMapBlocks.get(i).getIsBlockedTop())
                    blockDown = true;
            }
            if(currentMapBlocks.get(i).getTile() == actor1.getTile() - 50) {
                if(currentMapBlocks.get(i).getIsBlockedBottom())
                    blockUp = true;
            }
        }
        if (input.isKeyDown(Input.KEY_UP)||actor1.getDirection()==Actor.Direction.UP)
        {
            spriteAnimation = up;
            spriteAnimation.update(delta);
            if(!blockUp)
            {
                actor1.move(0, -delta * 0.1d);
                actor1.setDirection(Actor.Direction.UP);
                if (actor1.getDirection().equals(Actor.Direction.NONE))
                    checkForObject();
            }
        }
        else if (input.isKeyDown(Input.KEY_DOWN)||actor1.getDirection()==Actor.Direction.DOWN)
        {
            spriteAnimation = down;
            spriteAnimation.update(delta);
            if(!blockDown)
            {
                actor1.move(0, delta * 0.1d);
                actor1.setDirection(Actor.Direction.DOWN);
                if (actor1.getDirection().equals(Actor.Direction.NONE))
                    checkForObject();
            }
                
        }
        else if (input.isKeyDown(Input.KEY_RIGHT)||actor1.getDirection()==Actor.Direction.RIGHT)
        {
            spriteAnimation = right;
            spriteAnimation.update(delta);
            if(!blockRight)
            {
                actor1.move(delta * 0.1d, 0);
                actor1.setDirection(Actor.Direction.RIGHT);
                if (actor1.getDirection().equals(Actor.Direction.NONE))
                    checkForObject();
            }
        }
        else if (input.isKeyDown(Input.KEY_LEFT)||actor1.getDirection()==Actor.Direction.LEFT)
        {
            spriteAnimation = left;
            spriteAnimation.update(delta);
            if(!blockLeft)
            {
                actor1.move(-delta * 0.1d, 0);
                actor1.setDirection(Actor.Direction.LEFT);
                if (actor1.getDirection().equals(Actor.Direction.NONE))
                    checkForObject();
            }
        }
        
    }
    
    public void checkForObject()
    {
        for (MapObject o: objectsOnMap)
        {
            if(o.getTile()==actor1.getTile())
            {
                if(o instanceof rpgdesigner.Event)
                {
                    rpgdesigner.Event e = (rpgdesigner.Event)o;
                    Object[] list = e.getActions();
                    for (int i = 0; i < list.length; i++)
                    {
                        Action a = (Action)list[i];
                        if (a.getCategory()== Action.Category.NPC)
                            if (a.getType() == Action.Type.SPEECH)
                            {
                                timeForText=200;
                                text=a.getSetting();
                            }
                            else if (a.getType() == Action.Type.MOVE_NPC)
                            {
                                distanceLeftForNPC = a.getValue()*32;
                                movingNPC=e.getAssignedNPC();
                                directionOfMovingNPC=a.getSetting();
                            }
                    }
                }
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        //render the 1st and 2nd layers
        layer1.draw(0,0);
        layer2.draw(0,0);
        //render actor image
        spriteAnimation.draw(actor1.getLocX(), actor1.getLocY());
        
        //Draw all items that were added to the map
        //skip item zero which we are assuming is actor1 - need to fix this
        for (int i =1 ; i<objectsOnMap.size();i++)
        {
            MapObject o = objectsOnMap.get(i);
            o.getSlickImage().draw(o.getLocX(), o.getLocY());
        }
        
        //render 3rd layer
        layer3.draw(0,0);
        if(timeForText>0)
        {
            displayText(g);
        }
        if(distanceLeftForNPC>0)
        {
            moveNPC();
        }
    }
}
