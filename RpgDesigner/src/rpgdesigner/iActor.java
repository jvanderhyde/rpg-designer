package rpgdesigner;



import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Fran
 */
public class iActor extends JPanel{
    private JTextField tfName;
    
    public  iActor()
    {
        this.setLayout(new BorderLayout());
        tfName = new JTextField();
        
        tfName.setColumns(20);
        add(tfName, BorderLayout.CENTER);
        JLabel lblName = new JLabel("Name");
        add(lblName,BorderLayout.WEST);
        
        //Actor Types
        ButtonGroup group = new ButtonGroup();
        JRadioButton rbPlayable = new JRadioButton("Playable");
        JRadioButton rbEnemy = new JRadioButton("Enemy");
        JRadioButton rbNPC = new JRadioButton("NPC");
        group.add(rbPlayable);
        group.add(rbEnemy);
        group.add(rbNPC);
        JPanel pRadioButtons = new JPanel();
        pRadioButtons.add(rbPlayable ,BorderLayout.WEST);
        pRadioButtons.add(rbNPC,BorderLayout.WEST);
        pRadioButtons.add(rbEnemy,BorderLayout.WEST);
        
        //Stats
        JPanel pStats= new JPanel();
        pStats.setLayout(new GridLayout(4,3 ));
        //pStats.setSize(2, 1);
        JLabel lblBegState = new JLabel ("BeginningState");
        JLabel lblIncrease= new JLabel ("Increase on Level");
        JLabel lblHP = new JLabel ("HP");
        
        JLabel lblSP = new JLabel ("SP");
        JLabel lblXP = new JLabel ("XP");
        
                
        JTextField tfBegHP = new JTextField();
        
        JTextField tfIncHP = new JTextField();
        JTextField tfBegSP = new JTextField();
        JTextField tfIncSP = new JTextField();
        JTextField tfBegXP = new JTextField();
        JTextField tfIncXP = new JTextField();
        
        pStats.add(new JLabel ());
        pStats.add(lblBegState);
        pStats.add(lblIncrease);
        pStats.add(lblHP);
        pStats.add(tfBegHP);
        pStats.add(tfIncHP);
        
        pStats.add(lblSP);
        pStats.add(tfBegSP);
        pStats.add(tfIncSP);
        
        pStats.add(lblXP);
        pStats.add(tfBegXP);
        pStats.add(tfIncXP);
        add(pStats, BorderLayout.EAST);
        
        JPanel pSkills= new JPanel();
        JLabel lblSkillName, lblSPUsed, lblLevelReq, lblDamage;
        lblSkillName = new JLabel("Skill Name");
        lblSPUsed= new JLabel("SP Used");
        lblLevelReq = new JLabel("Level Required");
        lblDamage = new JLabel("Damage");
        pSkills.add(lblSkillName);
        pSkills.add(lblSPUsed);        
        pSkills.add(lblLevelReq);
        pSkills.add(lblDamage);
        add(pSkills, BorderLayout.SOUTH);
    }
    
}
