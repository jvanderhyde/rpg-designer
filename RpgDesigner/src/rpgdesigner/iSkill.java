/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author Fran
 */
public class iSkill extends JPanel implements ListCellRenderer{
    JLabel lblName, lblSP, lblLevel, lblDamage;
    Skill skill;
    
    public iSkill(Skill s)
    {
        lblName= new JLabel(s.getName());
        lblSP = new JLabel(""+s.getSPUsed());
        lblLevel = new JLabel(""+s.getLvlReq());
        lblDamage = new JLabel(""+s.getDamage());
        this.setLayout(new GridLayout(1,3));
        add(lblName);
        add(lblSP);
        add(lblLevel);
        add(lblDamage);
        
    }
    
    public iSkill()
    {
        lblName= new JLabel();
        lblSP = new JLabel();
        lblLevel = new JLabel();
        lblDamage = new JLabel();
        this.setLayout(new GridLayout(1,3));
        add(lblName);
        add(lblSP);
        add(lblLevel);
        add(lblDamage);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setBackground(isSelected ? Color.blue : Color.white);
        this.setForeground(isSelected ? Color.white : Color.black);
        //this.addComponentListener(new SkillListener());
        return this;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
   
    
    
    
}
