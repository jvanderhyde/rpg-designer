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
    
    private boolean isBlockedRight;
    private boolean isBlockedLeft;
    private boolean isBlockedTop;
    private boolean isBlockedBottom;
    private float locX;
    private float locY;
    private int tileNum;
    
    public Block() {
        
    }
    
    public Block(boolean b, float x, float y, int tile) {
        isBlockedRight = b;
        isBlockedLeft = b;
        isBlockedTop = b;
        isBlockedBottom = b;
        locX = x;
        locY = y;
        tileNum = tile;
    }
    
    public float getLocX() {
        return this.locX;
    }
    
    public float getLocY() {
        return this.locY;
    }
    
    public void setLocation(float x, float y) {
        this.locX = x;
        this.locY = y;
    }
    
    public int getTile() {
        return tileNum;
    }

    public void setTile(int tileNumber) {
        tileNum=tileNumber;
    }
    
    public boolean getIsBlocked() {
        if(isBlockedRight && isBlockedLeft && isBlockedTop && isBlockedBottom)
            return true;
        return false;
    }
    
    public boolean getIsUnblocked() {
        if(isBlockedRight || isBlockedLeft || isBlockedTop || isBlockedBottom)
            return false;
        return true;
    }
    
    public boolean getIsBlockedRight() {
        return this.isBlockedRight;
    }
    
    public boolean getIsBlockedLeft() {
        return this.isBlockedLeft;
    }
    
    public boolean getIsBlockedTop() {
        return this.isBlockedTop;
    }
    
    public boolean getIsBlockedBottom() {
        return this.isBlockedBottom;
    }
    
    public void setIsBlocked(boolean b) {
        isBlockedRight = b;
        isBlockedLeft = b;
        isBlockedTop = b;
        isBlockedBottom = b;
    }
    
    public void setIsBlockedRight(boolean b) {
        isBlockedRight = b;
    }
    
    public void setIsBlockedLeft(boolean b) {
        isBlockedLeft = b;
    }
    
    public void setIsBlockedTop(boolean b) {
        isBlockedTop = b;
    }
    
    public void setIsBlockedBottom(boolean b) {
        isBlockedBottom = b;
    }
    
    public void toggleBlock() {
        if(isBlockedRight && isBlockedLeft && isBlockedTop && isBlockedBottom) {
            isBlockedTop = false;
            isBlockedBottom = false;
        } else if(isBlockedRight && isBlockedLeft) {
            isBlockedRight = false;
            isBlockedLeft = false;
            isBlockedTop = true;
            isBlockedBottom = true;
        } else if (isBlockedTop && isBlockedBottom) {
            isBlockedLeft = true;
            isBlockedTop = false;
            isBlockedBottom = false;
        } else if (isBlockedLeft) {
            isBlockedTop = true;
            isBlockedLeft = false;
        } else if(isBlockedTop) {
            isBlockedRight = true;
            isBlockedTop = false;
        } else if(isBlockedRight) {
            isBlockedBottom = true;
            isBlockedRight = false;
        } else if(isBlockedBottom) {
            setIsBlocked(true);
        }
    }
    
    public BufferedImage getBlockImage() {
        if(getIsBlocked()) {
            BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("B", 0, 22);
            graphics.drawString("B", 25, 22);
            graphics.drawString("B", 12, 6);
            graphics.drawString("B", 12, 36);
            return image;
        } else if(isBlockedRight && isBlockedLeft) {
            BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("B", 0, 22);
            graphics.drawString("B", 25, 22);
            return image;
        } else if(isBlockedTop && isBlockedBottom) {
            BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("B", 12, 6);
            graphics.drawString("B", 12, 36);
            return image;
        } else if(isBlockedLeft) {
            BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("B", 0, 22);
            return image;
        } else if(isBlockedRight) {
            BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("B", 25, 22);
            return image;
        } else if(isBlockedTop) {
            BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("B", 12, 6);
            return image;
        } else if(isBlockedBottom) {
            BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.drawString("B", 12, 36);
            return image;
        }
            return new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
    }
    
}
