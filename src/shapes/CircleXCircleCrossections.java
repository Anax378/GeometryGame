package shapes;

import Game.Main;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class CircleXCircleCrossections implements Serializable {
    public Float[] centre1;
    public Float[] centre2;

    public Float[] diameter1;
    public Float[] diameter2;

    public Float[] position1 = new Float[]{null, null};
    public Float[] position2 = new Float[]{null, null};

    public float renderDiameter;
    public Color renderColor;

    public boolean exists;



    public CircleXCircleCrossections(Float[] centre1, Float[] diameter1, Float[] centre2, Float[] diameter2, float renderDiameter, Color renderColor){
        this.centre1 = centre1;
        this.centre2 = centre2;
        this.diameter1 = diameter1;
        this.diameter2 = diameter2;
        this.renderColor = renderColor;
        this.renderDiameter = renderDiameter;
    }

    public void update(){
        if(diameter1[0] == null | diameter2[0] == null | centre1[0] == null | centre1[1] == null | centre2[0] == null | centre2[1] == null){exists = false;}else{exists = true;}
        if(exists){
            float da = centre1[0] - centre2[0];
            float db = centre1[1] - centre2[1];
            float d = (float) Math.sqrt((da*da)+(db*db));
            if(d > (diameter1[0]/2f + diameter2[0]/2f)){exists = false; position1[0] = null; position1[1] = null; position2[0] = null; position2[1] = null;}
            else{
                float a = ((diameter1[0]/2f)*(diameter1[0]/2f) - (diameter2[0]/2f)*(diameter2[0]/2f) + d*d) / (d*2f);
                float h = (float) Math.sqrt(((diameter1[0]/2f)*(diameter1[0]/2f))-a*a);

                float[] p2 = new float[]{centre1[0]+a*(centre2[0]-centre1[0])/d, centre1[1]+a*(centre2[1]-centre1[1])/d};

                position1[0] = p2[0]+h*(centre2[1]-centre1[1])/d;
                position1[1] = (p2[1]-h*(centre2[0]-centre1[0])/d);

                position2[0] = p2[0]-h*(centre2[1]-centre1[1])/d;
                position2[1] = (p2[1]+h*(centre2[0]-centre1[0])/d);


                exists = true;
            }

        }
    }

    public BufferedImage renderOnImage(BufferedImage image){
        if(position1[0] == null | position1[1] == null | position2[0] == null | position2[1] == null){exists = false;}else{exists = true;}
        if (exists){
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle1 = new Ellipse2D.Double((position1[0] + Main.currentLevel.off[0]) - (renderDiameter / 2f), (position1[1] + Main.currentLevel.off[1]) - (renderDiameter / 2f), renderDiameter, renderDiameter);
            Ellipse2D.Double circle2 = new Ellipse2D.Double((position2[0] + Main.currentLevel.off[0]) - (renderDiameter / 2f), (position2[1] + Main.currentLevel.off[1]) - (renderDiameter / 2f), renderDiameter, renderDiameter);
            g2d.fill(circle1);
            g2d.fill(circle2);

        }
        return image;
    }
}
