/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.BorderLayout;
import java.awt.Color;
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
public class iItem extends JPanel implements iListableObject{

    JLabel lblImage;
    JTextField tfName, tfHP, tfXP, tfSP;
    JRadioButton rbSustenance, rbKey, rbEquipment;
    Item item;
    String imagePath;
    Boolean invalidInput;
    JButton btnChangeImg;
    JPanel pWest;
    public iItem(){
        this.setLayout(new BorderLayout());
        lblImage = new JLabel("");
        btnChangeImg = new JButton("Change Image");
        btnChangeImg.addActionListener(new ItemListener());
        JLabel lblName = new JLabel("Name");
        tfName = new JTextField(20);
        JPanel pName = new JPanel();
        pName.add(lblName);
        pName.add(tfName);
        pWest = new JPanel(new BorderLayout());
        pWest.add(pName, BorderLayout.NORTH);
        pWest.add(btnChangeImg, BorderLayout.SOUTH);
        pWest.add(lblImage, BorderLayout.CENTER);
        this.add(pWest, BorderLayout.WEST);
        
        JPanel pCenter =  new JPanel();
        JLabel lblType = new JLabel("Type");
        rbSustenance =  new JRadioButton("Sustenance");
        rbKey = new JRadioButton("Key");
        rbEquipment = new JRadioButton("Equipment");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbSustenance);
        bg.add(rbKey);
        bg.add(rbEquipment);
        pCenter.add(lblType);
        pCenter.add(rbSustenance);
        pCenter.add(rbKey);
        pCenter.add(rbEquipment);
        this.add(pCenter, BorderLayout.CENTER);
        
        JPanel pStats = new JPanel(new GridLayout(4,2));
        pStats.add(new JLabel(""));
        pStats.add(new JLabel("Effect of Use in Battle"));
        
        tfHP = new JTextField();
        tfXP = new JTextField();
        tfSP = new JTextField();
        pStats.add(new JLabel("HP"));
        pStats.add(tfHP);
        pStats.add(new JLabel("SP"));
        pStats.add(tfSP);
        pStats.add(new JLabel("XP"));
        pStats.add(tfXP);
        this.add(pStats, BorderLayout.EAST);
        
    }
    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void saveObject() {
        invalidInput = false;
        item.setImagePath(imagePath);
        item.setIncreaseHP(WIDTH);
        item.setIncreaseHP(getFieldInt(tfHP));
        item.setIncreaseSP(getFieldInt(tfSP));
        item.setIncreaseXP(getFieldInt(tfXP));
        item.setName(tfName.getText());
        if (rbSustenance.isSelected())
            item.setType(Item.itemType.SUSTENANCE);
        else if (rbKey.isSelected())
            item.setType(Item.itemType.KEY);
        else 
            item.setType(Item.itemType.EQUIPMENT);
    }

    @Override
    public void reset() {
        item = new Item();
        lblImage = new JLabel();
        tfName.setText(item.getName());
        tfHP.setText(Integer.toString(item.getIncreaseHP()));
        tfSP.setText(Integer.toString(item.getIncreaseSP()));
        tfXP.setText(Integer.toString(item.getIncreaseXP()));
        rbKey.setSelected(true);
        
    }

    @Override
    public void setObject(Object o) {
        item = (Item)o;
        tfName.setText(item.getName());
        tfHP.setText(Integer.toString(item.getIncreaseHP()));
        tfSP.setText(Integer.toString(item.getIncreaseSP()));
        tfXP.setText(Integer.toString(item.getIncreaseXP()));
        
        switch (item.getType())
        {
            case KEY:
                rbKey.setSelected(true);
                break;
            case EQUIPMENT:
                rbEquipment.setSelected(true);
                break;
            case SUSTENANCE:
                rbSustenance.setSelected(true);
                break;
        }
    }

    @Override
    public Object getObject() {
        return item;
    }

    @Override
    public boolean hasInvalidInput() {
        return invalidInput;
    }
    
    public int getFieldInt(JTextField tf)
    {
        String input = tf.getText();
        try {
            int x = Integer.parseInt(input);
            tf.setForeground(Color.BLACK);
            return x;
        }
        catch(NumberFormatException nFE) {
            tf.setForeground(Color.red);
            invalidInput = true;
            return -1;
        }
    }
    
    private class ItemListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnChangeImg) {
                String dir = System.getProperty("user.dir");
                JFileChooser fcImage = new JFileChooser(dir+"/Resources/Sprites");
                int returnVal = fcImage.showOpenDialog(iItem.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fcImage.getSelectedFile();
                    //This is where a real application would open the file.
                    imagePath = file.getPath();
                    BufferedImage myPicture;
                    try {
                        myPicture = ImageIO.read(new File(imagePath));
                        pWest.remove(lblImage);
                        lblImage = new JLabel(new ImageIcon( myPicture ));
                        pWest.add(lblImage, BorderLayout.NORTH);
                        //image.setPreferredSize(new Dimension(10,10));
                        //pImage.add(image,BorderLayout.NORTH);
                        
                        //iItem.this.pack();
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


