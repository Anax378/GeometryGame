package levelParts;

import Game.Main;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Orb implements Serializable {

    public Float[] position;
    public Color renderColor;
    public int diameter;
    public boolean exists;

    public Orb(Float[] position, Color renderColor, int diameter){
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
            g2d.drawOval( Math.round(Main.currentLevel.toRenderCoords(position)[0]) - Math.round((diameter / 2f)), Math.round(Main.currentLevel.toRenderCoords(position)[1]) - Math.round((diameter / 2f)), Math.round(diameter), Math.round(diameter));

            g2d.setPaint(new Color(renderColor.getRed(), renderColor.getGreen(), renderColor.getBlue(), 10));
            g2d.fill(circle);
        }

        return image;

    }

    public boolean isInCircle(Float[] pos){
        if(pos[0] == null || pos[1] == null || position[1] == null || position[0] == null){return  false;}
        float a = position[0] - pos[0];
        float b = position[1] - pos[1];
        if(Math.sqrt(a*a + b*b) <= diameter/2f){return true;}else{return false;}

    }

}
