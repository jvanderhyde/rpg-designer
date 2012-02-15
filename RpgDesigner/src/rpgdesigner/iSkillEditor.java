/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author Fran
 */
public class iSkillEditor extends JDialog {
    
    private JLabel lblImage;
    private JTextField taName, taLvl, taDamage, taSP;
    private JLabel lblName, lblLvl, lblDamage, lblSP;
    private JButton btnChangeAnimation, btnSave, btnCancel;
    private JPanel pWest, pEast, pSouth;
    private Skill skill;
    
    private void setUpFields()
    {
        
        this.setLayout(new BorderLayout());
        
        //West Panel contains image and change image button
        pWest = new JPanel();
        pWest.setSize(1, 5);
        pWest.setLayout(new GridLayout(2,1));
        lblImage = new JLabel("Image goes here");
        btnChangeAnimation = new JButton ("Change Animation");
        btnChangeAnimation.setSize(1,3);
        pWest.add(lblImage);
        pWest.add(btnChangeAnimation);
        //pWest.
        this.add(pWest, BorderLayout.WEST);
        
        //East Panel Contains text areas and labels for Name, level, damage, SP
        pEast = new JPanel();
        pEast.setLayout(new GridLayout(4,2));
        lblName = new JLabel("Name");
        taName = new JTextField();
        lblLvl = new JLabel("Level");
        taLvl = new JTextField();
        lblDamage = new JLabel("Damage");
        taDamage = new JTextField();
        lblSP = new JLabel("SP");
        taSP = new JTextField();
        
        
        pEast.add(lblName);
        pEast.add(taName);
        pEast.add(lblLvl);
        pEast.add(taLvl);
        pEast.add(lblDamage);
        pEast.add(taDamage);
        pEast.add(lblSP);
        pEast.add(taSP);
        add(pEast, BorderLayout.EAST);
        
        //South Panel contains save and cancel button
        pSouth =  new JPanel();
        btnSave= new JButton("Save");
        btnCancel = new JButton("Cancel");
        btnSave.addActionListener(new SkillListener());
        btnCancel.addActionListener(new SkillListener());
        pSouth.add(btnSave);
        pSouth.add(btnCancel);
        add(pSouth, BorderLayout.SOUTH);
        
        
        //getContentPane().add(this);
        
        
    }
    
    public iSkillEditor (JFrame frame, boolean modal)
    {
        super(frame, modal);
        this.setUpFields();
        
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }
    
    public iSkillEditor (JFrame frame, boolean modal, Skill skill)
    {
        super(frame, modal);
        setUpFields();
        taDamage.setText(Integer.toString(skill.getDamage()));
        this.taLvl.setText(Integer.toString(skill.getLvlReq()));
        this.taName.setText(skill.getName());
        this.taSP.setText(Integer.toString(skill.getSPUsed()));
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }
            
    public Skill getSkill()
    {
        
        return skill;
    }
    
    private class SkillListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource()==btnSave)
                skill = new Skill(taName.getText(),  "no path", getFieldInt(taSP), getFieldInt(taLvl), getFieldInt(taDamage));
            
            iSkillEditor.this.setVisible(false);
            
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
}
