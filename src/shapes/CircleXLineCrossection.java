package shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class CircleXLineCrossection {
    public Integer[] position1 = new Integer[2];
    public Integer[] position2 = new Integer[2];

    public Integer[] p1;
    public Integer[] p2;
    public Integer[] centre;
    public Integer circleDiameter;

    public int diameter;

    public Color renderColor;

    public boolean exists1;
    public boolean exists2;
    public boolean exists;

    public CircleXLineCrossection (Integer[] p1, Integer[] p2, Integer[] centre, Integer circleDiameter,int diameter, Color renderColor){
        this.p1 = p1;
        this.p2 = p2;
        this.centre = centre;
        this.circleDiameter = circleDiameter;
        this.renderColor = renderColor;
        this.diameter = diameter;
    }
    public void update(){
        if(p1[0] == null | p1[1] == null | p2[0] == null | p2[1] == null | circleDiameter == null | centre[0] == null | centre[1] == null){exists = false;}else{exists = true;}
        if (exists){
            float x1 = p1[0];
            float x2 = p2[0];
            float y1 = p1[1];
            float y2 = p2[1];

            float dx = x2 - x1;
            float dy = y2 - y1;
            float dr = (float) Math.sqrt(dx*dx + dy*dy);
            float d = x1*y2-x2*y1;



            float discriminant = ((circleDiameter.floatValue()/2f)*(circleDiameter.floatValue()/2f) * (dr*dr)) - (d*d);

            System.out.println(String.valueOf(discriminant));

            if(discriminant < 0f){exists = false; exists1 = false; exists2 = false; position1[0] = null; position1[1] = null; position2[0] = null; position2[1] = null;}
            if(discriminant == 0f){
                position1[0] = (int) Math.round((d*dy+ sgn(dy)*dx*Math.sqrt(discriminant))/(dr*dr)) + centre[0];
                position1[1] = (int) Math.round((-d*dx+Math.abs(dy)*Math.sqrt(discriminant))/(dr*dr)) + centre[1];
                exists2 = false;
                exists1 = true;
            }
            if (discriminant > 0f){
                position1[0] = (int) Math.round((d*dy+ sgn(dy)*dx*Math.sqrt(discriminant))/(dr*dr)) + centre[0];
                position1[1] = (int) Math.round((-d*dx+Math.abs(dy)*Math.sqrt(discriminant))/(dr*dr)) + centre[1];
                position2[0] = (int) Math.round((d*dy- sgn(dy)*dx*Math.sqrt(discriminant))/(dr*dr)) + centre[0];
                position2[1] = (int) Math.round((-d*dx-Math.abs(dy)*Math.sqrt(discriminant))/(dr*dr)) + centre[1];
                exists1 = true;
                exists2 = true;

            }
        }
    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (p1[0] == null | p1[1] == null | p2[0] == null | p2[1] == null){exists1 = false;}else{exists = true;}
        if(exists1 & exists) {
            System.out.println(position1[0] + " " + position1[1]);
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
