package rpgdesigner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    List<Event> eventList = new ArrayList();
    List<Map> mapList = new ArrayList();
    List<Actor> actorList = new ArrayList();
    
    //This class works for creating a new game project
    public Game() {
        eventList.add(new Event());
    }
    
    //This class works for loading an already created project
    public Game(File savedProject) {
        //TODO: Not yet implemented
    }
    
    public void saveProject() {
        //TODO: Not yet implemented
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

    public List<Map> getMapList() {
        return this.mapList;
    }
}
