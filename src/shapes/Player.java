package shapes;

import Game.Main;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Player implements Serializable {


    public Float[] physicsPosition;
    public Float[] velocity;
    public Float[] acceleration = new Float[]{0f, 2000f};

    int lastClickedCount;

    public Color renderColor;
    public Integer[] position;
    public int diameter;
    public int horizontalInput = 0;
    public boolean mouseInHitbox = false;
    public boolean exists;
    public int inputImpact = 1;


    public Player(Integer[] position, Color renderColor, int diameter){
        this.position = position;
        this.physicsPosition = new Float[]{position[0].floatValue(), position[1].floatValue()};
        this.renderColor = renderColor;
        this.diameter = diameter;
        this.velocity = new Float[]{0f, 0f};

    }

    public void update(){
        horizontalInput = 0;

        if(Main.w.isDDown){horizontalInput++;}
        if(Main.w.isADown){horizontalInput--;}
        float t = 1f/Game.Main.tps;



        velocity[0] = velocity[0] * 0.9f;
        velocity[1] = velocity[1] * 0.988f;

        physicsPosition[0] = physicsPosition[0]+velocity[0]*t;
        physicsPosition[1] = physicsPosition[1]+velocity[1]*t;


        velocity[0] = velocity[0] + acceleration[0]*t;
        velocity[1] = velocity[1] + acceleration[1]*t;

        if(physicsPosition[0] < (float) diameter/2f){physicsPosition[0] = (float) diameter/2f; velocity[0] = 0f;}
        if(physicsPosition[0] > (float) Main.w.width - (float) diameter/2f){physicsPosition[0] = (float) Main.w.width - diameter/2f;velocity[0] = 0f;}
        if(physicsPosition[1] < (float) diameter/2f){physicsPosition[1] = (float) diameter/2f;velocity[1] = 0f;}
        if(physicsPosition[1] > (float) Main.w.height - (float) diameter/2f){physicsPosition[1] = (float) Main.w.height - diameter/2f;velocity[1] = 0f;}

        if(Main.w.mouseDown & lastClickedCount != Main.w.clickCount){velocity[1] = velocity[1] - 1000f;Main.w.isSpaceDown = false;}

        velocity[0] = velocity[0] - 500f * -horizontalInput;

        float speedLimit = 500f;

        if(velocity[0] > speedLimit){velocity[0] = speedLimit;}
        if(velocity[0] < -speedLimit){velocity[0] = -speedLimit;}




        position[0] = Math.round(physicsPosition[0]);
        position[1] = Math.round(physicsPosition[1]);

        lastClickedCount = Main.w.clickCount;


/*
        java.awt.Point mousePosition = Game.Main.w.frame.getMousePosition();
        if (mousePosition != null) {

            mousePosition.y = mousePosition.y - 36;
            int a = Math.abs(mousePosition.y - position[1]);
            int b = Math.abs(mousePosition.x - position[0]);

            boolean inHitbox = Math.sqrt(a * a + b * b) < diameter;

            if (!Game.Main.w.mouseDown) {
                mouseInHitbox = inHitbox;
            }

            if (mouseInHitbox & Game.Main.w.mouseDown) {

                position[0] = mousePosition.x;
                position[1] = mousePosition.y;
            }
        }
*/
    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (position[0] == null || position[1] == null){exists = false;}else{exists = true;}
        if(exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle = new Ellipse2D.Double(position[0] - (diameter / 2), position[1] - (diameter / 2), diameter, diameter);
            g2d.fill(circle);
        }

        return image;

    }

}

