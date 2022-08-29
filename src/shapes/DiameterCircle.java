package shapes;

import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class DiameterCircle implements Serializable {

    public Float[] center;
    public Float[] diameter;
    public Color renderColor;
    public boolean exists;

    public DiameterCircle(Float[] center,Float[] diameter, Color renderColor){

        this.center = center;
        this.diameter = diameter;
        this.renderColor = renderColor;

    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (center[0] == null || center[1] == null){exists = false;}else{exists = true;}

        if (exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            g2d.drawOval(Math.round(Main.currentLevel.toRenderCoords(center)[0])  - Math.round((diameter[0] / 2)), Math.round(Main.currentLevel.toRenderCoords(center)[1]) - Math.round((diameter[0] / 2)), Math.round(diameter[0]), Math.round(diameter[0]));
            g2d.dispose();
        }

        return image;

    }

}
