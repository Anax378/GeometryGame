package shapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class DiameterCircle implements Serializable {

    public Integer[] center;
    public int diameter;
    public Color renderColor;
    public boolean exists;

    public DiameterCircle(Integer[] center,int diameter, Color renderColor){

        this.center = center;
        this.diameter = diameter;
        this.renderColor = renderColor;

    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (center[0] == null || center[1] == null){exists = false;}else{exists = true;}

        if (exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            g2d.drawOval(center[0] - (diameter / 2), center[1] - (diameter / 2), diameter, diameter);
            g2d.dispose();
        }

        return image;

    }

}
