/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fran
 */
public class Posse {
    
    private List<Integer> actorsIds = new ArrayList();
    //private List<Item> items;
    
    public List<Integer> getActors()
    {
        return actorsIds;
    }
    
    public void setActors(List<Integer> ids)
    {
        this.actorsIds = ids;
    }
    
    public void addActor (int a)
    {
        actorsIds.add(a);
    }
    
    public void removeActor(int a)
    {
        actorsIds.remove(a);
    }
    
}
