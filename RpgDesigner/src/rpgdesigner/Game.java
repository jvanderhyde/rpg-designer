package rpgdesigner;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import nu.xom.Document;
import nu.xom.Element;

/**
 *
 * @author james
 * 
 * This class is responsible holding all of the data for a game, as well as loading
 * and saving it into a project format.  
 */
public class Game {
    private String gameName;
    private String gameDescription;
    private String gameAuthor;
    private String musicFilePath;

    
    private boolean gameIsSaveAnywhere;
    private int gameLoseEvent;
    private int gameWinEvent;
    private int gameStartMap;
    private Posse startPosse = new Posse();
    List<Object> eventList = new ArrayList();
    List<Object> mapList = new ArrayList();
    List<Object> actorList = new ArrayList();
    List<Object> itemList = new ArrayList();
    //This class works for creating a new game project
    public Game() {
        eventList.add(new Event());
    }
    
    //This class works for loading an already created project
    public Game(File savedProject) {
        //TODO: Not yet implemented
    }
    
    public void saveProject(File location) throws IOException {
        new File(location+"/"+this.gameName).mkdir();
        PrintWriter out = new PrintWriter(new FileWriter(new File(location+"/"+this.gameName+"/actors.xml")));
        //Save the actors into an xml file
            Document actorXML = saveActors();
            out.print(actorXML.toXML());
            out.close();
        //Save the items into an xml file
            Document itemXML = saveItems(); 
            out = new PrintWriter(new FileWriter(new File(location+"/"+this.gameName+"/items.xml")));
            out.print(itemXML.toXML());
            out.close();
        //Save the Events into an xml file
            Document eventXML = saveEvents();
            out = new PrintWriter(new FileWriter(new File(location+"/"+this.gameName+"/events.xml")));
            out.print(eventXML.toXML());
            out.close();
        //Save the maps into an xml file
            Document mapXML = saveMaps();
            out = new PrintWriter(new FileWriter(new File(location+"/"+this.gameName+"/maps.xml")));
            out.print(mapXML.toXML());
            out.close();
        //Save the settings into an xml file
            Document settingsXML = saveSettings();
            out = new PrintWriter(new FileWriter(new File(location+"/"+this.gameName+"/settings.xml")));
            out.print(settingsXML.toXML());
            out.close();
            
        //Now lets do the magically part and create a project file with these
        try {
        BufferedInputStream origin;
        FileOutputStream dest = new FileOutputStream(location + "/" + gameName);
        ZipOutputStream outProj = new ZipOutputStream(new BufferedOutputStream(dest));
        byte data[] = new byte[2048];
        // get a list of files from current directory
        File f = new File(location+"/"+this.gameName);
        String files[] = f.list();
        for (int i=0; i<files.length; i++) {
            System.out.println("Adding: "+files[i]);
            FileInputStream fi = new 
              FileInputStream(files[i]);
            origin = new 
              BufferedInputStream(fi, 2048);
            ZipEntry entry = new ZipEntry(files[i]);
            outProj.putNextEntry(entry);
            int count;
            while((count = origin.read(data, 0, 2048)) != -1) {
               outProj.write(data, 0, count);
            }
            origin.close();
         }
         outProj.close();
      } catch(Exception e) {
          
      }
    }
    
    public String getMusicFilePath() {
        return musicFilePath;
    }

    public void setMusicFilePath(String musicFilePath) {
        this.musicFilePath = musicFilePath;
    }
    
