/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    JFileChooser fcImage;
    private String imagePath;
    
    private void setUpFields()
    {
        
        this.setLayout(new BorderLayout());
        
        //West Panel contains image and change image button
        pWest = new JPanel();
        pWest.setSize(1, 5);
        pWest.setLayout(new BorderLayout());
        lblImage = new JLabel("Image goes here");
        btnChangeAnimation = new JButton ("Change Animation");
        btnChangeAnimation.setSize(1,3);
        pWest.add(lblImage, BorderLayout.NORTH);
        pWest.add(btnChangeAnimation, BorderLayout.SOUTH);
        btnChangeAnimation.addActionListener(new SkillListener());
        //Create a file chooser for the image
        fcImage = new JFileChooser();
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
        pEast.add(lblSP);
        pEast.add(taSP);
        pEast.add(lblLvl);
        pEast.add(taLvl);
        pEast.add(lblDamage);
        pEast.add(taDamage);
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
        imagePath = skill.getImagePath();
        loadImage();
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }
            
    public Skill getSkill()
    {
        
        return skill;
    }
    
    public void loadImage()
    {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(imagePath));
            pWest.remove(lblImage);
            lblImage = new JLabel(new ImageIcon( myPicture ));
            lblImage.setSize(50, 50);


            pWest.add(lblImage,BorderLayout.NORTH);

            //mainFrame.pack();
        } catch (IOException ex) {
            System.out.println(ex.toString());

        }
    }
    
    private class SkillListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource()==btnSave)
            {
                skill = new Skill(taName.getText(),  imagePath, getFieldInt(taSP), getFieldInt(taLvl), getFieldInt(taDamage));           
                iSkillEditor.this.setVisible(false);
            }
            else if (e.getSource() == btnCancel)
            {
                iSkillEditor.this.setVisible(false);
            }
            else if (e.getSource() == btnChangeAnimation)
            {
                int returnVal = fcImage.showOpenDialog(iSkillEditor.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fcImage.getSelectedFile();
                    //This is where a real application would open the file.
                    imagePath = file.getPath();
                    System.out.println("Opening: " + imagePath + "." );
                    
                    iSkillEditor.this.loadImage();


                } else {
                    System.out.println("Open command cancelled by user." );
                }
            }
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
