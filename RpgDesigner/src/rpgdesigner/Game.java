package rpgdesigner;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import nu.xom.*;

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
        List<Integer> posseToStart0 = new ArrayList();
        for(int i = 0; i < 3; i++) {
            posseToStart0.add(0);
        }
        startPosse.setActors(posseToStart0);
    }
    
    //This class works for loading an already created project
    public Game(File savedProject) {
        
    }
    
    public void saveProject(File location) throws IOException {
        location = new File(location + "/" + this.gameName);
        new File(location.toString()).mkdir();
        PrintWriter out = new PrintWriter(new FileWriter(new File(location+"/actors.xml")));
        //Save the actors into an xml file
            Document actorXML = saveActors();
            out.print(actorXML.toXML());
            out.close();
        //Save the items into an xml file
            Document itemXML = saveItems(); 
            out = new PrintWriter(new FileWriter(new File(location+"/items.xml")));
            out.print(itemXML.toXML());
            out.close();
        //Save the Events into an xml file
            Document eventXML = saveEvents();
            out = new PrintWriter(new FileWriter(new File(location+"/events.xml")));
            out.print(eventXML.toXML());
            out.close();
        //Save the maps into an xml file
            Document mapXML = saveMaps();
            out = new PrintWriter(new FileWriter(new File(location+"/maps.xml")));
            out.print(mapXML.toXML());
            out.close();
        //Save the settings into an xml file
            Document settingsXML = saveSettings();
            out = new PrintWriter(new FileWriter(new File(location+"/settings.xml")));
            out.print(settingsXML.toXML());
            out.close();
            
        //Add the game folder
        File gameFolder = new File("Game");
        if(gameFolder.exists())
            copyDirectory(gameFolder, new File(location + "/Game"));
            
        //Now lets do the magically part and create a project file with these
        //For now lets just use a folder.  No time to find out how to unzip, though this code does work
//        try {
//        BufferedInputStream origin;
//        FileOutputStream dest = new FileOutputStream(location + "/" + location.getName() + ".rpgd");
//        
//        ZipOutputStream outProj = new ZipOutputStream(new BufferedOutputStream(dest));
//        byte data[] = new byte[2048];
//        // get a list of files from current directory
//        File f = new File(location+"/"+location.getName());
//        String files[] = f.list();
//        for (int i=0; i<files.length; i++) {
//            System.out.println("Adding: "+files[i]);
//            FileInputStream fi = new 
//              FileInputStream(files[i]);
//            origin = new 
//              BufferedInputStream(fi, 2048);
//            ZipEntry entry = new ZipEntry(files[i]);
//            outProj.putNextEntry(entry);
//            int count;
//            while((count = origin.read(data, 0, 2048)) != -1) {
//               outProj.write(data, 0, count);
//            }
//            origin.close();
//         }
//         outProj.close();
//      } catch(Exception e) {
//          
//      }
    }
    
    public void copyDirectory(File sourceLocation , File targetLocation)
    throws IOException {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            
            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {
            
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);
            
            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
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
            
            Element blocks = new Element("blocks");
            for(int j = 0; j < m.getBlocks().size(); j++) {
                Element block = new Element("Tile");
                Block b = m.getBlocks().get(j);
                
                Element blockTop = new Element("BlockTop");
                blockTop.appendChild(Boolean.toString(b.getIsBlockedTop()));
                block.appendChild(blockTop);
                
                Element blockBottom = new Element("BlockBottom");
                blockBottom.appendChild(Boolean.toString(b.getIsBlockedBottom()));
                block.appendChild(blockBottom);
                
                Element blockRight = new Element("BlockRight");
                blockRight.appendChild(Boolean.toString(b.getIsBlockedRight()));
                block.appendChild(blockRight);
                
                Element blockLeft = new Element("BlockLeft");
                blockLeft.appendChild(Boolean.toString(b.getIsBlockedLeft()));
                block.appendChild(blockLeft);
                
                Element tileNum = new Element("TileNum");
                tileNum.appendChild(Integer.toString(b.getTile()));
                block.appendChild(tileNum);
                
                Element tileX = new Element("locX");
                tileX.appendChild(Float.toString(b.getLocX()));
                block.appendChild(tileX);
                
                Element tileY = new Element("locY");
                tileY.appendChild(Float.toString(b.getLocY()));
                block.appendChild(tileY);
                
                
                blocks.appendChild(block);
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
    
    /*
     * Load files from folder selected after selecting load from the menu
     */
    public void loadProject(File projectFolder) {
        //TODO: Load objects from the maps
        loadActors(projectFolder); 
        loadItems(projectFolder);
        loadEvents(projectFolder);
        loadMaps(projectFolder);
        loadSettings(projectFolder);
    }
    
    /*
     * Start the methods that load each object from xml
     */
    private void loadActors(File location) {
            Builder parser = new Builder();
            Document actorsXML = null;
        try {
            actorsXML = parser.build(location + "actors.xml");
        } catch (ParsingException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < actorsXML.getChildCount(); i++) {
            Node actorNode = actorsXML.getChild(i);
            Actor a = new Actor();
            a.setName(actorNode.getChild(0).getValue());

            a.setImagePath(actorNode.getChild(1).getValue());

            a.setType(Integer.parseInt(actorNode.getChild(2).getValue()));

            a.setLocation(Integer.parseInt(actorNode.getChild(3).getValue()), 
                    Integer.parseInt(actorNode.getChild(4).getValue()));

            a.setBegHP(Integer.parseInt(actorNode.getChild(5).getValue()));

            a.setBegSP(Integer.parseInt(actorNode.getChild(6).getValue()));

            a.setIncreaseHP(Integer.parseInt(actorNode.getChild(7).getValue()));

            a.setIncreaseSP(Integer.parseInt(actorNode.getChild(8).getValue()));

            a.setIncreaseXP(Integer.parseInt(actorNode.getChild(9).getValue()));


            for(int j = 0; j < actorNode.getChild(10).getChildCount(); j++) {
                Skill s = new Skill();
                Node skillFromXML = actorNode.getChild(10).getChild(i);

                s.setName(skillFromXML.getChild(0).getValue());

                s.setImagePath(skillFromXML.getChild(1).getValue());

                s.setSPUsed(Integer.parseInt(skillFromXML.getChild(2).getValue()));

                s.setlvlReq(Integer.parseInt(skillFromXML.getChild(3).getValue()));

                s.setdamage(Integer.parseInt(skillFromXML.getChild(4).getValue()));
                
                a.getSkillsList().addElement(s);
            }
            
            this.actorList.add(a);
        }
    }
    
    private void loadItems(File location) {
        Builder parser = new Builder();
        Document itemsXML = null;
        try {
            itemsXML = parser.build(location + "items.xml");
        } catch (ParsingException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < itemsXML.getChildCount(); i++) {
            Item it = new Item();
            Node itemFromXML = itemsXML.getChild(i);
            
            it.setName(itemFromXML.getChild(0).getValue());
            
            it.setImagePath(itemFromXML.getChild(1).getValue());
            
            it.setType(it.getItemType(itemFromXML.getChild(2).getValue()));
            
            it.setIncreaseHP(Integer.parseInt(itemFromXML.getChild(3).getValue()));
            
            it.setIncreaseSP(Integer.parseInt(itemFromXML.getChild(4).getValue()));
            
            it.setIncreaseXP(Integer.parseInt(itemFromXML.getChild(5).getValue()));
            
            it.setLocation(Float.parseFloat(itemFromXML.getChild(6).getValue()), 
                    Float.parseFloat(itemFromXML.getChild(7).getValue()));
            
            it.setTile(Integer.parseInt(itemFromXML.getChild(8).getValue()));
            
            this.itemList.add(it);
        }
    }
    
    public void loadEvents(File location) {
        Builder parser = new Builder();
        Document eventsXML = null;
        try {
            eventsXML = parser.build(location + "events.xml");
        } catch (ParsingException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < eventsXML.getChildCount(); i++) {
            Event e = new Event();
            Node eventFromXML = eventsXML.getChild(i);
            
            e.setName(eventFromXML.getChild(0).getValue());
            
            e.setOnActionKey(Boolean.parseBoolean(eventFromXML.getChild(1).getValue()));
            
            e.setLocation(Float.parseFloat(eventFromXML.getChild(2).getValue()), 
                    Float.parseFloat(eventFromXML.getChild(3).getValue()));
            
            e.setTile(Integer.parseInt(eventFromXML.getChild(4).getValue()));
            
            for(int j = 0; j < eventFromXML.getChild(5).getChildCount(); j++) {
                Node actionFromXML = eventFromXML.getChild(5);
                Action a = null;
                a = new Action(a.getActionCategory(actionFromXML.getChild(0).getValue()), 
                        a.getActionType(actionFromXML.getChild(1).getValue()));
                
                a.setDisplayedValue(actionFromXML.getChild(2).getValue());
                
                a.setValue(Integer.parseInt(actionFromXML.getChild(3).getValue()));
                
                a.setSetting(actionFromXML.getChild(4).getValue());
                
                //It isn't easy for me to add these back at this point.  Postponing this step.  Events  will not
                //completely load when loading a save.
            }
            this.eventList.add(e);
        }
    }
    
    public void loadMaps(File location) {
        Builder parser = new Builder();
        Document mapsXML = null;
        try {
            mapsXML = parser.build(location + "maps.xml");
        } catch (ParsingException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < mapsXML.getChildCount(); i++) {
            Map m = new Map();
            Node mapFromXML = mapsXML.getChild(i);
            
            m.setName(mapFromXML.getChild(0).getValue());
            
            Node blockXML = mapsXML.getChild(1);
            for(int j = 0; j < blockXML.getChildCount(); j++) {
                Block b = new Block();
                
                b.setIsBlockedTop(Boolean.parseBoolean(blockXML.getChild(i).getChild(0).getValue()));
                
                b.setIsBlockedBottom(Boolean.parseBoolean(blockXML.getChild(i).getChild(1).getValue()));
                
                b.setIsBlockedRight(Boolean.parseBoolean(blockXML.getChild(i).getChild(2).getValue()));
                
                b.setIsBlockedLeft(Boolean.parseBoolean(blockXML.getChild(i).getChild(3).getValue()));
                
                b.setTile(Integer.parseInt(blockXML.getChild(i).getChild(4).getValue()));
                
                b.setLocation(Float.parseFloat(blockXML.getChild(i).getChild(5).getValue()), 
                        Float.parseFloat(blockXML.getChild(i).getChild(6).getValue()));
                
                m.getBlocks().add(b);
            }
            
            //Here we will load the tiles from each layer image
            BufferedImage imageLayer1 = null, imageLayer2 = null, imageLayer3 = null;
            try {
                imageLayer1 = ImageIO.read(new File(location + "/Game/Maps/" + m.getName() + "/layer1.png"));
                imageLayer2 = ImageIO.read(new File(location + "/Game/Maps/" + m.getName() + "/layer2.png"));
                imageLayer3 = ImageIO.read(new File(location + "/Game/Maps/" + m.getName() + "/layer3.png"));
            } catch (IOException ex) {
                Logger.getLogger(iMap.class.getName()).log(Level.SEVERE, null, ex);
            }
            int k = 0;
            for(int y = 0; y < 1600; y+=32) {
                for(int x = 0; x < 1600; x+=32) {
                    Tile tileLayer1 = new Tile();
                    tileLayer1.setImage(imageLayer1.getSubimage(x, y, 32, 32));
                    m.getLayer1().set(k, tileLayer1);
                    Tile tileLayer2 = new Tile();
                    tileLayer2.setImage(imageLayer2.getSubimage(x, y, 32, 32));
                    m.getLayer2().set(k, tileLayer2);
                    Tile tileLayer3 = new Tile();
                    tileLayer3.setImage(imageLayer3.getSubimage(x, y, 32, 32));
                    m.getLayer3().set(k, tileLayer3);
                    k++;
                }
            }
            this.mapList.add(m);   
        }
    }
    
    public void loadSettings(File location) {
        Builder parser = new Builder();
        Document settingsXML = null;
        try {
            settingsXML = parser.build(location + "settings.xml");
        } catch (ParsingException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Node settingsFromXML = settingsXML.getChild(0);
        
        this.gameName = settingsFromXML.getChild(0).getValue();
        
        this.gameDescription = settingsFromXML.getChild(1).getValue();
        
        this.gameAuthor = settingsFromXML.getChild(2).getValue();
        
        this.setLoseEvent(Integer.parseInt(settingsFromXML.getChild(3).getValue()));
        
        this.setWinEvent(Integer.parseInt(settingsFromXML.getChild(4).getValue()));
        
        this.setStartMap(Integer.parseInt(settingsFromXML.getChild(5).getValue()));
        
        List<Integer> startActors = new ArrayList();
        for(int i = 0; i < 3; i++) {
            startActors.add(Integer.parseInt(settingsFromXML.getChild(6+i).getValue()));
        }
        this.setStartPosse(startActors);
        
        this.setIsSaveAnywhere(Boolean.parseBoolean(settingsFromXML.getChild(9).getValue()));
    }
    
    /*
     * End the methods that load each object from xml
     */
    
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
}
