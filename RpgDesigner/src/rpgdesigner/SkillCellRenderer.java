/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

/**
 *
 * @author Fran
 */

    
    public class SkillCellRenderer extends JPanel implements ListCellRenderer
    {
        JLabel lblName, lblSP, lblLevel, lblDamage;

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Skill skill = (Skill) value;
            lblName = new JLabel(skill.getName());
            
            lblSP = new JLabel(Integer.toString(skill.getSPUsed()));
            lblLevel= new JLabel(Integer.toString(skill.getLvlReq()));
            lblDamage = new JLabel(Integer.toString(skill.getDamage()));
            
            Color HIGHLIGHT_COLOR = Color.cyan;
    
        if (isSelected) {
          setBackground(HIGHLIGHT_COLOR);
          setForeground(Color.white);
        } else {
          setBackground(Color.white);
          setForeground(Color.black);
        }
        //this.setSize(500, 10);
        //lblName.setSize(500, 500);
        this.removeAll();
        this.add(lblName);
        this.add(lblSP);
        this.add(lblLevel);
        this.add(lblDamage);
        return this;
        }
        
    }
    

