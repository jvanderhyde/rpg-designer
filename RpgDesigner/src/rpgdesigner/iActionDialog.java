/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Fran
 */
public class iActionDialog extends JDialog
{
    ButtonGroup bgDirections;
    JRadioButton rbUp, rbLeft, rbRight, rbDown;
    JTextField tfDistance;
    JButton btnOk, btnCancel;
    Action value;
    JFrame mainFrame;
    Action.Type type;
    Action.Category category;
            
    public iActionDialog (JFrame frame, Action.Category c, Action.Type t)
    {
        
        super(frame, true);
        type = t;
        category = c;
        mainFrame = frame;
        value=new Action(c,t);
        if (type == Action.Type.ACTION_MOVE_NPC || type == Action.Type.ACTION_MOVE)
        {
            this.setLayout(new BorderLayout());
            JPanel pDirections = new JPanel();
            rbUp = new JRadioButton ("Up");
            rbDown = new JRadioButton ("Down");
            rbLeft = new JRadioButton ("Left");
            rbRight = new JRadioButton ("Right");
            bgDirections = new ButtonGroup();
            bgDirections.add(rbUp);
            bgDirections.add(rbDown);
            bgDirections.add(rbLeft);
            bgDirections.add(rbRight);
            tfDistance = new JTextField();
            tfDistance.setColumns(15);
            pDirections.add(new JLabel("Pick a Direction: "));
            pDirections.add(rbUp);
            pDirections.add(rbDown);
            pDirections.add(rbLeft);
            pDirections.add(rbRight);
            add(pDirections, BorderLayout.NORTH);
            
            JPanel pDistance = new JPanel();
            
            pDistance.add(new JLabel("Enter a Distance"));
            pDistance.add(tfDistance);
            add(pDistance, BorderLayout.CENTER);
            
            setLocationRelativeTo(frame);
        
        }
        JPanel pButtons = new JPanel();
        btnOk = new JButton("OK");
        btnCancel = new JButton("Cancel");
        btnOk.addActionListener(new EventDialogActionListener());
        btnCancel.addActionListener(new EventDialogActionListener());
        pButtons.add(btnOk);
        pButtons.add(btnCancel);
        add(pButtons, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
    
    public  Action getValue()
    {
        
        return value;
    }
    
    private class EventDialogActionListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Action performed");
            
            if (e.getSource()==btnOk)
            {
                System.out.println("Ok Clicked");
                
                //value should be passed in to the action dialog instead so you can 
                //check what type it is
                //...or use "type" that I already have being passed in the constructor
                
                //if category is move...
                if (type == Action.Type.ACTION_MOVE_NPC || type == Action.Type.ACTION_MOVE )
                {
                    value.setValue(getFieldInt(tfDistance));
                    if (rbUp.isSelected())
                        value.setSetting("up");
                    else if (rbDown.isSelected())
                        value.setSetting("down");
                    else if (rbLeft.isSelected())
                        value.setSetting("left");
                    else if (rbRight.isSelected())
                        value.setSetting("right");
                    if (type == Action.Type.ACTION_MOVE_NPC  )
                        value.setDisplayedValue("NPC move "+ value.getSetting() + " "+ tfDistance.getText());
                    else if (type == Action.Type.ACTION_MOVE )
                        value.setDisplayedValue("Possy move "+ value.getSetting() + " "+ tfDistance.getText());
                            
                            
                    System.out.println("The value is: "+value);
                }
                
                
            }
            else value =null;
            iActionDialog.this.setVisible(false);
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
}

