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
    Animation npcAnimation;
    
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
//        Graphics layer1Graphics = layer1.getGraphics();
        layer2 = new Image("Game/Maps/" + workingMap.getName() + "/layer2.png");
//        Graphics layer2Graphics = layer2.getGraphics();
        layer3 = new Image("Game/Maps/" + workingMap.getName() + "/layer3.png");
//        Graphics layer3Graphics = layer3.getGraphics();
        int i = 0;
//        for(int y = 0; y < 1600; y+=32) {
//            for(int x = 0; x < 1600; x+=32) {
//                Tile tile1 = workingMap.getLayer1().get(i);
//                Image tileset1 = new Image("Resources/Tilesets/GrasslandV6_0_3.png");
//                Tile tile2 = workingMap.getLayer2().get(i);
//                Image tileset2 = new Image("Resources/Tilesets/GrasslandV6_0_3.png");
//                Tile tile3 = workingMap.getLayer3().get(i);
//                Image tileset3 = new Image("Resources/Tilesets/GrasslandV6_0_3.png");
//                Image layer1Image = tileset1.getSubImage(tile1.getTilesetX(), tile1.getTilesetY(), 32, 32);
//                Image layer2Image = tileset2.getSubImage(tile2.getTilesetX(), tile2.getTilesetY(), 32, 32);
//                Image layer3Image = tileset3.getSubImage(tile3.getTilesetX(), tile3.getTilesetY(), 32, 32);
//                layer1Graphics.drawImage(
//                        layer1Image, x, y);
//                layer2Graphics.drawImage(
//                        layer2Image, x, y);
//                layer3Graphics.drawImage(
//                        layer3Image, x, y);
//                i++;
//            }
//        }
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
        npcAnimation.draw(movingNPC.getLocX(), movingNPC.getLocY());
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
        if (input.isKeyDown(Input.KEY_UP)||actor1.getDirection()==Actor.Direction.UP)
        {
            spriteAnimation = up;
            spriteAnimation.update(delta);
            //if(!currentMapBlocks.get(actor1.getNewLocTile(0, -delta * 0.1d)).getIsBlockedBottom())
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
            //if(!currentMapBlocks.get(actor1.getNewLocTile(0, delta * 0.1d)).getIsBlockedTop())
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
            //if(!currentMapBlocks.get(actor1.getNewLocTile(delta * 0.1d, 0)).getIsBlockedLeft())
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
            //if(!currentMapBlocks.get(actor1.getNewLocTile(-delta * 0.1d, 0)).getIsBlockedRight())
            {
                actor1.move(-delta * 0.1d, 0);
                actor1.setDirection(Actor.Direction.LEFT);
                if (actor1.getDirection().equals(Actor.Direction.NONE))
                    checkForObject();
            }
        }
        
       
        
    }
    
    public void checkForObject() throws SlickException
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
                                
                                Image sheetImage = new Image(movingNPC.getImagePath());
                                SpriteSheet npcSheet = new SpriteSheet(sheetImage, 32, 32);

                                Image [] movementUp = {npcSheet.getSprite(0, 0), npcSheet.getSprite(1, 0), npcSheet.getSprite(2, 0)};
                                Image [] movementRight = {npcSheet.getSprite(0, 1), npcSheet.getSprite(1, 1), npcSheet.getSprite(2, 1)};
                                Image [] movementDown = {npcSheet.getSprite(0, 2), npcSheet.getSprite(1, 2), npcSheet.getSprite(2, 2)};
                                Image [] movementLeft = {npcSheet.getSprite(0, 3), npcSheet.getSprite(1, 3), npcSheet.getSprite(2, 3)};

                                //change animation every 300 ms
                                int [] duration = {300, 300, 300};
                                
                                if(directionOfMovingNPC.equals("up"))
                                    npcAnimation = new Animation(movementUp, duration, false);
                                else if(directionOfMovingNPC.equals("down"))
                                    npcAnimation = new Animation(movementDown, duration, false);
                                else if(directionOfMovingNPC.equals("left"))
                                    npcAnimation = new Animation(movementLeft, duration, false);
                                else if(directionOfMovingNPC.equals("right"))
                                    npcAnimation = new Animation(movementRight, duration, false);
                                

                               
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
            npcAnimation.update((long)10);
        }
    }
}
