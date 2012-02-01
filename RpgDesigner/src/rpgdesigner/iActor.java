package rpgdesigner;



import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Fran
 */
public class iActor extends JPanel{
    private JTextField tfName;
    private Actor actor;
    
    private JTextField tfBegHP, tfIncHP ,tfBegSP, tfIncSP ,  tfIncXP;
    private JLabel image;
    private JButton btnAdd, btnEdit, btnDelete, btnChangeImg;
    private JRadioButton rbPlayable, rbEnemy, rbNPC;
    
    
    public  iActor()
    {
        //test2
        this.setLayout(new BorderLayout());
        tfName = new JTextField("Type name...");
        
        tfName.setColumns(20);
        add(tfName, BorderLayout.WEST);
        //JLabel lblName = new JLabel("Name");
        //add(lblName,BorderLayout.WEST);
        
        //Actor Types
        ButtonGroup group = new ButtonGroup();
        rbPlayable = new JRadioButton("Playable");
        rbEnemy = new JRadioButton("Enemy");
        rbNPC = new JRadioButton("NPC");
        group.add(rbPlayable);
        group.add(rbEnemy);
        group.add(rbNPC);
        JPanel pRadioButtons = new JPanel();
        JPanel pCenter = new JPanel();
        pCenter.setLayout(new BorderLayout());
        pRadioButtons.add(rbPlayable );
        pRadioButtons.add(rbNPC);
        pRadioButtons.add(rbEnemy);
        pCenter.add(pRadioButtons, BorderLayout.SOUTH);
        pCenter.add(tfName,BorderLayout.NORTH);
        add(pCenter,BorderLayout.CENTER);
        //Stats
        JPanel pStats= new JPanel();
        pStats.setLayout(new GridLayout(4,3 ));
        //pStats.setSize(2, 1);
        JLabel lblBegState = new JLabel ("BeginningState");
        JLabel lblIncrease= new JLabel ("Increase on Level");
        JLabel lblHP = new JLabel ("HP");
        
        JLabel lblSP = new JLabel ("SP");
        JLabel lblXP = new JLabel ("XP req'd for level up");
        
                
        tfBegHP = new JTextField();
        tfIncHP = new JTextField();
        tfBegSP = new JTextField();
        tfIncSP = new JTextField();
        //tfBegXP = new JTextField();
        tfIncXP = new JTextField();
        
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
        //pStats.add(tfBegXP);
        pStats.add(tfIncXP);
        add(pStats, BorderLayout.EAST);
        
        //Skills
        JPanel pSkills= new JPanel();
        pSkills.setLayout(new GridLayout(2,9));
        JLabel lblSkillName, lblSPUsed, lblLevelReq, lblDamage;
        lblSkillName = new JLabel("Skill Name");
        lblSPUsed= new JLabel("SP Used");
        lblLevelReq = new JLabel("Level Required");
        lblDamage = new JLabel("Damage");
        pSkills.add(lblSkillName);
        pSkills.add(lblSPUsed);        
        pSkills.add(lblLevelReq);
        pSkills.add(lblDamage);
        
        btnAdd= new JButton("Add");
        btnEdit= new JButton("Edit");
        btnDelete = new JButton("Delete");
        pSkills.add(btnAdd);
        pSkills.add(btnEdit);
        pSkills.add(btnDelete);
        add(pSkills, BorderLayout.SOUTH);
        
        //Image
        btnChangeImg = new JButton("Change Image");
        add(btnChangeImg, BorderLayout.WEST);
    }
    
    public Actor getActor()
    {
        return actor;
    }
    
    public void saveActor()
    {
        actor.setName(tfName.getText());
        actor.setType(getActorType());
        actor.setBegHP(getFieldInt(tfBegHP));
        actor.setBegSP(getFieldInt(tfBegSP));
        actor.setIncreaseHP(getFieldInt(tfIncHP));
        actor.setIncreaseSP(getFieldInt(tfIncSP));
        actor.setIncreaseXP(getFieldInt(tfIncXP));
    }
    
    public int getActorType()
    {
        int type=0;
        if (rbPlayable.isSelected())
            type = 0;
        else if (rbEnemy.isSelected())
            type=1;
        else if (rbNPC.isSelected())
            type=2;
        return type;
                    
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