    private Document saveActors() {
        Element actors = new Element("Actors");
            for(int i = 0; i < actorList.size(); i++) {
                Actor a = (Actor)actorList.get(i);              
                Element actor = new Element("Actor");
                
                Element name = new Element("Name");
                name.appendChild(a.getName());
                actor.appendChild(name);
                
                Element imagePath = new Element("ImagePath");
                imagePath.appendChild(a.getImagePath());
                actor.appendChild(imagePath);
                
                Element actorType = new Element("ActorType");
                actorType.appendChild(Integer.toString(a.getType()));
                actor.appendChild(actorType);
                
                Element location = new Element("location");
                Element locX = new Element("LocX");
                locX.appendChild(Float.toString(a.getLocX()));
                Element locY = new Element("LocX");
                locY.appendChild(Float.toString(a.getLocY()));
                location.appendChild(locX);
                location.appendChild(locY);
                actor.appendChild(location);
                
                Element stats = new Element("Stats");
                Element begHP = new Element("BegHP");
                begHP.appendChild(Integer.toString(a.getBegHP()));
                Element begSP = new Element("BegSP");
                begSP.appendChild(Integer.toString(a.getBegSP()));
                Element increaseHP = new Element("IncreaseHP");
                increaseHP.appendChild(Integer.toString(a.getIncreaseHP()));
                Element increaseSP = new Element("IncreaseSP");
                increaseSP.appendChild(Integer.toString(a.getIncreaseSP()));
                Element increaseXP = new Element("IncreaseXP");
                increaseXP.appendChild(Integer.toString(a.getIncreaseXP()));
                stats.appendChild(begHP);
                stats.appendChild(begSP);
                stats.appendChild(increaseHP);
                stats.appendChild(increaseSP);
                stats.appendChild(increaseXP);
                actor.appendChild(stats);
                
                //These are all the skills of each actor
                Element skills = new Element("Skills");
                for(int j = 0; j < a.getSkillsList().getSize(); j++) {
                    Skill s = (Skill)a.getSkillsList().get(i);
                    Element skill = new Element("Skill");
                    
                    Element skillName = new Element("Name");
                    skillName.appendChild(s.getName());
                    skill.appendChild(skillName);
                    
                    Element skillImagePath = new Element("ImagePath");
                    skillImagePath.appendChild(s.getImagePath());
                    skill.appendChild(skillImagePath);
                    
                    Element spUsed = new Element("SPUsed");
                    spUsed.appendChild(Integer.toString(s.getSPUsed()));
                    skill.appendChild(spUsed);
                    
                    Element lvlReq = new Element("LVLReq");
                    lvlReq.appendChild(Integer.toString(s.getLvlReq()));
                    skill.appendChild(lvlReq);
                    
                    Element damage = new Element("Damage");
                    damage.appendChild(Integer.toString(s.getDamage()));
                    skill.appendChild(damage);
                    
                    skills.appendChild(skill);
                    
                    actor.appendChild(skills);
                }
                actors.appendChild(actor);  
            }
            return new Document(actors);
    }
    
    private Document saveItems() {
        Element items = new Element("Items");
        
        for(int i = 0; i < itemList.size(); i++) {
            Item it = (Item)itemList.get(i);
            
            Element item = new Element("Item");
            
            Element name = new Element("Name");
            name.appendChild(it.getName());
            item.appendChild(name);
            
            Element imagePath = new Element("ImagePath");
            imagePath.appendChild(it.getImagePath());
            item.appendChild(imagePath);
            
            Element itemType = new Element("ItemType");
            itemType.appendChild(it.getType().toString());
            item.appendChild(itemType);
            
            Element hpEffect = new Element("HPEffect");
            hpEffect.appendChild(Integer.toString(it.getIncreaseHP()));
            item.appendChild(hpEffect);
            
            Element spEffect = new Element("SPEffect");
            spEffect.appendChild(Integer.toString(it.getIncreaseSP()));
            item.appendChild(spEffect);
            
            Element xpEffect = new Element("XPEffect");
            xpEffect.appendChild(Integer.toString(it.getIncreaseXP()));
            item.appendChild(xpEffect);
            
            Element locX = new Element("LocX");
            locX.appendChild(Float.toString(it.getLocX()));
            item.appendChild(locX);
            
            Element locY = new Element("LocY");
            locY.appendChild(Float.toString(it.getLocY()));
            item.appendChild(locY);
            
            Element tileNum = new Element("TileNumber");
            tileNum.appendChild(Integer.toString(it.getTile()));
            item.appendChild(tileNum);
            
            items.appendChild(item);
        }
        
        return new Document(items);
    }
    
