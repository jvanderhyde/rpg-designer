/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import javax.swing.JPanel;

/**
 *
 * @author Fran
 */
public interface iListableObject  {
    /*
     * Gets the panel containing the user interface for the object
     */
    public JPanel getPanel();
    
    /*
     * Saves the input information to the current object
     */
    public void saveObject();
    
    /*
     * clears the input info
     */
    public void reset();
    
    public void setObject(Object o);
    public Object getObject();
    public boolean hasInvalidInput();
    
}
