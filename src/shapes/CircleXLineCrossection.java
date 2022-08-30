package shapes;

import Game.Main;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class CircleXLineCrossection implements Serializable {
    public Float[] position1 = new Float[]{null, null};
    public Float[] position2 = new Float[]{null, null};

    public Float[] p1;
    public Float[] p2;
    public Float[] centre;
    public Float[] circleDiameter;

    public float renderDiameter;

    public Color renderColor;

    public boolean exists1;
    public boolean exists2;
    public boolean exists;

    public CircleXLineCrossection (Float[] p1, Float[] p2, Float[] centre, Float circleDiameter[], float renderDiameter, Color renderColor){
        this.p1 = p1;
        this.p2 = p2;
        this.centre = centre;
        this.circleDiameter = circleDiameter;
        this.renderColor = renderColor;
        this.renderDiameter = renderDiameter;
    }
    public void update(){
        if(p1[0] == null | p1[1] == null | p2[0] == null | p2[1] == null | circleDiameter[0] == null | centre[0] == null | centre[1] == null){exists = false;}else{exists = true;}
        if (exists){
            float x1 = p1[0] - centre[0];
            float x2 = p2[0] - centre[0];
            float y1 = p1[1] - centre[1];
            float y2 = p2[1] - centre[1];

            float dx = x2 - x1;
            float dy = y2 - y1;
            float dr = (float) Math.sqrt(dx*dx + dy*dy);
            float d = x1*y2-x2*y1;



            float discriminant = ((circleDiameter[0]/2f)*(circleDiameter[0]/2f) * (dr*dr)) - (d*d);

            if(discriminant < 0f){exists = false; exists1 = false; exists2 = false; position1[0] = null; position1[1] = null; position2[0] = null; position2[1] = null;}
            if (discriminant >= 0f){

                Float[] buffer1 = new Float[]{0f,0f};
                Float[] buffer2 = new Float[]{0f,0f};

                buffer1[0] = centre[0] + (float)( (d*dy + sgn(dy)*dx*Math.sqrt(discriminant))/(dr*dr));
                buffer1[1] = centre[1] +  (float)( (-d*dx + Math.abs(dy)*Math.sqrt(discriminant))/(dr*dr));

                buffer2[0] = centre[0] + (float)( (d*dy - sgn(dy)*dx*Math.sqrt(discriminant))/(dr*dr));
                buffer2[1] = centre[1] +  (float)( (-d*dx - Math.abs(dy)*Math.sqrt(discriminant))/(dr*dr));

                float angleInRadians = (float) Math.toRadians(90);

                float vX = p2[0] - p1[0];
                float vY = p2[1] - p1[1];

                Float[] vrlp2 = new Float[]{(float) (vX*Math.cos(angleInRadians) - vY*Math.sin(angleInRadians)), (float) (vY*Math.cos(angleInRadians)+ vX*Math.sin(angleInRadians))};

                Float[] rlp2 = new Float[]{vrlp2[0] + p1[0], vrlp2[1] + p1[1]};

                Float[] vector = new Float[]{p1[0] - rlp2[0], p1[1] - rlp2[1]};



                Float[] a = centre;
                Float[] b = new Float[]{null, null};
                Float[] c = buffer1;

                b[0] = p1[0] + vector[0];
                b[1] = p1[1] + vector[1];

                boolean is1Left = ((b[0] - a[0])*(c[1] - a[1]) - (b[1] - a[1])*(c[0] - a[0])) > 0;

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






                exists1 = true;
                exists2 = true;

            }
        }
    }

    public BufferedImage renderOnImage(BufferedImage image){
        if (p1[0] == null | p1[1] == null | p2[0] == null | p2[1] == null){exists = false;}
        if(exists1 & exists) {
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle1 = new Ellipse2D.Double((Main.currentLevel.toRenderCoords(position1)[0]) - (Main.currentLevel.toRenderLength((float) renderDiameter) / 2f), (Main.currentLevel.toRenderCoords(position1)[1]) - (Main.currentLevel.toRenderLength((float) renderDiameter) / 2f), Main.currentLevel.toRenderLength((float) renderDiameter), Main.currentLevel.toRenderLength((float) renderDiameter));
            g2d.fill(circle1);
            g2d.dispose();
        }
        if(exists2 & exists){
            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            Ellipse2D.Double circle2 = new Ellipse2D.Double((Main.currentLevel.toRenderCoords(position2)[0]) - (Main.currentLevel.toRenderLength((float) renderDiameter) / 2f), (Main.currentLevel.toRenderCoords(position2)[1]) - (Main.currentLevel.toRenderLength((float) renderDiameter) / 2f), Main.currentLevel.toRenderLength((float) renderDiameter), Main.currentLevel.toRenderLength((float) renderDiameter));
            g2d.fill(circle2);
            g2d.dispose();

        }

        return image;

    }
    public float sgn (float x){if (x<0){return -1f;}else{return 1f;}}
}
