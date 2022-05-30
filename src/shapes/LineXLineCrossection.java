package shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class LineXLineCrossection {
    public Integer[] p1;
    public Integer[] p2;
    public Integer[] p3;
    public Integer[] p4;

    public Color renderColor;
    public int renderRadius;

    public boolean exists;

    Integer[] position;

    public LineXLineCrossection(Integer[] p1, Integer[] p2, Integer[] p3, Integer[] p4, Color renderColor, int renderRadius){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.renderColor = renderColor;
        this.renderRadius = renderRadius;
        this.position = new Integer[]{null, null};

    }

    public void update(){
        if (p1[0] == null & p1[1] == null & p2[0] == null & p2[1] == null& p3[0] == null & p3[1] == null & p4[0] == null & p4[1] == null) {exists = false;}else{exists = true;}
        if(exists) {
            int x1 = p1[0];
            int x2 = p2[0];
            int x3 = p3[0];
            int x4 = p4[0];

            int y1 = p1[1];
            int y2 = p2[1];
            int y3 = p3[1];
            int y4 = p4[1];

            int d = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);

            if (d == 0){position[0] = null; position[1] = null;}else{
                position[0] = ((x1*y2 - y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4))/d;
                position[1] = ((x1*y2 - y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4))/d;


            }

        }


    }


    public BufferedImage renderOnImage(BufferedImage image){
        if (position[0] == null || position[1] == null){exists = false;}else{exists = true;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle = new Ellipse2D.Double(position[0] - (renderRadius / 2), position[1] - (renderRadius / 2), renderRadius, renderRadius);
            g2d.fill(circle);
        }

        return image;
    }

}
