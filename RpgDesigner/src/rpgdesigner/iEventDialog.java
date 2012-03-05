/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Fran
 */
public class iEventDialog extends JDialog
{
    ButtonGroup bgDirections;
    JRadioButton rbUp, rbLeft, rbRight, rbDown;
    JTextField tfDistance;
    JButton btnOk, btnCancel;
    String value;
    JFrame mainFrame;
            
    public iEventDialog (JFrame frame, String type)
    {
        super(frame, true);
        mainFrame = frame;
        value="";
        if (type.compareTo("Move")==0)
        {
            this.setLayout(new FlowLayout());
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
            add(new JLabel("Pick a Direction: "));
            add(rbUp);
            add(rbDown);
            add(rbLeft);
            add(rbRight);
            add(new JLabel("Enter a Distance"));
            add(tfDistance);
            
            
             //setLocationRelativeTo(frame);
        
        }
        btnOk = new JButton("OK");
        btnCancel = new JButton("Cancel");
        btnOk.addActionListener(new EventDialogActionListener());
        btnCancel.addActionListener(new EventDialogActionListener());
        add(btnOk);
        add(btnCancel);
        pack();
        setVisible(true);
    }
    
    public  String getValue()
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
                if (rbUp.isSelected())
                value = "Move up "+ tfDistance.getText();
                else if (rbDown.isSelected())
                value = "Move down "+ tfDistance.getText();
                else if (rbLeft.isSelected())
                value = "Move left "+ tfDistance.getText();
                else if (rbRight.isSelected())
                value = "Move right "+ tfDistance.getText();
                System.out.println("The value is: "+value);
            }
            iEventDialog.this.setVisible(false);
        }
        
    }
}