    public Document saveEvents() {
        Element events = new Element("Events");
        
        for(int i = 0; i < eventList.size(); i++) {
            Event e = (Event)eventList.get(i);
            
            Element event = new Element("event");
            
            Element name = new Element("Name");
            name.appendChild(e.getName());
            event.appendChild(name);
            
            Element onActionKey = new Element("OnActionKey");
            onActionKey.appendChild(Boolean.toString(e.onActionKey()));
            event.appendChild(onActionKey);
            
            Element locX = new Element("LocX");
            locX.appendChild(Float.toString(e.getLocX()));
            event.appendChild(locX);
            
            Element locY = new Element("LocY");
            locY.appendChild(Float.toString(e.getLocY()));
            event.appendChild(locY);
            
            Element tileNum = new Element("TileNumber");
            tileNum.appendChild(Integer.toString(e.getTile()));
            event.appendChild(tileNum);
            
            Element actions = new Element("Actions");
            for(int j = 0; j < e.getActions().length; j++) {
                Element action = new Element("Action");
                Action a = (Action)e.getActions()[i];
                
                Element category = new Element("Category");
                category.appendChild(a.getCategory().toString());
                action.appendChild(category);
                
                Element type = new Element("Type");
                type.appendChild(a.getType().toString());
                action.appendChild(type);
                
                Element displayableValue = new Element("DisplayableValue");
                displayableValue.appendChild(a.getDisplayedValue());
                action.appendChild(displayableValue);
                
                Element value = new Element("Value");
                value.appendChild(Integer.toString(a.getValue()));
                action.appendChild(value);
                
                Element setting = new Element("Setting");
                setting.appendChild(a.getSetting());
                action.appendChild(setting);
                
                actions.appendChild(action);
            }
            event.appendChild(actions);
            
            events.appendChild(event);
        }
        
        return new Document(events);
    }
    
    public Document saveMaps() {
        Element maps = new Element("Maps");
        
        for(int i = 0; i < mapList.size(); i++) {
            Element map = new Element("Map");
            Map m = (Map)mapList.get(i);
            
            Element name = new Element("Name");
            name.appendChild(m.getName());
            map.appendChild(name);
            
            //Layer 1 tiles
            Element layer1Tiles = new Element("Layer1Tiles");
            for(int j = 0; j < m.getLayer1().size(); j++) {
                Element tile = new Element("Tile");
                Tile t = m.getLayer1().get(j);
                
                Element tileSetName = new Element("TileSet");
                tileSetName.appendChild(t.getTilesetName());
                tile.appendChild(tileSetName);
                
                Element tileSetX = new Element("TileSetX");
                tileSetX.appendChild(Integer.toString(t.getTilesetX()));
                tile.appendChild(tileSetX);
                
                Element tileSetY = new Element("TileSetY");
                tileSetY.appendChild(Integer.toString(t.getTilesetY()));
                tile.appendChild(tileSetY);
                
                layer1Tiles.appendChild(tile);
            }
            map.appendChild(layer1Tiles);
            
            //Layer 2 tiles
            Element layer2Tiles = new Element("Layer2Tiles");
            for(int j = 0; j < m.getLayer2().size(); j++) {
                Element tile = new Element("Tile");
                Tile t = m.getLayer2().get(j);
                
                Element tileSetName = new Element("TileSet");
                tileSetName.appendChild(t.getTilesetName());
                tile.appendChild(tileSetName);
                
                Element tileSetX = new Element("TileSetX");
                tileSetX.appendChild(Integer.toString(t.getTilesetX()));
                tile.appendChild(tileSetX);
                
                Element tileSetY = new Element("TileSetY");
                tileSetY.appendChild(Integer.toString(t.getTilesetY()));
                tile.appendChild(tileSetY);
                
                layer2Tiles.appendChild(tile);
            }
            map.appendChild(layer2Tiles);
            
            //Layer 3 tiles
            Element layer3Tiles = new Element("Layer1Tiles");
            for(int j = 0; j < m.getLayer3().size(); j++) {
                Element tile = new Element("Tile");
                Tile t = m.getLayer3().get(j);
                
                Element tileSetName = new Element("TileSet");
                tileSetName.appendChild(t.getTilesetName());
                tile.appendChild(tileSetName);
                
                Element tileSetX = new Element("TileSetX");
                tileSetX.appendChild(Integer.toString(t.getTilesetX()));
                tile.appendChild(tileSetX);
                
                Element tileSetY = new Element("TileSetY");
                tileSetY.appendChild(Integer.toString(t.getTilesetY()));
                tile.appendChild(tileSetY);
                
                layer3Tiles.appendChild(tile);
            }
            map.appendChild(layer3Tiles);
            
            //Layer 1 tiles
            Element blocks = new Element("blocks");
            for(int j = 0; j < m.getBlocks().size(); j++) {
                Element block = new Element("Tile");
                Block b = m.getBlocks().get(j);
                
                Element blockTop = new Element("BlockTop");
                blockTop.appendChild(Boolean.toString(b.getIsBlockedTop()));
                block.appendChild(block);
                
                Element blockBottom = new Element("BlockBottom");
                blockTop.appendChild(Boolean.toString(b.getIsBlockedBottom()));
                block.appendChild(block);
                
                Element blockRight = new Element("BlockRight");
                blockTop.appendChild(Boolean.toString(b.getIsBlockedRight()));
                block.appendChild(block);
                
                Element blockLeft = new Element("BlockLeft");
                blockTop.appendChild(Boolean.toString(b.getIsBlockedLeft()));
                block.appendChild(block);
            }
            map.appendChild(blocks);
            
            //TODO: implement objects
        }
        
        return new Document(maps);
    }
    
