/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Fran
 */
public class iObjectList extends JPanel {
    
    JButton btnAdd, btnEdit, btnDelete;
    JButton btnSave, btnCancel;
    JPanel pObjectButtons;
    JFrame frame;
    DefaultListModel listModel;
    JList list;
    CardLayout cl;
    JPanel pObject;
    iListableObject iObject ;
    ObjectType type;
    Object obj;
    List<Object> objects;
    Boolean newObject;
    
    
    public iObjectList (List<Object> a, JFrame f, ObjectType t,  Game game)
    {
        newObject = true;
        objects =a;
        type = t;
        frame = f;
        switch (type)
        {
                    case ACTOR:
                     {
                         iObject= new iActor(frame, new Actor());
                         break;
                     }
                    case MAP:
                    {
                        iObject = new iMap(frame, new Map(), game);
                        break;
                    }
                    case EVENT:
                    {
                        iObject = new iEvent(frame, new Event(), game.actorList);
                        break;
                    }
                    case ITEM:
                    {
                        iObject = new iItem();
                        break;
                    }
        }
        //CardLayout allows you to switch between viewing the list, and veiwing the iObjects
        cl = new CardLayout();
        this.setLayout(cl);
        
        //Create a list
        JPanel pList = new JPanel(new BorderLayout());
        listModel = new DefaultListModel();
        list = new JList(listModel); 
        
        //Add the objects to the list
        for(int i=0;i<a.size();i++)
        {
            listModel.addElement(a.get(i));
        }
        
        JScrollPane listScroller = new JScrollPane(list);
        pList.add(listScroller, BorderLayout.NORTH);
        
        //Add, edit and delete buttons
        JPanel pButtons = new JPanel(new GridLayout(1,3));
        btnEdit = new JButton("Edit");
        btnAdd = new JButton("Add");
        btnDelete = new JButton("Delete");
        btnEdit.addActionListener(new ListListener());
        btnDelete.addActionListener(new ListListener());
        btnAdd.addActionListener(new ListListener());
        pButtons.add(btnAdd);
        pButtons.add(btnEdit);
        pButtons.add(btnDelete);
        pList.add(pButtons, BorderLayout.SOUTH);
        add(pList, "List");
        
        //Save and Cancel buttons for the objects
        pObjectButtons = new JPanel();
        btnSave = new JButton("Save");
        btnCancel = new JButton ("Cancel");
        btnSave.addActionListener(new objectListener());
        btnCancel.addActionListener(new objectListener());
        pObjectButtons.add(btnSave);
        pObjectButtons.add(btnCancel);
        pObject = new JPanel(new BorderLayout());
        pObject.add(iObject.getPanel(), BorderLayout.NORTH);   
        pObject.add(pObjectButtons, BorderLayout.SOUTH);
        add(pObject, "Object");
    }
   public enum ObjectType{
		ACTOR,
		MAP,
                EVENT,
                ITEM;
	}

    private class ListListener implements ActionListener
    {
        

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource()==btnEdit)
            {
                newObject = false;
                //Get the selected object
                obj = list.getSelectedValue();
                if(obj!=null)
                {
                    iObject.setObject(obj);
                    cl.show(iObjectList.this, "Object");
                }
                else
                    JOptionPane.showMessageDialog(frame, "Please select an object to edit", "no object selected",
                            JOptionPane.WARNING_MESSAGE);
            }
            else if (e.getSource()==btnDelete)
            {
                listModel.removeElement(list.getSelectedValue());
            }
            else if (e.getSource()==btnAdd)
            {
                //Create the new iObject
                newObject = true;
                iObject.reset();
               
                //Display iObject
                cl.show(iObjectList.this, "Object");
            }
            
            frame.pack();
        }
        
        /*
         * Gets the integer value for a text field
         */
        public int getFieldInt(JTextField tf)
        {
            String input = tf.getText();
            try {
                int x = Integer.parseInt(input);
                return x;
            }
            catch(NumberFormatException nFE) {
                tf.setForeground(Color.red);
                return -1;
            }
        }
}
    
    private class objectListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
                if (e.getSource()==btnSave)
                {
                    //Save the object and add it to the list
                    iObject.saveObject();
                    if (!iObject.hasInvalidInput())
                    {
                        obj = iObject.getObject();
                        //listModel.addElement(iObject);
                        if (newObject)
                        {
                            listModel.addElement(obj);
                            objects.add(obj);

                        }
                        else 
                        {
                            objects.set(list.getSelectedIndex(), obj);
                            listModel.setElementAt(obj, list.getSelectedIndex());
                        }
                        cl.show(iObjectList.this, "List");
                    }
                    else
                        JOptionPane.showMessageDialog(frame, "Please fix the fields highlighted in red", 
                             "Cannot Continue", JOptionPane.ERROR_MESSAGE);
                }
                else if (e.getSource() == btnCancel)
                {
                    cl.show(iObjectList.this, "List");
                }
                
                
            
        }
    }
}