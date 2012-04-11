package rpgdesigner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    private String description;
    private String author;
    private boolean isSaveAnywhere;
    private Event loseEvent;
    private Event winEvent;
    private Map startMap;
    private int startPositionX;
    private int startPositionY;
    private Posse startPosse;
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
    
    public void saveProject() {
        new File(this.gameName).mkdir();
        
        //Save the actors into an xml file
            Document actorXML = saveActors();
        //Save the items into an xml file
            Document itemXML = saveItems();   
        //Save the Events into an xml file
            Document eventXML = saveEvents();
        //Save the maps into an xml file
        
        //Save the settings into an xml file
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
            
            //TODO implement location
            
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
            
            events.appendChild(event);
        }
        
        return new Document(events);
    }
    
    public void loadProject(File projectFile) {
        
    }
    
    //These are the getters for the game class variables
    public String getGameName() {
        return gameName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public boolean getIsSaveAnywhere() {
        return isSaveAnywhere; 
    }
    
    public Event getLoseEvent() {
        return loseEvent;
    }
    
    public Event getWinEvent() {
        return winEvent;
    }
    
    //These are the setters for the Game class variables
    public void setGameName(String name) {
        gameName = name;
    }
    
    public void setDescription(String desc) {
        description = desc;
    }
    
    public void setAuthor(String auth) {
        author = auth;
    }
    
    public void setIsSaveAnywhere(boolean isSave) {
        isSaveAnywhere = isSave;
    }
    
    public void setWinEvent(Event toWinEvent) {
        winEvent = toWinEvent;
    }
    
    public void setLoseEvent(Event toLoseEvent) {
        loseEvent = toLoseEvent;
    }
    
    public void setStartMap(Map m) {
        this.startMap = m;
    }
    
    public void setStartPosition(String x, String y) {
        this.startPositionX = Integer.parseInt(x);
        this.startPositionY = Integer.parseInt(y);
    }
    
    public void setStartPosse(List<Actor> actors) {
        for(Actor a : actors) {
            this.startPosse.addActor(a);
        }
    }

    public List<Object> getMapList() {
        return this.mapList;
    }
    
    public List<Object> getActorList(){
        return actorList;
    }
    
    public Map getStartMap() {
        return this.startMap;
    }
    
    public String getStartPositionX() {
        return String.valueOf(startPositionX);
    }
    
    public String getStartPositionY() {
        return String.valueOf(startPositionY);
    }
    
    public Posse getStartPosse() {
        return this.startPosse;
    }
}
