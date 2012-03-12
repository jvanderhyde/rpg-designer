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
    public JPanel getPanel();
    public void saveObject();
    public void reset();
    public void setObject(Object o);
    public Object getObject();
    public boolean hasInvalidInput();
    
}
