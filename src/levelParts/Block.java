package levelParts;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block {
    public Integer[] p1;
    public Integer[] p2;
    public Integer[] p3;
    public Integer[] p4;

    public Color renderColor;
    public boolean exists;

    public Block(Integer[] p1, Integer[] p2, Integer[] p3, Integer[] p4, Color renderColor){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;

        this.renderColor = renderColor;
    }

    public BufferedImage renderOnImage (BufferedImage image){

        if(p1 == null | p2 == null | p3 == null | p3 == null) {exists = false;}else{exists = true;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            g2d.fillPolygon(new int[]{p1[0], p2[0], p3[0], p4[0]}, new int[]{p1[1], p2[1], p3[1], p4[1]}, 4);
            g2d.dispose();
        }

        return image;
    }
}
