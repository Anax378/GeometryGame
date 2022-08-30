package levelParts;

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
    public Float[] position;
    public Float[] lastPhysicalPosition;

    public int diameter;
    public int horizontalInput = 0;
    public boolean mouseInHitbox = false;
    public boolean exists;
    public boolean canJump = false;
    public float friction = 0.9f;
    public float moveForce = 500f;
    public float groundMoveForce = 500f;
    public float airMoveForce = 0.6f * groundMoveForce;


    public Player(Float[] position, Color renderColor, int diameter){
        this.position = position;
        this.physicsPosition = new Float[]{position[0], position[1]};
        this.renderColor = renderColor;
        this.diameter = diameter;
        this.velocity = new Float[]{0f, 0f};
        this.lastPhysicalPosition = new Float[]{position[0], position[1]};

    }

    public void update() throws InterruptedException {
        if(position[0] == null | position[1] == null){exists = false;}else{exists = true;}
        if(exists){
            horizontalInput = 0;
            canJump = false;

            if(Main.w.isDDown){horizontalInput++;}
            if(Main.w.isADown){horizontalInput--;}

            float t = 1f/ Main.tps;

            velocity[0] = velocity[0] * friction;
            velocity[1] = velocity[1] * 0.988f;

            velocity[0] = velocity[0] + acceleration[0]*t;
            velocity[1] = velocity[1] + acceleration[1]*t;

            velocity[0] = velocity[0] - (moveForce * -horizontalInput);

            physicsPosition[0] = physicsPosition[0]+velocity[0]*t;
            physicsPosition[1] = physicsPosition[1]+velocity[1]*t;

            for(int i = 0; i < Main.currentLevel.blocks.size(); i++){
                boolean[] result = isInRectangle(new float[] {physicsPosition[0], physicsPosition[1]}, new float[]{Main.currentLevel.blocks.get(i).p1[0], Main.currentLevel.blocks.get(i).p1[1]}, new float[]{Main.currentLevel.blocks.get(i).p2[0], Main.currentLevel.blocks.get(i).p2[1]}, lastPhysicalPosition[1]);
                if(result[0]){
                    if(!result[1] && !result[2]){physicsPosition = new Float[]{physicsPosition[0], Main.currentLevel.blocks.get(i).p2[1]};}
                    if(!result[1] && result[2]){physicsPosition = new Float[]{physicsPosition[0], Main.currentLevel.blocks.get(i).p1[1]};canJump = true;}
                    if(result[1] && !result[2]){physicsPosition = new Float[]{Main.currentLevel.blocks.get(i).p2[0], physicsPosition[1]};}
                    if(result[1] && result[2]){physicsPosition = new Float[]{Main.currentLevel.blocks.get(i).p1[0], physicsPosition[1]};}

                    if(result[1]){velocity[0] = 0f;}
                    if(!result[1]){velocity[1] = 0f;}

                }
            }

            for(int i = 0; i < Main.currentLevel.orbs.size(); i++){
                if(Main.currentLevel.orbs.get(i).isInCircle(position)){canJump = true;}
            }

            if(canJump){friction = 0.0f;moveForce = groundMoveForce;}else{friction = 0.4f;moveForce = airMoveForce;}

            if(Main.w.isSpaceDown & canJump){velocity[1] = velocity[1] - 500f;Main.w.isSpaceDown = false;}

            float speedLimit = 500f;

            if(velocity[0] > speedLimit){velocity[0] = speedLimit;}
            if(velocity[0] < -speedLimit){velocity[0] = -speedLimit;}


            position[0] = physicsPosition[0];
            position[1] = physicsPosition[1];

            lastClickedCount = Main.w.clickCount;
            lastPhysicalPosition = new Float[]{physicsPosition[0], physicsPosition[1]};


            java.awt.Point mousePosition = Game.Main.w.frame.getMousePosition();

            if (mousePosition != null) {

                mousePosition.y = mousePosition.y - 36 - Main.currentLevel.off[1];
                mousePosition.x = mousePosition.x - Main.currentLevel.off[0];
                float a = Math.abs(mousePosition.y - position[1]);
                float b = Math.abs(mousePosition.x - position[0]);

                boolean inHitbox = Math.sqrt(a * a + b * b) < diameter;

                if (!Game.Main.w.mouseDown) {
                    mouseInHitbox = inHitbox;
                }

                if (mouseInHitbox & Game.Main.w.mouseDown) {
                    position[0] = (float) mousePosition.x + (Main.currentLevel.off[0]);
                    position[1] = (float) mousePosition.y + (Main.currentLevel.off[1] + 36);
                }
            }

            if(physicsPosition[1] > Main.deathAltitude){
                die();
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

    public boolean[] isInRectangle(float[] p, float[] p1, float[] p2, float lppy){

        boolean[] toReturn = new boolean[]{false, false, false};
        float cornerXPlus;
        float cornerXMinus;

        float cornerYPlus;
        float cornerYMinus;

        float x = p[0];
        float y = p[1];

        if(p1[0] < p2[0]){cornerXPlus = p2[0];cornerXMinus = p1[0];}else{cornerXPlus = p1[0];cornerXMinus = p2[0];}
        if(p1[1] < p2[1]){cornerYPlus = p2[1];cornerYMinus = p1[1];}else{cornerYPlus = p1[1];cornerYMinus = p2[1];}

        toReturn[0] = cornerXMinus < x & x < cornerXPlus & cornerYMinus < y & y < cornerYPlus;

        if(toReturn[0]){
            if(lppy > p1[1] & lppy < p2[1]){toReturn[1] = true;}
        float blockCornersDistanceX = Math.abs(p1[0] - p2[0]);
        float blockCornersDistanceY = Math.abs(p1[1] - p2[1]);

        if(toReturn[1]){if(Math.abs(p1[0] - p[0]) < (blockCornersDistanceX/2)){toReturn[2] = true;}}

        else{if(Math.abs(p1[1] - p[1]) < (blockCornersDistanceY/2)){toReturn[2] = true;}}
        }



        return toReturn;


    }

    public Float[] lineXLineSegmentIntersection(float[] p1, float[] p2, float[] p3, float[] p4){
        float x1 = p1[0];
        float x2 = p2[0];
        float x3 = p3[0];
        float x4 = p4[0];

        float y1 = p1[1];
        float y2 = p2[1];
        float y3 = p3[1];
        float y4 = p4[1];



        float d = (x1 - x2) * (y3 - y4 )- (y1 - y2)*(x3 - x4);
        if(d == 0){return new Float[]{null, null};}

        float x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3)) / d;
        float y = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

        float cornerXPlus;
        float cornerXMinus;

        float cornerYPlus;
        float cornerYMinus;

        if(p3[0] < p4[0]){cornerXPlus = p4[0];cornerXMinus = p3[0];}else{cornerXPlus = p3[0];cornerXMinus = p4[0];}
        if(p3[1] < p4[1]){cornerYPlus = p4[1];cornerYMinus = p3[1];}else{cornerYPlus = p3[1];cornerYMinus = p4[1];}

        if(cornerXMinus <= x & x <= cornerXPlus & cornerYMinus <= y & y <= cornerYPlus ){return new Float[]{x, y};}else{return new Float[]{null, null};}

    }

    public void die() throws InterruptedException {
        position[0] = null;
        position[1] = null;
        exists = false;
        Main.currentLevel.isRedAppearing = true;
        Main.currentLevel.startRedAppearing = true;

    }

}



