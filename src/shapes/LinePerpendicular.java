package shapes;

import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class LinePerpendicular implements Serializable {
    public Float[] lp1;
    public Float[] lp2;

    public Float[] p1;
    public Float[] p2 = new Float[]{null, null};

    public Float[] renderP1 = new Float[]{null, null};
    public Float[] renderP2 = new Float[]{null, null};

    public int[] resolution;
    public Color renderColor;
    public boolean exists;

    public LinePerpendicular(Float[] lp1, Float[] lp2, Float[] p1, Color renderColor, int[] resolution){
        this.lp1 = lp1;
        this.lp2 = lp2;

        this.resolution = new int[]{resolution[0]*1000000, resolution[1]*1000000};
        this.renderColor = renderColor;
        this.p1 = p1;
    }

    public void update(){
        if(lp1[0] == null | lp1[1] == null | lp2[0] == null | lp2[1] == null | p1[0] == null | p1[1] == null){exists = false;}else{exists = true;}
        if (exists){
            float angleInRadians = (float) Math.toRadians(90);

            float vX = lp2[0] - lp1[0];
            float vY = lp2[1] - lp1[1];

            Float[] vrlp2 = new Float[]{(float) (vX*Math.cos(angleInRadians) - vY*Math.sin(angleInRadians)), (float) (vY*Math.cos(angleInRadians)+ vX*Math.sin(angleInRadians))};

            Float[] rlp2 = new Float[]{vrlp2[0] + lp1[0], vrlp2[1] + lp1[1]};

            Float[] vector = new Float[]{p1[0] - rlp2[0], p1[1] - rlp2[1]};

            p2[0] = lp1[0] + vector[0];
            p2[1] = lp1[1] + vector[1];

        }

        if (p1[0] == null | p1[1] == null | p2[0] == null | p2[1] == null){exists = false;}else{exists = true;}
        if(exists) {

            if (!(p1[0].equals(p2[0]) && p1[1].equals(p2[1]))) {
                float x1 = 0;
                float x2 = resolution[0];
                float x3 = p1[0];
                float x4 = p2[0];

                float y1 = 0;
                float y2 = 0;
                float y3 = p1[1];
                float y4 = p2[1];

                float d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

                if (d != 0) {
                    renderP1[0] = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
                    renderP1[1] = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

                    x1 = 0;
                    x2 = resolution[0];
                    x3 = p1[0];
                    x4 = p2[0];

                    y1 = resolution[1];
                    y2 = resolution[1];
                    y3 = p1[1];
                    y4 = p2[1];

                    d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

                    renderP2[0] = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
                    renderP2[1] = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
                } else {

                    renderP1[0] = 0f;
                    renderP1[1] = p1[1];

                    renderP2[0] = (float) resolution[0];
                    renderP2[1] = p1[1];


                }
            }else{
                renderP1[0] = null; renderP1[1] = null; renderP2[0] = null; renderP2[1] = null;}
        }
        else{
            renderP1[0] = null; renderP2[0] = null; renderP1[1] = null; renderP2[1] = null; exists = false;}
    }


    public BufferedImage renderOnImage(BufferedImage image){
        if (renderP1[0] == null || renderP1[1] == null|| renderP2[0] == null || renderP2[1] == null){exists = false;}else{exists = true;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            g2d.drawLine(Math.round(renderP1[0] + Main.currentLevel.off[0]), Math.round(renderP1[1] + Main.currentLevel.off[1]), Math.round(renderP2[0] + Main.currentLevel.off[0]), Math.round(renderP2[1] + Main.currentLevel.off[1]));
            g2d.dispose();
        }

        return image;
    }



}
