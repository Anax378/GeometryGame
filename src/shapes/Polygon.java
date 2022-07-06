package shapes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Polygon {

    public Float[][] verts;
    public int[] Xs;
    public int[] Ys;
    public Color backgroundRenderColor;
    public Color renderColor;

    public boolean exists;

    public void Polygon(Float[][] verts, Color renderColor){
        this.verts = verts;
        this.renderColor = renderColor;

        backgroundRenderColor = new Color(renderColor.getRed(), renderColor.getGreen(), renderColor.getBlue(), 100);

        Xs = new int[verts.length];
        Ys = new int[verts.length];

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
          Graphics2D g2d = image.createGraphics();
          g2d.setPaint(renderColor);
          g2d.drawPolyline(Xs, Ys, verts.length);
          g2d.setPaint(backgroundRenderColor);
          g2d.drawPolygon(Xs, Ys, verts.length);
        }
        return image;
    }
    public boolean isInRectangle(){
        // not finished
        return false;
    }
}