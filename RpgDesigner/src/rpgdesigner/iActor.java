package rpgdesigner;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
    private JPanel pStats, pSkills;
    JList list;
    DefaultListModel skills;
    
    
    JFrame mainFrame;
    
    
    
    public  iActor(JFrame f, Actor a)
    {
        mainFrame = f;
        //test2
        this.setLayout(new BorderLayout());
        actor = a;
        if(actor.getName() != null 
                && actor.getName().length() != 0)
            tfName = new JTextField (actor.getName());
        else
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
        pStats= new JPanel();
        pStats.setLayout(new GridLayout(6,3 ));
        //pStats.setSize(2, 1);
        JLabel lblBegState = new JLabel ("BeginningState");
        JLabel lblIncrease= new JLabel ("Increase on Level");
        JLabel lblHP = new JLabel ("HP");
        //lblHP.setHorizontalTextPosition(0);
        JLabel lblSP = new JLabel ("SP");
        JLabel lblXP = new JLabel ("XP req'd for level up");
        
                
        tfBegHP = new JTextField(Integer.toString(actor.getBegHP()));
        tfIncHP = new JTextField(Integer.toString(actor.getIncreaseHP()));
        tfBegSP = new JTextField(Integer.toString(actor.getBegSP()));
        tfIncSP = new JTextField(Integer.toString(actor.getIncreaseSP()));
        //tfBegXP = new JTextField();
        tfIncXP = new JTextField(Integer.toString(actor.getIncreaseXP()));
        
        pStats.add(new JLabel ());
        pStats.add(lblBegState);
        pStats.add(lblIncrease);
        pStats.add(lblHP);
        pStats.add(tfBegHP);
        pStats.add(tfIncHP);
        
        pStats.add(lblSP);
        pStats.add(tfBegSP);
        pStats.add(tfIncSP);
        
        
        //pStats.add(tfBegXP);
        pStats.add(new JLabel());
        pStats.add(new JLabel());
        pStats.add(new JLabel());
        pStats.add(new JLabel());
        pStats.add(lblXP);
        pStats.add(tfIncXP);
        pStats.add(new JLabel());
        add(pStats, BorderLayout.EAST);
        
        //Skills
        pSkills= new JPanel();
        JPanel pSkillLabels = new JPanel();
        pSkillLabels.setLayout(new GridLayout(1,4));
        pSkills.setLayout(new BorderLayout());
        JLabel lblSkillName, lblSPUsed, lblLevelReq, lblDamage;
        lblSkillName = new JLabel("Skill Name");
        lblSPUsed= new JLabel("SP Used");
        lblLevelReq = new JLabel("Level Required");
        lblDamage = new JLabel("Damage");
        pSkillLabels.add(lblSkillName);
        pSkillLabels.add(lblSPUsed);        
        pSkillLabels.add(lblLevelReq);
        pSkillLabels.add(lblDamage);
        
        btnAdd= new JButton("Add");
        btnEdit= new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnAdd.addActionListener(new iActorListener());
        btnEdit.addActionListener(new iActorListener() );
        btnDelete.addActionListener(new iActorListener());
        
        pSkills.add(pSkillLabels, BorderLayout.NORTH);
        add(pSkills, BorderLayout.SOUTH);
        
        //Image
        btnChangeImg = new JButton("Change Image");
        add(btnChangeImg, BorderLayout.WEST);
        
        //
            
            skills = new DefaultListModel();
            list = new JList(skills); //data has type Object[]
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.addListSelectionListener(null);
            
            //list.setV.setVisibleRowCount(-1);

            JScrollPane listScroller = new JScrollPane(list);
            listScroller.setPreferredSize(new Dimension(250, 80));

            pSkills.add(listScroller, BorderLayout.CENTER);
            JPanel pSkillButtons = new JPanel();
            pSkillButtons.add(btnAdd);
        pSkillButtons.add(btnEdit);
        pSkillButtons.add(btnDelete);
        pSkills.add(pSkillButtons, BorderLayout.SOUTH);
    }
    
    public Actor getActor()
    {
        //saveActor();
        return actor;
    }
    
    public void setActor(Actor a)
    {
        actor = a;
        tfName.setText(actor.getName());
        tfBegHP.setText(Integer.toString(actor.getBegHP()));
        
        
        tfIncHP.setText(Integer.toString(actor.getIncreaseHP()));
        tfBegSP.setText(Integer.toString(actor.getBegSP()));
        tfIncSP.setText(Integer.toString(actor.getIncreaseSP()));
        tfIncXP.setText(Integer.toString(actor.getIncreaseXP()));
        setActorType(actor.getType());
        
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
    
    public void setActorType(int n)
    {
        if (n == 0)
            rbPlayable.setSelected(true);
        else if (n == 1)
            rbEnemy.setSelected(true);
        else if (n==2)
            rbNPC.setSelected(true);
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
    
  private class iActorListener implements ActionListener 
          {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==btnAdd)
            {
                iSkillEditor skill;
                skill = new iSkillEditor(mainFrame, true);

                Skill newSkill = skill.getSkill();
                if (newSkill!=null)
                {
                    list.setCellRenderer(new SkillCellRenderer());
                    
                    skills.addElement(newSkill);

                    //listScroller.add(newSkill);
                    //list.setSelectedIndex(list.getSelectedIndex());
                    //list.ensureIndexIsVisible(list.getSelectedIndex());
                    //iActor.this.mainFrame.pack();
                }
                //iActor.this.removeAll();
                //iActor.this.
                //iActor.this.add(skill);
                //throw new UnsupportedOperationException("Not supported yet.");
            
            }
            else if (e.getSource()==btnEdit)
            {
                
                iSkillEditor skillEdit;
                Skill value = (Skill)list.getSelectedValue();
                skillEdit = new iSkillEditor(mainFrame, true, value);
                
                Skill newSkill = skillEdit.getSkill();
                
                if (newSkill!=null)
                {
                    list.setCellRenderer(new SkillCellRenderer());
                    value=newSkill;
                    int index = list.getSelectedIndex();
                    skills.remove(index);
                    int size = skills.getSize();
                    if (index == skills.getSize()) {
                        //removed item in last position
                        index--;
                    }
                    skills.addElement(newSkill);
                    list.setSelectedValue(newSkill, true);

                    //list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }   
            }
            else if (e.getSource()==btnDelete)
            {
                
                
                Skill value = (Skill)list.getSelectedValue();
                
                //iSkill[] skz = new iSkill[2];
                
                    int index = list.getSelectedIndex();
                    skills.remove(index);
                    int size = skills.getSize();
                    if (index == skills.getSize()) {
                        //removed item in last position
                        index--;
                    }
                    
                    //list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                } 
        
            }
                
            }
          
  }

  
