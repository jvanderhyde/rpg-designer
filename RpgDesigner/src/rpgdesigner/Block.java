package rpgdesigner;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author james
 * This class holds all information for a block.  Blocks can be placed onto maps
 * in order to block the player from crossing certain areas from a certain(or all)
 * directions.
 */
public class Block {
    
    //These are the booleans that keep track of which direction is blocked
    private boolean isBlockedRight;
    private boolean isBlockedLeft;
    private boolean isBlockedTop;
    private boolean isBlockedBottom;
    
    private float locX; //The x coordinate location of the block on the map
    private float locY; //The y coordinate location of the block on the map
    private int tileNum; //The number of the tile which is being blocked
    
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
    
    //Toggles this block to the different available direction blocks
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
    
    //Getter Methods for Block
    public float getLocX() {
        return this.locX;
    }
    
    public float getLocY() {
        return this.locY;
    }
    
    public int getTile() {
        return tileNum;
    }
    
    //Returns true if all sides are blocked
    public boolean getIsBlocked() {
        if(isBlockedRight && isBlockedLeft && isBlockedTop && isBlockedBottom)
            return true;
        return false;
    }
    
    //Returns true if no side is blocked
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
    
    //Determines and returns the image to use for this block on the map editor
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
    
    //Setter Methods for block
    public void setLocation(float x, float y) {
        this.locX = x;
        this.locY = y;
    }

    public void setTile(int tileNumber) {
        tileNum=tileNumber;
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
}
