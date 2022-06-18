package shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class CircleXLineCrossection {
    public Integer[] position1;
    public Integer[] position2;

    public Integer[] p1;
    public Integer[] p2;
    public Integer[] centre;
    public Integer diameter;

    public Color renderColor;

    public boolean exists1;
    public boolean exists2;
    public boolean exists;

    public CircleXLineCrossection (Integer[] p1, Integer[] p2, Integer[] centre, Integer diameter, Color renderColor){
        this.p1 = p1;
        this.p2 = p2;
        this.centre = centre;
        this.diameter = diameter;
        this.renderColor = renderColor;
    }
    public void update(){
        if(p1[0] == null | p1[1] == null | p2[0] == null | p2[1] == null | diameter == null | centre[0] == null | centre[1] == null){exists = false;}else{exists = true;}
        if (exists){
            float x1 = p1[0];
            float x2 = p2[0];
            float y1 = p1[1];
            float y2 = p2[1];

            float dx = x2 - x1;
            float dy = y2 - y1;
            float dr = (float) Math.sqrt(dx*dx + dy*dy);
            float d = x1*y2-x2*y1;

            float discriminant = diameter.floatValue()*diameter.floatValue() * dr*dr - d*d;

            if(discriminant < 0){exists1 = false; exists2 = false; position1[0] = null; position1[1] = null; position2[0] = null; position2[1] = null;}
            if(discriminant == 0){
                position1[0] = (int) Math.round((d*dy+ sgn(dy)*dx*Math.sqrt(discriminant))/dr*dr);
                position1[1] = (int) Math.round((-d*dx+Math.abs(dy)*Math.sqrt(discriminant))/dr*dr);
                exists2 = false;
                exists1 = true;
            }
            if (discriminant > 0){
                position1[0] = (int) Math.round((d*dy+ sgn(dy)*dx*Math.sqrt(discriminant))/dr*dr);
                position1[1] = (int) Math.round((-d*dx+Math.abs(dy)*Math.sqrt(discriminant))/dr*dr);
                position2[0] = (int) Math.round((d*dy- sgn(dy)*dx*Math.sqrt(discriminant))/dr*dr);
                position2[1] = (int) Math.round((-d*dx-Math.abs(dy)*Math.sqrt(discriminant))/dr*dr);
                exists1 = true;
                exists2 = true;

            }
        }
    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (p1[0] == null || p1[1] == null){exists1 = false;}else{exists1 = true;}
        if(p2[0] == null | p2[1] == null){exists2 = false;}else{exists2 = true;}
        if(exists1 & exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle1 = new Ellipse2D.Double(position1[0] - (diameter / 2f), position1[1] - (diameter / 2f), diameter, diameter);
            g2d.fill(circle1);
        }
        if(exists2 & exists){
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle2 = new Ellipse2D.Double(position2[0] - (diameter / 2f), position2[1] - (diameter / 2f), diameter, diameter);
            g2d.fill(circle2);

        }

        return image;

    }
    public float sgn (float x){if (x<0){return -1f;}else{return 1f;}}
}
