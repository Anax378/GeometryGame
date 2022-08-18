package levelParts;

import Game.Main;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Mover implements Serializable {

    public Float[] position;
    public Color renderColor;
    public int diameter;
    public boolean exists;
    public boolean isHeld;

    public Mover(Float[] position, Color renderColor, int diameter){
        this.position = position;
        this.renderColor = renderColor;
        this.diameter = diameter;

    }

    public void update(){

        java.awt.Point mousePosition = Game.Main.w.frame.getMousePosition();
        if (mousePosition != null){

            mousePosition.y = mousePosition.y - 36;
            mousePosition.x = mousePosition.x - 5;

            float a = ((mousePosition.x - Main.currentLevel.off[0]) - position[0]);
            float b = ((mousePosition.y - Main.currentLevel.off[1]) - position[1]);

            float dist = (float) Math.sqrt((a*a + b*b));
            if((dist <= diameter/2f) & Main.w.mouseDown){isHeld = true;}
            if(!Main.w.mouseDown){isHeld = false;}
            if(isHeld){
                position[0] = (float) mousePosition.x - Main.currentLevel.off[0];
                position[1] = (float) mousePosition.y - Main.currentLevel.off[1];
            }

        }



    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (position[0] == null || position[1] == null){exists = false;}else{exists = true;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle = new Ellipse2D.Double((position[0] + Main.currentLevel.off[0]) - (diameter / 2f), (position[1] + Main.currentLevel.off[1]) - (diameter / 2f), diameter, diameter);
            g2d.fill(circle);
        }

        return image;

    }

}