    public Document saveSettings() {
        Element settings = new Element("Settings");
        
        Element name = new Element("GameName");
        name.appendChild(this.gameName);
        settings.appendChild(name);
        
        Element description = new Element("Description");
        description.appendChild(this.gameDescription);
        settings.appendChild(description);
        
        Element author = new Element("Author");
        author.appendChild(this.gameAuthor);
        settings.appendChild(author);
        
        Element loseEvent = new Element("LoseEvent");
        loseEvent.appendChild(Integer.toString(this.gameLoseEvent));
        settings.appendChild(loseEvent);
        
        Element winEvent = new Element("WinEvent");
        winEvent.appendChild(Integer.toString(this.gameWinEvent));
        settings.appendChild(winEvent);
        
        Element startMap = new Element("StartingMap");
        startMap.appendChild(Integer.toString(this.gameStartMap));
        settings.appendChild(startMap);
        
        Element actor1 = new Element("Actor1");
        actor1.appendChild(Integer.toString(this.getStartPosse().getActors().get(0)));
        settings.appendChild(actor1);
        
        Element actor2 = new Element("Actor2");
        actor2.appendChild(Integer.toString(this.getStartPosse().getActors().get(1)));
        settings.appendChild(actor2);
        
        Element actor3 = new Element("Actor3");
        actor3.appendChild(Integer.toString(this.getStartPosse().getActors().get(2)));
        settings.appendChild(actor3);
        
        Element isSaveAnywhere = new Element("IsSaveAnywhere");
        isSaveAnywhere.appendChild(Boolean.toString(this.gameIsSaveAnywhere));
        settings.appendChild(isSaveAnywhere);
        
        return new Document(settings);
    }
    
    public void loadProject(File projectFile) {
        
    }
    
    //These are the getters for the game class variables
    public String getGameName() {
        return gameName;
    }
    
    public String getDescription() {
        return gameDescription;
    }
    
    public String getAuthor() {
        return gameAuthor;
    }
    
    public boolean getIsSaveAnywhere() {
        return gameIsSaveAnywhere; 
    }
    
    public int getLoseEvent() {
        return gameLoseEvent;
    }
    
    public int getWinEvent() {
        return gameWinEvent;
    }
    
    //These are the setters for the Game class variables
    public void setGameName(String name) {
        gameName = name;
    }
    
    public void setDescription(String desc) {
        gameDescription = desc;
    }
    
    public void setAuthor(String auth) {
        gameAuthor = auth;
    }
    
    public void setIsSaveAnywhere(boolean isSave) {
        gameIsSaveAnywhere = isSave;
    }
    
    public void setWinEvent(int toWinEvent) {
        gameWinEvent = toWinEvent;
    }
    
    public void setLoseEvent(int toLoseEvent) {
        gameLoseEvent = toLoseEvent;
    }
    
    public void setStartMap(int m) {
        this.gameStartMap = m;
    }
    
    public void setStartPosse(List<Integer> actors) {
        for (Iterator<Integer> it = actors.iterator(); it.hasNext();) {
            Integer a = it.next();
            this.startPosse.addActor(a);
        }
    }

    public List<Object> getMapList() {
        return this.mapList;
    }
    
    public List<Object> getActorList(){
        return actorList;
    }
    
    public int getStartMap() {
        return this.gameStartMap;
    }
    
    public Posse getStartPosse() {
        return this.startPosse;
    }
}
