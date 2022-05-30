package shapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class LineSegment implements Serializable {

    public Integer[] p1;
    public Integer[] p2;
    public Color renderColor;
    public boolean exists;

    public LineSegment(Integer[] p1, Integer[] p2, Color renderColor) {
        this.p1 = p1;
        this.p2 = p2;
        this.renderColor = renderColor;

    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (p1[0] == null || p1[1] == null|| p2[0] == null || p2[1] == null){exists = false;}else{exists = true;}
        if(p1.equals(p2)){exists = false;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            g2d.drawLine(p1[0], p1[1], p2[0], p2[1]);
            g2d.dispose();
        }

        return image;
    }

}
