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

            Float[] onScreenPos = Main.currentLevel.toRenderCoords(position);

            float a = (mousePosition.x - onScreenPos[0]);
            float b = (mousePosition.y - onScreenPos[1]);

            float dist = (float) Math.sqrt((a*a + b*b));
            if((dist <= diameter/2f) & Main.w.mouseDown){isHeld = true;}
            if(!Main.w.mouseDown){isHeld = false;}
            if(isHeld) {
                position[0] = Main.currentLevel.toFieldCoords(new Float[]{(float) mousePosition.x, (float) mousePosition.y})[0];
                position[1] = Main.currentLevel.toFieldCoords(new Float[]{(float) mousePosition.x, (float) mousePosition.y})[1] + 300f;
            }

        }



    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (position[0] == null || position[1] == null){exists = false;}else{exists = true;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle = new Ellipse2D.Double((Main.currentLevel.toRenderCoords(position)[0]) - (Main.currentLevel.toRenderLength((float) diameter) / 2f), (Main.currentLevel.toRenderCoords(position)[1]) - (Main.currentLevel.toRenderLength((float) diameter) / 2f), Main.currentLevel.toRenderLength((float) diameter), Main.currentLevel.toRenderLength((float) diameter));
            g2d.fill(circle);
        }

        return image;

    }

}
