package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Fran
 */
public class iActor extends JPanel implements iListableObject{
    private JTextField tfName;
    private Actor actor;
    
    private JTextField tfBegHP, tfIncHP ,tfBegSP, tfIncSP ,  tfIncXP;
    private JLabel image;
    private JButton btnAdd, btnEdit, btnDelete, btnChangeImg;
    private JRadioButton rbPlayable, rbEnemy, rbNPC;
    private JPanel pStats, pSkills, pImage;
    JList list;
    DefaultListModel skills;
    final JFileChooser fcImage;
    private String imagePath;
    private boolean invalidInput = false;
    
    JFrame mainFrame;
    
    @Override
    public JPanel getPanel()
    {
        return this;
    }
    
    public  iActor(JFrame f, Actor a)
    {
        mainFrame = f;
        this.setLayout(new BorderLayout());
        actor = a;
        
        //Actor Name
        if(actor.getName() != null 
                && actor.getName().length() != 0)
            tfName = new JTextField (actor.getName());
        else
            tfName = new JTextField("Type name...");
        
        tfName.setColumns(20);
        add(tfName, BorderLayout.WEST);
        
        //Actor Type Radio Buttons
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
        JLabel lblBegState = new JLabel ("BeginningState");
        JLabel lblIncrease= new JLabel ("Increase on Level");
        JLabel lblHP = new JLabel ("HP");
        JLabel lblSP = new JLabel ("SP");
        JLabel lblXP = new JLabel ("XP req'd for level up");
        tfBegHP = new JTextField(Integer.toString(actor.getBegHP()));
        tfIncHP = new JTextField(Integer.toString(actor.getIncreaseHP()));
        tfBegSP = new JTextField(Integer.toString(actor.getBegSP()));
        tfIncSP = new JTextField(Integer.toString(actor.getIncreaseSP()));
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
        
        //Add, edit, and delete buttons
        btnAdd= new JButton("Add");
        btnEdit= new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnAdd.addActionListener(new iActorListener());
        btnEdit.addActionListener(new iActorListener() );
        btnDelete.addActionListener(new iActorListener());
        
        pSkills.add(pSkillLabels, BorderLayout.NORTH);
        add(pSkills, BorderLayout.SOUTH);
        
        //Image
        pImage = new JPanel();
        pImage.setLayout(new BorderLayout());
        btnChangeImg = new JButton("Change Image");
        pImage.add(btnChangeImg, BorderLayout.SOUTH);
        image = new JLabel();
        pImage.add(image, BorderLayout.NORTH);
        add(pImage, BorderLayout.WEST);
        btnChangeImg.addActionListener(new iActorListener());
        //Create a file chooser for the image
        fcImage = new JFileChooser();

        if (actor.getSkillsList() == null)
            skills = new DefaultListModel();            
        else
            skills = actor.getSkillsList();
        
        //Display a list of skills in a scroll pane
        list = new JList(skills); 
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.addListSelectionListener(null);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        pSkills.add(listScroller, BorderLayout.CENTER);
        
        //Add, edit and delete buttons for skills
        JPanel pSkillButtons = new JPanel();
        pSkillButtons.add(btnAdd);
        pSkillButtons.add(btnEdit);
        pSkillButtons.add(btnDelete);
        pSkills.add(pSkillButtons, BorderLayout.SOUTH);
    }
    
    public Actor getActor()
    {
        return actor;
    }
    
   
    
