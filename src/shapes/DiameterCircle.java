package shapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class DiameterCircle implements Serializable {

    public Float[] center;
    public float diameter;
    public Color renderColor;
    public boolean exists;

    public DiameterCircle(Float[] center,float diameter, Color renderColor){

        this.center = center;
        this.diameter = diameter;
        this.renderColor = renderColor;

    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (center[0] == null || center[1] == null){exists = false;}else{exists = true;}

        if (exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            g2d.drawOval(Math.round(center[0])  - Math.round((diameter / 2)), Math.round(center[1]) - Math.round((diameter / 2)), Math.round(diameter), Math.round(diameter));
            g2d.dispose();
        }

        return image;

    }

}
