/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgdesigner;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author james
 */
public class Block {
    
    private boolean isBlocked;
    
    public Block(boolean b) {
        isBlocked = b;
    }
    
    public boolean getIsBlocked() {
        return this.isBlocked;
    }
    
    public void setIsBlocked(boolean b) {
        isBlocked = b;
    }
    
    public BufferedImage getBlockImage() {
        if(isBlocked) {
            BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("B", 12, 22);
            return image;
        } else 
            return new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
    }
    
}
