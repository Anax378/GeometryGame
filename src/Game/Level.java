package Game;

import levelParts.*;
import shapes.*;
import shapes.Point;
import shapes.Polygon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Level implements Serializable {

    public List<Point> points = new ArrayList<>();
    public List<LineSegment> lineSegments = new ArrayList<>();
    public List<MidPoint> midPoints = new ArrayList<>();
    public List<Circle> circles = new ArrayList<>();
    public List<DiameterCircle> diameterCircles = new ArrayList<>();
    public List<Line> lines = new ArrayList<>();
    public List<LineXLineCrossection> lineXLineCrossections = new ArrayList<>();
    public List<LineXLineSegmentCrossection> lineXLineSegmentCrossections = new ArrayList<>();
    public List<LineSegmentXLineSegmentCrossection> lineSegmentXLineSegmentCrossections = new ArrayList<>();
    public List<Block> blocks = new ArrayList<>();
    public List<CircleXLineCrossection> circleXLineCrossections = new ArrayList<>();
    public List<CircleXLineSegmentCrossection> circleXLineSegmentCrossections = new ArrayList<>();
    public List<CircleXCircleCrossection> circleXCircleCrossections = new ArrayList<>();
    public List<LineParallel> lineParallels = new ArrayList<>();
    public List<LinePerpendicular> linePerpendiculars = new ArrayList<>();
    public List<Orb> orbs = new ArrayList<>();
    public List<Mover> movers = new ArrayList<>();
    public List<Polygon> polygons = new ArrayList<>();

    public int width;
    public int height;

    public int[] off = new int[]{0, 0};

    public Player player;

    public double redOverlayAlpha = 0;
    public boolean isRedAppearing = false;
    public boolean startRedAppearing = false;

    long redStart = 0;
    long greenStart = 0;

    public double greenOverlayAlpha = 0;
    public boolean isGreenDisappearing = false;
    public boolean startGreenDisappearing = false;


    float[] initialPlayerPosition = new float[2];
    List<float[]>initialMoverPositions = new ArrayList<>();

    public Level(Player player){
        this.player = player;

        width = Main.w.width;
        height = Main.w.height;

        initialPlayerPosition[0] = player.position[0];
        initialPlayerPosition[1] = player.position[1];

    }

    public boolean reachedObjective(){return false;}

    public BufferedImage getFrame(){

        width = Main.w.width;
        height = Main.w.height;
        if(player.position[0] == null | player.position[1] == null){;}else{
            off[0] = Math.round((width/2f) - player.position[0]);
            off[1] = Math.round((height/2f) - player.position[1]) + 150;
        }

        BufferedImage field = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D fig = field.createGraphics();
        fig.setPaint(new Color(255, 255, 255));
        fig.fillRect(0, 0, width, height);

        int objectsFailedToRender = 0;
        for(int i = 0;i < blocks.size(); i++){field = blocks.get(i).renderOnImage(field);if(!blocks.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < circles.size(); i++){field = circles.get(i).renderOnImage(field);if(!circles.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < diameterCircles.size(); i++){field = diameterCircles.get(i).renderOnImage(field);if(!diameterCircles.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < lines.size(); i++){field = lines.get(i).renderOnImage(field);if(!lines.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < lineParallels.size(); i++){field = lineParallels.get(i).renderOnImage(field);if(!lineParallels.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < linePerpendiculars.size(); i++){field = linePerpendiculars.get(i).renderOnImage(field);if(!linePerpendiculars.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < lineSegments.size(); i++){field = lineSegments.get(i).renderOnImage(field);if(!lineSegments.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < lineXLineCrossections.size(); i++){field = lineXLineCrossections.get(i).renderOnImage(field);if(!lineXLineCrossections.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < lineXLineSegmentCrossections.size(); i++){field = lineXLineSegmentCrossections.get(i).renderOnImage(field);if(!lineXLineSegmentCrossections.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < lineSegmentXLineSegmentCrossections.size(); i++){field = lineSegmentXLineSegmentCrossections.get(i).renderOnImage(field);if(!lineSegmentXLineSegmentCrossections.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < circleXLineCrossections.size(); i++){field = circleXLineCrossections.get(i).renderOnImage(field);if(!circleXLineCrossections.get(i).exists | (!circleXLineCrossections.get(i).exists1 & !circleXLineCrossections.get(i).exists2)){objectsFailedToRender++;}}
        for(int i = 0;i < circleXLineSegmentCrossections.size(); i++){field = circleXLineSegmentCrossections.get(i).renderOnImage(field);if(!circleXLineSegmentCrossections.get(i).exists | (!circleXLineSegmentCrossections.get(i).exists1 & !circleXLineSegmentCrossections.get(i).exists2)){objectsFailedToRender++;}}
        for(int i = 0;i < midPoints.size(); i++){field = midPoints.get(i).renderOnImage(field);if(!midPoints.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < circleXCircleCrossections.size(); i++){field = circleXCircleCrossections.get(i).renderOnImage(field);if(!circleXCircleCrossections.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < points.size(); i++){field = points.get(i).renderOnImage(field);if(!points.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < polygons.size(); i++){field = polygons.get(i).renderOnImage(field);if(!polygons.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < orbs.size(); i++){field = orbs.get(i).renderOnImage(field);if(!orbs.get(i).exists){objectsFailedToRender++;}}
        for(int i = 0;i < movers.size(); i++){field = movers.get(i).renderOnImage(field);if(!movers.get(i).exists){objectsFailedToRender++;}}
        player.renderOnImage(field);

        if (objectsFailedToRender != 0){
            fig.setPaint(Color.RED);
            fig.setFont(new Font("Plain", Font.PLAIN, 20));
            fig.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            fig.drawString(String.valueOf(objectsFailedToRender) + "!", 20 + off[0], 20 + off[1]);
            fig.dispose();
        }
        if(startRedAppearing){
            redStart = System.currentTimeMillis();
            startRedAppearing = false;}
        if(isRedAppearing){redOverlayAlpha = ((255d/1000d)*(System.currentTimeMillis() - redStart));}

        if(redOverlayAlpha > 250d){
            isRedAppearing = false; redOverlayAlpha = 0d;Main.isTimeToRestart = true;}
        if(isRedAppearing){
            fig = field.createGraphics();
            fig.setPaint(new Color(255, 0, 0, (int) Math.round(redOverlayAlpha)));
            fig.fillRect(0, 0, width, height);
        }
        if(startGreenDisappearing){
            greenStart = System.currentTimeMillis();
            greenOverlayAlpha = 255;
            startGreenDisappearing = false;
            isGreenDisappearing = true;
        }
        if(isGreenDisappearing){
            greenOverlayAlpha = 255f - ((255d/1000d)*(System.currentTimeMillis() - greenStart));
        }
        if(greenOverlayAlpha < 0){
            isGreenDisappearing = false;
            startGreenDisappearing = false;
            greenOverlayAlpha = 0;
            Main.isTimeToRestart = true;
            Main.isTimeToNext = true;
        }
        if(isGreenDisappearing){
            fig = field.createGraphics();
            fig.setPaint(new Color(0, 255, 0, (int) Math.round(greenOverlayAlpha)));
            fig.fillRect(0, 0, width, height);
        }


        fig.dispose();
        return field;

    }

    public static boolean isInCircle(float[] position,float[] center, float radius){
        float a = Math.abs(position[0] - center[0]);
        float b = Math.abs(position[1] - center[1]);
        float distance = (float) Math.sqrt(a*a + b*b);
        return distance < radius;
    }

    public void update() {
        for (int i = 0; i < midPoints.size(); i++) {midPoints.get(i).update();}
        for (int i = 0; i < circles.size(); i++) {circles.get(i).update();}
        for (int i = 0; i < lines.size(); i++) {lines.get(i).update();}
        for (int i = 0; i < lineParallels.size(); i++) {lineParallels.get(i).update();}
        for (int i = 0; i < linePerpendiculars.size(); i++) {linePerpendiculars.get(i).update();}
        for (int i = 0; i < lineXLineCrossections.size(); i++) {lineXLineCrossections.get(i).update();}
        for (int i = 0; i < lineXLineSegmentCrossections.size(); i++) {lineXLineSegmentCrossections.get(i).update();}
        for (int i = 0; i < lineSegmentXLineSegmentCrossections.size(); i++) {lineSegmentXLineSegmentCrossections.get(i).update();}
        for (int i = 0; i < circleXLineCrossections.size(); i++) {circleXLineCrossections.get(i).update();}
        for (int i = 0; i < circleXLineSegmentCrossections.size(); i++) {circleXLineSegmentCrossections.get(i).update();}
        for (int i = 0; i < circleXCircleCrossections.size(); i++) {circleXCircleCrossections.get(i).update();}
        for (int i = 0; i < polygons.size(); i++) {polygons.get(i).update();}
        for (int i = 0; i < movers.size(); i++) {movers.get(i).update();}
    }
    public void restart(){
        player.physicsPosition[0] = initialPlayerPosition[0];
        player.physicsPosition[1] = initialPlayerPosition[1];
        player.position[0] = initialPlayerPosition[0];
        player.position[1] = initialPlayerPosition[1];


        for (int i = 0; i < movers.size(); i++){
            movers.get(i).position[0] = initialMoverPositions.get(i)[0];
            movers.get(i).position[1] = initialMoverPositions.get(i)[1];
        }
    }

    public void add(Object object){
        if(object instanceof Point){points.add((Point) object);}
        if(object instanceof LineSegment){lineSegments.add((LineSegment) object);}
        if(object instanceof MidPoint){midPoints.add((MidPoint) object);}
        if(object instanceof Circle){circles.add((Circle) object);}
        if(object instanceof DiameterCircle){diameterCircles.add((DiameterCircle) object);}
        if(object instanceof Line){lines.add((Line) object);}
        if(object instanceof LineXLineCrossection){lineXLineCrossections.add((LineXLineCrossection) object);}
        if(object instanceof LineXLineSegmentCrossection){lineXLineSegmentCrossections.add((LineXLineSegmentCrossection) object);}
        if(object instanceof Block){blocks.add((Block) object);}
        if(object instanceof CircleXLineCrossection){circleXLineCrossections.add((CircleXLineCrossection) object);}
        if(object instanceof CircleXLineSegmentCrossection){circleXLineSegmentCrossections.add((CircleXLineSegmentCrossection) object);}
        if(object instanceof CircleXCircleCrossection){circleXCircleCrossections.add((CircleXCircleCrossection) object);}
        if(object instanceof LineParallel){lineParallels.add((LineParallel) object);}
        if(object instanceof LinePerpendicular){linePerpendiculars.add((LinePerpendicular) object);}
        if(object instanceof Orb){orbs.add((Orb) object);}
        if(object instanceof Mover){movers.add((Mover) object);initialMoverPositions.add(new float[]{((Mover) object).position[0], ((Mover) object).position[1]});}
        if(object instanceof Polygon){polygons.add((Polygon) object);}



    }

    public Float[] toRenderCoords(Float[] coords){

        Float[] renderCoords = new Float[]{coords[0] + off[0], coords[1] + off[1]};

        renderCoords[0] = renderCoords[0] - (width/2f);
        renderCoords[1] = renderCoords[1] - (height/2f) - 150;

        renderCoords[0] = renderCoords[0] * Main.zoomModifier;
        renderCoords[1] = renderCoords[1] * Main.zoomModifier;

        renderCoords[0] = renderCoords[0] + (width/2f);
        renderCoords[1] = renderCoords[1] + (height/2f) + 150;

        return renderCoords;

    }
    public Float toRenderLength(Float length){
        return length * Main.zoomModifier;
    }

}
