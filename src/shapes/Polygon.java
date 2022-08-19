package shapes;

import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Polygon implements Serializable {

    public Float[][] verts;
    public int[] Xs;
    public int[] Ys;

    public int[] rXs;
    public int[] rYs;

    public Color backgroundRenderColor;
    public Color renderColor;

    public boolean exists;

    public Polygon(Float[][] verts, Color renderColor){
        this.verts = verts;
        this.renderColor = renderColor;

        backgroundRenderColor = new Color(renderColor.getRed(), renderColor.getGreen(), renderColor.getBlue(), 10);

        Xs = new int[verts.length];
        Ys = new int[verts.length];

        rXs = new int[verts.length];
        rYs = new int[verts.length];

    }
    public void update(){
        boolean foundNull = false;
        for(int i = 0; i < verts.length; i++){

            if(verts[i][0] == null | verts[i][1] == null){foundNull = true; exists = false;}
            if(exists){
                Xs[i] = (int) verts[i][0].floatValue();
                Ys[i] = (int) verts[i][1].floatValue();
            }
            if(!foundNull){exists = true;}
        }
    }

    public BufferedImage renderOnImage(BufferedImage image){
        if(exists){
            for(int i = 0; i < verts.length; i++){

                rXs[i] = Xs[i] + Main.currentLevel.off[0];
                rYs[i] = Ys[i] + Main.currentLevel.off[1];
            }


            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(renderColor);
            g2d.drawPolygon(rXs, rYs, verts.length);
            g2d.setPaint(backgroundRenderColor);
            g2d.fillPolygon(rXs, rYs, verts.length);
        }
        return image;
    }
    public boolean isInRectangle(float x, float y){
        boolean foundNull = false;

        for (int i = 0; i < verts.length; i++){
            if(verts[i][0] == null | verts[i][1] == null){foundNull = true; exists = false;}
        }if(!foundNull){exists = true;}

        if(exists){

        int intersectionsFound = 0;
        float[] a = new float[2];
        float[] b = new float[2];

        float[] c = new float[]{x, y};
        float[] d = new float[]{1000000f, 1000000f};

        float[] r = new float[2];
        float[] s = new float[]{d[0] - c[0], d[1] - c[1]};

        for(int i = 0; i< verts.length ;i++){
            a[0] = verts[i][0];
            a[1] = verts[i][1];
            if(i+1 > verts.length - 1){b[0] = verts[0][0];b[1] = verts[0][1];}else{b[0] = verts[i+1][0]; b[1] = verts[i+1][1];}

            r[0] = b[0]-a[0];
            r[1] = b[1]-a[1];

            float u = cross(new float[]{a[0]-c[0],a[1]-c[1]},r)/cross(s,r);
            float t = cross(new float[]{c[0]-a[0],c[1]-a[1]},s)/cross(r,s);

            if(0 <= t & t <= 1 & 0 <= u & u <= 1){intersectionsFound++;}
        }
        return intersectionsFound % 2 != 0;

        }
        else{return false;}
    }

    public float cross(float[] a, float[] b){
        return a[0]*b[1] - a[1]*b[0];
    }
}