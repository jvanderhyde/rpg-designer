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
    Object iObject ;
    
    public iObjectList (Object[] a, JFrame f)
    {
        frame = f;
        cl = new CardLayout();
        this.setLayout(cl);
        JPanel pList = new JPanel(new BorderLayout());
    
        listModel = new DefaultListModel();
        list = new JList(listModel); //data has type Object[]
        
        for(int i=0;i<a.length;i++)
        {
            listModel.addElement(a[i]);
        }
        //if (a[0].getClass() == "Actor")
            //;
        
        JScrollPane listScroller = new JScrollPane(list);
        //listScroller.setPreferredSize(new Dimension(250, 80));

        pList.add(listScroller, BorderLayout.NORTH);
        
        JPanel pButtons = new JPanel(new GridLayout(1,3));
        btnEdit = new JButton("Edit");
        btnAdd = new JButton("Add");
        btnDelete = new JButton("Delete");
        btnEdit.addActionListener(new ListListener());
        btnDelete.addActionListener(new ListListener());
        btnAdd.addActionListener(new ListListener());
        pButtons.add(btnEdit);
        pButtons.add(btnAdd);
        pButtons.add(btnDelete);
        pList.add(pButtons, BorderLayout.SOUTH);
        
        add(pList, "List");
        
        //
        iObject = new iActor(frame, new Actor());
        //iObject = new iActor(frame, (Actor)list.getSelectedValue());
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
    
    private class ListListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource()==btnEdit)
            {
                //removeAll();
                //add(new JLabel("Edit"), "La");
                iObject= new iActor(frame, (Actor)list.getSelectedValue());
                pObject.removeAll();
                
                pObject.add((iActor)iObject, BorderLayout.CENTER);
                pObject.add(pObjectButtons, BorderLayout.SOUTH);
                
                listModel.removeElement(list.getSelectedValue());
                cl.show(iObjectList.this, "Object");
                
            }
            else if (e.getSource()==btnAdd)
            {
                iObject= new iActor(frame, new Actor());
                pObject.removeAll();
                
                pObject.add((iActor)iObject, BorderLayout.CENTER);
                pObject.add(pObjectButtons, BorderLayout.SOUTH);
                
                //listModel.removeElement(list.getSelectedValue());
                cl.show(iObjectList.this, "Object");
            }
            frame.pack();
            
            //pack();
            
            //iSkillEditor.this.setVisible(false);
            
            //throw new UnsupportedOperationException("Not supported yet.");
        }
        
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
                iActor a = (iActor)iObject;
                a.saveActor();
                listModel.addElement((a).getActor());
                cl.show(iObjectList.this, "List");
            }
            else if (e.getSource() == btnCancel)
            {
                iActor a = (iActor)iObject;
                listModel.addElement((a).getActor());
                cl.show(iObjectList.this, "List");
            }
            
        }
        
    }
}
