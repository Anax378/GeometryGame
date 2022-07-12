package shapes;

import Game.Main;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class LineXLineSegmentCrossection {
    public Float[] p1;
    public Float[] p2;
    public Float[] p3;
    public Float[] p4;

    public Color renderColor;
    public int renderRadius;

    public boolean exists;

    Float[] position;

    public LineXLineSegmentCrossection(Float[] p1, Float[] p2, Float[] p3, Float[] p4, Color renderColor, int renderRadius){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.renderColor = renderColor;
        this.renderRadius = renderRadius;
        this.position = new Float[]{null, null};

    }

    public void update(){
        if(p1[0] == null | p1[1] == null & p2[0] == null | p2[1] == null | p3[0] == null | p3[1] == null | p4[0] == null | p4[1] == null){exists = false;}else{exists = true;}
        if(exists) {
            float x1 = p1[0];
            float x2 = p2[0];
            float x3 = p3[0];
            float x4 = p4[0];

            float y1 = p1[1];
            float y2 = p2[1];
            float y3 = p3[1];
            float y4 = p4[1];

            float d = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);

            if (d == 0){position[0] = null; position[1] = null;}else{
                float x = ((x1*y2 - y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4))/d;
                float y = ((x1*y2 - y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4))/d;

                float cornerXPlus;
                float cornerXMinus;

                float cornerYPlus;
                float cornerYMinus;

                if(p3[0] < p4[0]){cornerXPlus = p4[0];cornerXMinus = p3[0];}else{cornerXPlus = p3[0];cornerXMinus = p4[0];}
                if(p3[1] < p4[1]){cornerYPlus = p4[1];cornerYMinus = p3[1];}else{cornerYPlus = p3[1];cornerYMinus = p4[1];}

                if(cornerXMinus-0.1f <= x & x <= cornerXPlus+0.1f & cornerYMinus-0.1f <= y & y <= cornerYPlus+0.1f ){position[0] = x; position[1] = y;}else{position[0] = null; position[1] = null;}



            }

        }


    }


    public BufferedImage renderOnImage(BufferedImage image){

        if (position[0] == null || position[1] == null){exists = false;}else{exists = true;}
        if(exists) {

            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle = new Ellipse2D.Double((position[0] + Main.currentLevel.off[0]) - (renderRadius / 2f), (position[1] + Main.currentLevel.off[1]) - (renderRadius / 2f), renderRadius, renderRadius);
            g2d.fill(circle);
        }

        return image;
    }

}
