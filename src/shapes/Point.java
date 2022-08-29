package shapes;

import Game.Main;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Point implements Serializable {

    public Float[] position;
    public Color renderColor;
    public int diameter;
    public boolean exists;

    public Point(Float[] position, Color renderColor, int diameter){
        this.position = position;
        this.renderColor = renderColor;
        this.diameter = diameter;

    }
    public BufferedImage renderOnImage(BufferedImage image){
        if (position[0] == null || position[1] == null){exists = false;}else{exists = true;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle = new Ellipse2D.Double((Main.currentLevel.toRenderCoords(position)[0]) - (diameter / 2f), (Main.currentLevel.toRenderCoords(position)[1]) - (diameter / 2f), diameter, diameter);
            g2d.fill(circle);
        }

        return image;

    }

}
