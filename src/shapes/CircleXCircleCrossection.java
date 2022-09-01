package shapes;

import Game.Main;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class CircleXCircleCrossection implements Serializable {
    public Float[] centre1;
    public Float[] centre2;

    public Float[] diameter1;
    public Float[] diameter2;

    public Float[] position1 = new Float[]{null, null};
    public Float[] position2 = new Float[]{null, null};

    public float renderDiameter;
    public Color renderColor1;
    public Color renderColor2;

    public boolean exists;



    public CircleXCircleCrossection(Float[] centre1, Float[] diameter1, Float[] centre2, Float[] diameter2, float renderDiameter, Color renderColor1, Color renderColor2){
        this.centre1 = centre1;
        this.centre2 = centre2;
        this.diameter1 = diameter1;
        this.diameter2 = diameter2;
        this.renderColor1 = renderColor1;
        this.renderColor2 = renderColor2;
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

                Float[] buffer1 = new Float[]{0f,0f};
                Float[] buffer2 = new Float[]{0f,0f};

                Float[] a85 = centre1;
                Float[] b = centre2;
                Float[] c = buffer1;

                boolean is1Left = ((b[0] - a85[0])*(c[1] - a85[1]) - (b[1] - a85[1])*(c[0] - a85[0])) > 0;

                if(!is1Left){
                    //switch
                    position1[0] = buffer2[0];
                    position1[1] = buffer2[1];

                    position2[0] = buffer1[0];
                    position2[1] = buffer1[1];

                }else{
                    //do not switch

                    position1[0] = buffer1[0];
                    position1[1] = buffer1[1];

                    position2[0] = buffer2[0];
                    position2[1] = buffer2[1];

                }


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
            g2d.setPaint(renderColor1);
            Ellipse2D.Double circle1 = new Ellipse2D.Double((Main.currentLevel.toRenderCoords(position1)[0]) - (Main.currentLevel.toRenderLength((float) renderDiameter) / 2f), (Main.currentLevel.toRenderCoords(position1)[1]) - (Main.currentLevel.toRenderLength((float) Main.currentLevel.toRenderLength((float) renderDiameter)) / 2f), Main.currentLevel.toRenderLength((float) renderDiameter), Main.currentLevel.toRenderLength((float) renderDiameter));
            g2d.setPaint(renderColor2);
            Ellipse2D.Double circle2 = new Ellipse2D.Double((Main.currentLevel.toRenderCoords(position2)[0]) - (Main.currentLevel.toRenderLength((float) renderDiameter) / 2f), (Main.currentLevel.toRenderCoords(position2)[1]) - (Main.currentLevel.toRenderLength((float) Main.currentLevel.toRenderLength((float) renderDiameter)) / 2f), Main.currentLevel.toRenderLength((float) renderDiameter), Main.currentLevel.toRenderLength((float) renderDiameter));
            g2d.fill(circle1);
            g2d.fill(circle2);

        }
        return image;
    }
}