    public void saveActor()
    {
        invalidInput = false;
        actor.setName(tfName.getText());
        actor.setType(getActorType());
        actor.setBegHP(getFieldInt(tfBegHP));
        actor.setBegSP(getFieldInt(tfBegSP));
        actor.setIncreaseHP(getFieldInt(tfIncHP));
        actor.setIncreaseSP(getFieldInt(tfIncSP));
        actor.setIncreaseXP(getFieldInt(tfIncXP));
        actor.setSkillsList(skills);
        actor.setImagePath(imagePath);
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
    /**
     * Gets the input integer value of a textField
     * @param tf - the text field to get the int from
     * @return 
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
            invalidInput = true;
            return -1;
        }
    }
    
    public boolean hasInvalidInput()
    {
        return invalidInput;
    }
    /*
     * Used in iObjectList
     */

    @Override
    public String toString() {
        return actor.toString();
    }

    @Override
    public void saveObject() {
        saveActor();
    }

    @Override
    public void reset() {
        invalidInput = false;
        actor = new Actor();
        tfName.setText("Enter Name...");
        setObject(actor);
        tfName.setText(actor.getName());
        tfBegHP.setText(Integer.toString(actor.getBegHP()));
        tfIncHP.setText(Integer.toString(actor.getIncreaseHP()));
        tfBegSP.setText(Integer.toString(actor.getBegSP()));
        tfIncSP.setText(Integer.toString(actor.getIncreaseSP()));
        tfIncXP.setText(Integer.toString(actor.getIncreaseXP()));
        setActorType(actor.getType());
        imagePath = "";
        pImage.remove(image);
//        tfBegHP.setText();
//        tfIncHP.setText(Integer.toString(actor.getIncreaseHP()));
//        tfBegSP.setText(Integer.toString(actor.getBegSP()));
//        tfIncSP.setText(Integer.toString(actor.getIncreaseSP()));
//        tfIncXP.setText(Integer.toString(actor.getIncreaseXP()));
//        setActorType(actor.getType());
//        
    
    }

    @Override
    public void setObject(Object o) {
        actor = (Actor)o;
        tfName.setText(actor.getName());
        tfBegHP.setText(Integer.toString(actor.getBegHP()));
        tfIncHP.setText(Integer.toString(actor.getIncreaseHP()));
        tfBegSP.setText(Integer.toString(actor.getBegSP()));
        tfIncSP.setText(Integer.toString(actor.getIncreaseSP()));
        tfIncXP.setText(Integer.toString(actor.getIncreaseXP()));
        setActorType(actor.getType());
        imagePath = actor.getImagePath();
        if (imagePath!=null && !imagePath.isEmpty())
        {
            System.out.println("Opening: " + imagePath + "." );
            BufferedImage myPicture;
            try {
                myPicture = ImageIO.read(new File(imagePath));
                pImage.remove(image);
                image = new JLabel(new ImageIcon( myPicture ));
                //image.setPreferredSize(new Dimension(10,10));
                pImage.add(image,BorderLayout.NORTH);
                mainFrame.pack();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        
    }

    @Override
    public Object getObject() {
        return actor;
    }
    
  private class iActorListener implements ActionListener 
  {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==btnAdd)
            {
                iSkillEditor skill;
                //Displays iSkillEditor in a new window
                skill = new iSkillEditor(mainFrame, true);

                //When iSkillEditor is closed, we get the newSkill
                Skill newSkill = skill.getSkill();
                
                //If save was clicked (not cancel) add the new skill to the list
                if (newSkill!=null)
                {
                    list.setCellRenderer(new SkillCellRenderer());
                    skills.addElement(newSkill);
                }
            }
            else if (e.getSource()==btnEdit)
            {
                //Load iSkillEditor with the selectedValue
                iSkillEditor skillEdit;
                Skill value = (Skill)list.getSelectedValue();
                skillEdit = new iSkillEditor(mainFrame, true, value);
                
                //When iSkillEditor is closed, we get the new skill
                Skill newSkill = skillEdit.getSkill();
                
                //Delete the original skill from the list and add the modified skill
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
                    list.ensureIndexIsVisible(index);
                }   
            }
            else if (e.getSource()==btnDelete)
            {
                //Get the selected skill
                Skill value = (Skill)list.getSelectedValue();
                int index = list.getSelectedIndex();
                
                //Remove if from the list
                skills.remove(index);
                int size = skills.getSize();
                if (index == skills.getSize()) {
                    //removed item in last position
                    index--;
                }
                list.ensureIndexIsVisible(index);
            } 
            else if (e.getSource() == btnChangeImg) {
                int returnVal = fcImage.showOpenDialog(iActor.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fcImage.getSelectedFile();
                    //This is where a real application would open the file.
                    imagePath = file.getPath();
                    System.out.println("Opening: " + imagePath + "." );
                    BufferedImage myPicture;
                    try {
                        myPicture = ImageIO.read(new File(imagePath));
                        pImage.remove(image);
                        image = new JLabel(new ImageIcon( myPicture ));
                        //image.setPreferredSize(new Dimension(10,10));
                        pImage.add(image,BorderLayout.NORTH);
                        mainFrame.pack();
                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                    }


                } else {
                    System.out.println("Open command cancelled by user." );
                }
            }
        
        }
                
   }
          
  }

  
