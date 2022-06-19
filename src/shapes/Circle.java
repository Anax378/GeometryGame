package shapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Circle implements Serializable {
    public Float[] center;
    public Float[] diameterPoint;
    public Float diameter;
    public Color renderColor;

    public boolean exists;


    public Circle(Float[] center, Float[] diameterPoint, Color renderColor){

        this.center = center;
        this.diameterPoint = diameterPoint;
        this.renderColor = renderColor;
    }

    public void update(){
        if (center[0] == null || center[1] == null || diameterPoint[0] == null || diameterPoint[1] == null){exists = false;}else{exists = true;}
        if (exists) {
            float a = Math.abs(diameterPoint[1] - center[1]);
            float b = Math.abs(diameterPoint[0] - center[0]);
            diameter = (float) Math.round(Math.sqrt(a * a + b * b)) * 2;
        }else {diameter = null;}
    }

    public BufferedImage renderOnImage(BufferedImage image){

        if (center[0] == null || center[1] == null || diameter == null){exists = false;}else{exists = true;}
        if (exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);

            //g2d.setStroke(new BasicStroke(1));

            g2d.drawOval( Math.round(center[0]) - Math.round((diameter / 2)), Math.round(center[1]) - Math.round((diameter / 2)), Math.round(diameter), Math.round(diameter));
            g2d.dispose();
        }
        return image;

    }

}
