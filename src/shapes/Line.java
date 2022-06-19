package shapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Line implements Serializable {

    public Float[] dp1;
    public Float[] dp2;
    public Float[] p1;
    public Float[] p2;
    public boolean exists;
    public Color renderColor;
    int resolution[];

    public Line(Float[] dp1, Float[] dp2, Color renderColor, int[] resolution){
        this.dp1 = dp1;
        this.dp2 = dp2;
        this.resolution = resolution;
        this.renderColor = renderColor;
        this.p1 = new Float[]{null, null};
        this.p2 = new Float[]{null, null};
    }

    public void update(){
        if (dp1[0] == null || dp1[1] == null|| dp2[0] == null || dp2[1] == null){exists = false;}else{exists = true;}
        if(exists) {
            if (!(dp1[0].equals(dp2[0]) && dp1[1].equals(dp2[1]))) {
                float x1 = 0;
                float x2 = resolution[0];
                float x3 = dp1[0];
                float x4 = dp2[0];

                float y1 = 0;
                float y2 = 0;
                float y3 = dp1[1];
                float y4 = dp2[1];

                float d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

                if (d != 0) {
                    p1[0] = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
                    p1[1] = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

                    x1 = 0;
                    x2 = resolution[0];
                    x3 = dp1[0];
                    x4 = dp2[0];

                    y1 = resolution[1];
                    y2 = resolution[1];
                    y3 = dp1[1];
                    y4 = dp2[1];

                    d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

                    p2[0] = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
                    p2[1] = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
                } else {

                    p1[0] = 0f;
                    p1[1] = dp1[1];

                    p2[0] = (float) resolution[0];
                    p2[1] = dp1[1];


                }
            }else{p1[0] = null; p1[1] = null; p2[0] = null; p2[1] = null;}
        }
        else{p1[0] = null; p2[0] = null; p1[1] = null; p2[1] = null; exists = false;}
    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (p1[0] == null || p1[1] == null|| p2[0] == null || p2[1] == null){exists = false;}else{exists = true;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            g2d.drawLine(Math.round(p1[0]), Math.round(p1[1]), Math.round(p2[0]), Math.round(p2[1]));
            g2d.dispose();
        }

        return image;
    }

}
