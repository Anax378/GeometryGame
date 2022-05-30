package shapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Circle implements Serializable {
    public Integer[] center;
    public Integer[] diameterPoint;
    public Integer diameter;
    public Color renderColor;

    public boolean exists;


    public Circle(Integer[] center, Integer[] diameterPoint, Color renderColor){

        this.center = center;
        this.diameterPoint = diameterPoint;
        this.renderColor = renderColor;
    }

    public void update(){
        if (center[0] == null || center[1] == null || diameterPoint[0] == null || diameterPoint[1] == null){exists = false;}else{exists = true;}
        if (exists) {
            int a = Math.abs(diameterPoint[1] - center[1]);
            int b = Math.abs(diameterPoint[0] - center[0]);
            diameter = (int) Math.round(Math.sqrt(a * a + b * b)) * 2;
        }else diameter = null;
    }

    public BufferedImage renderOnImage(BufferedImage image){

        if (center[0] == null || center[1] == null || diameter == null){exists = false;}else{exists = true;}
        if (exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);

            //g2d.setStroke(new BasicStroke(1));

            g2d.drawOval(center[0] - (diameter / 2), center[1] - (diameter / 2), diameter, diameter);
            g2d.dispose();
        }
        return image;

    }

}
