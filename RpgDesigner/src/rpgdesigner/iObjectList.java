/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.*;
import java.awt.event.*;
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
    ObjectType Type;
    
    
    public iObjectList (Object[] a, JFrame f, ObjectType t)
    {
        Type = t;
        frame = f;
        //CardLayout allows you to switch between viewing the list, and veiwing the iObjects
        cl = new CardLayout();
        this.setLayout(cl);
        
        //Create a list
        JPanel pList = new JPanel(new BorderLayout());
        listModel = new DefaultListModel();
        list = new JList(listModel); 
        
        //Add the objects to the list
        for(int i=0;i<a.length;i++)
        {
            listModel.addElement(a[i]);
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
        add(pObject, "Object");
    }
   public enum ObjectType{
		ACTOR,
		MAP;
	}

    private class ListListener implements ActionListener
    {
        

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource()==btnEdit)
            {
                //Get the selected object
                iObject= (iListableObject)list.getSelectedValue();
                pObject.removeAll();
                
                //Display the object
                pObject.add(iObject.getPanel(), BorderLayout.CENTER);
                pObject.add(pObjectButtons, BorderLayout.SOUTH);
                listModel.removeElement(list.getSelectedValue());
                cl.show(iObjectList.this, "Object");
                
            }
            else if (e.getSource()==btnDelete)
            {
                listModel.removeElement(list.getSelectedValue());
            }
            else if (e.getSource()==btnAdd)
            {
                //Create the new iObject
                switch (Type)
                {
                    case ACTOR:
                     {
                         iObject= new iActor(frame, new Actor());
                         break;
                     }
                    case MAP:
                    {
                        iObject = new iMap(new Map());
                    }
                }
               
                //Display iObject
                pObject.removeAll();
                pObject.add(iObject.getPanel(), BorderLayout.CENTER);   
                pObject.add(pObjectButtons, BorderLayout.SOUTH);
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
                    listModel.addElement(iObject);
                }
                else if (e.getSource() == btnCancel)
                {
                    //Add the original object back to the list
                    listModel.addElement(iObject);

                }
                cl.show(iObjectList.this, "List");
            
        }
    }
}