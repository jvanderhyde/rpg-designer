package rpgdesigner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author james
 * This class extends JPanel to create a panel in the editor that displays the tileset you
 * have currently selected in the editor
 */
public class TilesetPanel extends JPanel{
    private BufferedImage tileset;
    private BufferedImage gridTileSets;
    private BufferedImage selecter;
    private int selecterX;
    private int selecterY;
    
    public TilesetPanel(BufferedImage tileset) {
        this.tileset = tileset;
        gridTileSets = new BufferedImage(tileset.getWidth(),tileset.getHeight(),BufferedImage.TRANSLUCENT);
        Graphics2D gridToDraw2 = gridTileSets.createGraphics();
        gridToDraw2.setColor(Color.red);
        for (int x=0; x<=tileset.getWidth(); x+=32) {
            gridToDraw2.drawLine(x, 0, x, tileset.getHeight());
        }
        for (int y=0; y<=tileset.getHeight(); y+=32) {
            gridToDraw2.drawLine(0, y, tileset.getWidth(), y);
        }
        this.setPreferredSize(new Dimension(tileset.getWidth(), tileset.getHeight()));
        selecter = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
        Graphics2D tileSelecter = selecter.createGraphics();
        tileSelecter.setPaint(Color.blue);
        tileSelecter.drawRect(0, 0, 31, 31);
        tileSelecter.drawRect(1,1,29,29);
        selecterX = 0;
        selecterY = 0;
    }
    
    public void setSelecter(int x, int y) {
        selecterX = x;
        selecterY = y;
    }
    
    public void setTileset(BufferedImage tileset) {
        this.tileset = tileset;
        gridTileSets = new BufferedImage(tileset.getWidth(),tileset.getHeight(),BufferedImage.TRANSLUCENT);
        Graphics2D gridToDraw2 = gridTileSets.createGraphics();
        gridToDraw2.setColor(Color.red);
        for (int x=0; x<=tileset.getWidth(); x+=32) {
            gridToDraw2.drawLine(x, 0, x, tileset.getHeight());
        }
        for (int y=0; y<=tileset.getHeight(); y+=32) {
            gridToDraw2.drawLine(0, y, tileset.getWidth(), y);
        }
        this.setPreferredSize(new Dimension(tileset.getWidth(), tileset.getHeight()));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(tileset, 0, 0, null);
        g.drawImage(gridTileSets, -1, -1, null);
        g.drawImage(selecter, selecterX-1, selecterY-1, null);
    }
}
