/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.util.List;

/**
 *
 * @author Fran
 */
public class Posse {
    
    private List<Actor> actors;
    //private List<Item> items;
    
    public List<Actor> getActors()
    {
        return actors;
    }
    
    public void setActors(List<Actor> actors)
    {
        this.actors = actors;
    }
    
    public void addActor (Actor a)
    {
        actors.add(a);
    }
    
    public void removeActor(Actor a)
    {
        actors.remove(a);
    }
    
}
