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

    public List<Point> points;
    public List<LineSegment> lineSegments;
    public List<MidPoint> midPoints;
    public List<Circle> circles;
    public List<DiameterCircle> diameterCircles;
    public List<Line> lines;
    public List<LineXLineCrossection> lineXLineCrossections;
    public List<LineXLineSegmentCrossection> lineXLineSegmentCrossections;
    public List<LineSegmentXLineSegmentCrossection> lineSegmentXLineSegmentCrossections;
    public List<Block> blocks;
    public List<CircleXLineCrossection> circleXLineCrossections;
    public List<CircleXLineSegmentCrossection> circleXLineSegmentCrossections;
    public List<CircleXCircleCrossections> circleXCircleCrossections;
    public List<LineParallel> lineParallels;
    public List<LinePerpendicular> linePerpendiculars;
    public List<Orb> orbs;
    public List<Mover> movers;
    public List<Polygon> polygons;

    public int width;
    public int height;

    public int[] off = new int[]{0, 0};

    public Player player;

    public double redOverlayAlpha = 0;
    public boolean isAppearing = false;
    public boolean startAppearingFlag = false;
    long start = 0;

    public Level(List<Point> points,
                 List<LineSegment> lineSegments,
                 List<MidPoint> midPoints,
                 List<Circle> circles,
                 List<DiameterCircle> diameterCircles,
                 List<Line> lines,
                 List<LineXLineCrossection> lineXLineCrossections,
                 List<LineXLineSegmentCrossection> lineXLineSegmentCrossections,
                 List<LineSegmentXLineSegmentCrossection> lineSegmentXLineSegmentCrossections,
                 List<CircleXLineCrossection> circleXLineCrossections,
                 List<CircleXLineSegmentCrossection> circleXLineSegmentCrossections,
                 List<CircleXCircleCrossections> circleXCircleCrossections,
                 List<LineParallel> lineParallels,
                 List<LinePerpendicular> linePerpendiculars,
                 List<Polygon> polygons,
                 List<Block> blocks,
                 List<Orb> orbs,
                 List<Mover> movers,
                 Player player
                 ){

        this.points = points;
        this.lineSegments = lineSegments;
        this.midPoints = midPoints;
        this.circles = circles;
        this.diameterCircles = diameterCircles;
        this.lines = lines;
        this.lineXLineCrossections = lineXLineCrossections;
        this.lineXLineSegmentCrossections = lineXLineSegmentCrossections;
        this.lineSegmentXLineSegmentCrossections = lineSegmentXLineSegmentCrossections;
        this.circleXLineCrossections = circleXLineCrossections;
        this.circleXLineSegmentCrossections = circleXLineSegmentCrossections;
        this.circleXCircleCrossections = circleXCircleCrossections;
        this.lineParallels = lineParallels;
        this.linePerpendiculars = linePerpendiculars;
        this.polygons = polygons;

        this.player = player;
        this.blocks = blocks;
        this.orbs = orbs;
        this.movers = movers;

        width = Main.w.width;
        height = Main.w.height;



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

        int objectsFailedtorender = 0;
        for(int i = 0;i < blocks.size(); i++){field = blocks.get(i).renderOnImage(field);if(!blocks.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < circles.size(); i++){field = circles.get(i).renderOnImage(field);if(!circles.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < diameterCircles.size(); i++){field = diameterCircles.get(i).renderOnImage(field);if(!diameterCircles.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lines.size(); i++){field = lines.get(i).renderOnImage(field);if(!lines.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineParallels.size(); i++){field = lineParallels.get(i).renderOnImage(field);if(!lineParallels.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < linePerpendiculars.size(); i++){field = linePerpendiculars.get(i).renderOnImage(field);if(!linePerpendiculars.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineSegments.size(); i++){field = lineSegments.get(i).renderOnImage(field);if(!lineSegments.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineXLineCrossections.size(); i++){field = lineXLineCrossections.get(i).renderOnImage(field);if(!lineXLineCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineXLineSegmentCrossections.size(); i++){field = lineXLineSegmentCrossections.get(i).renderOnImage(field);if(!lineXLineSegmentCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineSegmentXLineSegmentCrossections.size(); i++){field = lineSegmentXLineSegmentCrossections.get(i).renderOnImage(field);if(!lineSegmentXLineSegmentCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < circleXLineCrossections.size(); i++){field = circleXLineCrossections.get(i).renderOnImage(field);if(!circleXLineCrossections.get(i).exists | (!circleXLineCrossections.get(i).exists1 & !circleXLineCrossections.get(i).exists2)){objectsFailedtorender++;}}
        for(int i = 0;i < circleXLineSegmentCrossections.size(); i++){field = circleXLineSegmentCrossections.get(i).renderOnImage(field);if(!circleXLineSegmentCrossections.get(i).exists | (!circleXLineSegmentCrossections.get(i).exists1 & !circleXLineSegmentCrossections.get(i).exists2)){objectsFailedtorender++;}}
        for(int i = 0;i < midPoints.size(); i++){field = midPoints.get(i).renderOnImage(field);if(!midPoints.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < circleXCircleCrossections.size(); i++){field = circleXCircleCrossections.get(i).renderOnImage(field);if(!circleXCircleCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < points.size(); i++){field = points.get(i).renderOnImage(field);if(!points.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < polygons.size(); i++){field = polygons.get(i).renderOnImage(field);if(!polygons.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < orbs.size(); i++){field = orbs.get(i).renderOnImage(field);if(!orbs.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < movers.size(); i++){field = movers.get(i).renderOnImage(field);if(!movers.get(i).exists){objectsFailedtorender++;}}
        player.renderOnImage(field);

        if (objectsFailedtorender != 0){
            fig.setPaint(Color.RED);
            fig.setFont(new Font("Plain", Font.PLAIN, 20));
            fig.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            fig.drawString(String.valueOf(objectsFailedtorender) + "!", 20 + off[0], 20 + off[1]);
            fig.dispose();
        }
        if(startAppearingFlag){start = System.currentTimeMillis();startAppearingFlag = false;}
        if(isAppearing){redOverlayAlpha = ((255d/1000d)*(System.currentTimeMillis() - start));}

        if(redOverlayAlpha > 250d){isAppearing = false; redOverlayAlpha = 0d;}
        if(isAppearing){
            fig = field.createGraphics();
            fig.setPaint(new Color(255, 0, 0, (int) Math.round(redOverlayAlpha)));
            fig.fillRect(0, 0, width, height);
        }
        fig.dispose();
        return field;

    }

    public static boolean isInCircle(float[] position,float[] center, float diameter){
        float a = Math.abs(position[0] - center[0]);
        float b = Math.abs(position[1] - center[1]);
        double distance = Math.sqrt(a*a + b*b);
        return distance < diameter;
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
}
