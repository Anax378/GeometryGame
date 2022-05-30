package shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class LineSegmentXLineSegmentCrossection {
    public Integer[] p1;
    public Integer[] p2;
    public Integer[] p3;
    public Integer[] p4;

    public Color renderColor;
    public int renderRadius;

    public boolean exists;

    Integer[] position;

    public LineSegmentXLineSegmentCrossection(Integer[] p1, Integer[] p2, Integer[] p3, Integer[] p4, Color renderColor, int renderRadius){
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
                int x = ((x1*y2 - y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4))/d;
                int y = ((x1*y2 - y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4))/d;

                int cornerXPlus;
                int cornerXMinus;

                int cornerYPlus;
                int cornerYMinus;

                int cornerXPlus2;
                int cornerXMinus2;

                int cornerYPlus2;
                int cornerYMinus2;

                if(p3[0] < p4[0]){cornerXPlus = p4[0];cornerXMinus = p3[0];}else{cornerXPlus = p3[0];cornerXMinus = p4[0];}
                if(p3[1] < p4[1]){cornerYPlus = p4[1];cornerYMinus = p3[1];}else{cornerYPlus = p3[1];cornerYMinus = p4[1];}

                if(p1[0] < p2[0]){cornerXPlus2 = p2[0];cornerXMinus2 = p1[0];}else{cornerXPlus2 = p1[0];cornerXMinus2 = p2[0];}
                if(p1[1] < p2[1]){cornerYPlus2 = p2[1];cornerYMinus2 = p1[1];}else{cornerYPlus2 = p1[1];cornerYMinus2 = p2[1];}

                if(cornerXMinus <= x & x <= cornerXPlus & cornerYMinus <= y & y <= cornerYPlus & cornerXMinus2 <= x & x <= cornerXPlus2 & cornerYMinus2 <= y & y <= cornerYPlus2)
                {position[0] = x; position[1] = y;}
                else{position[0] = null; position[1] = null;}



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
