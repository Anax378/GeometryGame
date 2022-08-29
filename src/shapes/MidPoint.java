package shapes;

import Game.Main;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class MidPoint implements Serializable {

    public Float[] p1;
    public Float[] p2;
    public int diameter;
    public Color renderColor;
    public Float[] position;
    public boolean exists;

    public MidPoint(Float[] p1, Float[] p2, Color renderColor, int diameter){
        this.p1 = p1;
        this.p2 = p2;
        this.diameter = diameter;
        this.renderColor = renderColor;
        this.position = new Float[]{null, null};
    }

    public void update(){
        if (p1[0] == null || p1[1] == null|| p2[0] == null || p2[1] == null){exists = false;}else{exists = true;}
        if (exists) {
            position[0] = ((p1[0] + p2[0]) / 2);
            position[1] = ((p1[1] + p2[1]) / 2);
        }else {position[0] = null; position[1] = null;}
    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (position[0] == null || position[1] == null){exists = false;}else{exists = true;}
        if (exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);

            Ellipse2D.Double circle = new Ellipse2D.Double((Main.currentLevel.toRenderCoords(position)[0]) - (Main.currentLevel.toRenderLength((float) diameter) / 2f), (Main.currentLevel.toRenderCoords(position)[1]) - (Main.currentLevel.toRenderLength((float) diameter) / 2f), Main.currentLevel.toRenderLength((float) diameter), Main.currentLevel.toRenderLength((float) diameter));
            g2d.fill(circle);
        }
        return image;

    }

}
