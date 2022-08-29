package shapes;

import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class LineSegment implements Serializable {

    public Float[] p1;
    public Float[] p2;
    public Color renderColor;
    public boolean exists;

    public LineSegment(Float[] p1, Float[] p2, Color renderColor) {
        this.p1 = p1;
        this.p2 = p2;
        this.renderColor = renderColor;

    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (p1[0] == null || p1[1] == null|| p2[0] == null || p2[1] == null){exists = false;}else{exists = true;}
        if(p1.equals(p2)){exists = false;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            g2d.drawLine(Math.round(Main.currentLevel.toRenderCoords(p1)[0]), Math.round(Main.currentLevel.toRenderCoords(p1)[1]), Math.round(Main.currentLevel.toRenderCoords(p2)[0]), Math.round(Main.currentLevel.toRenderCoords(p2)[1]));
            g2d.dispose();
        }

        return image;
    }

}
