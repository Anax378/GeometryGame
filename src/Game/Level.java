package Game;

import levelParts.Block;
import shapes.*;
import shapes.Point;

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

    public int width;
    public int height;

    public Player player;

    public Level(List<Point> points,
                 List<LineSegment> lineSegments,
                 List<MidPoint> midPoints,
                 List<Circle> circles,
                 List<DiameterCircle> diameterCircles,
                 List<Line> lines,
                 List<LineXLineCrossection> lineXLineCrossections,
                 List<LineXLineSegmentCrossection> lineXLineSegmentCrossections,
                 List<LineSegmentXLineSegmentCrossection> lineSegmentXLineSegmentCrossections,
                 List<Block> blocks,
                 List<CircleXLineCrossection> circleXLineCrossections,
                 List<CircleXLineSegmentCrossection> circleXLineSegmentCrossections,
                 List<CircleXCircleCrossections> circleXCircleCrossections,
                 List<LineParallel> lineParallels,
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

        this.player = player;
        this.blocks = blocks;

        width = Main.w.width;
        height = Main.w.height;



    }

    public boolean reachedObjective(){return false;}

    public BufferedImage getFrame(){
        BufferedImage frame = new BufferedImage(Main.w.width, Main.w.height, BufferedImage.TYPE_INT_RGB);

        width = Main.w.width;
        height = Main.w.height;

        Graphics2D g = frame.createGraphics();
        g.setPaint(new Color(255, 255, 255));
        g.fillRect(0, 0, width, height);

        int objectsFailedtorender = 0;
        for(int i = 0;i < blocks.size(); i++){frame = blocks.get(i).renderOnImage(frame);if(!blocks.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < circles.size(); i++){frame = circles.get(i).renderOnImage(frame);if(!circles.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < diameterCircles.size(); i++){frame = diameterCircles.get(i).renderOnImage(frame);if(!diameterCircles.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lines.size(); i++){frame = lines.get(i).renderOnImage(frame);if(!lines.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineParallels.size(); i++){frame = lineParallels.get(i).renderOnImage(frame);if(!lineParallels.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineSegments.size(); i++){frame = lineSegments.get(i).renderOnImage(frame);if(!lineSegments.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineXLineCrossections.size(); i++){frame = lineXLineCrossections.get(i).renderOnImage(frame);if(!lineXLineCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineXLineSegmentCrossections.size(); i++){frame = lineXLineSegmentCrossections.get(i).renderOnImage(frame);if(!lineXLineSegmentCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineSegmentXLineSegmentCrossections.size(); i++){frame = lineSegmentXLineSegmentCrossections.get(i).renderOnImage(frame);if(!lineSegmentXLineSegmentCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < circleXLineCrossections.size(); i++){frame = circleXLineCrossections.get(i).renderOnImage(frame);if(!circleXLineCrossections.get(i).exists | (!circleXLineCrossections.get(i).exists1 & !circleXLineCrossections.get(i).exists2)){objectsFailedtorender++;}}
        for(int i = 0;i < circleXLineSegmentCrossections.size(); i++){frame = circleXLineSegmentCrossections.get(i).renderOnImage(frame);if(!circleXLineSegmentCrossections.get(i).exists | (!circleXLineSegmentCrossections.get(i).exists1 & !circleXLineSegmentCrossections.get(i).exists2)){objectsFailedtorender++;}}
        for(int i = 0;i < midPoints.size(); i++){frame = midPoints.get(i).renderOnImage(frame);if(!midPoints.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < circleXCircleCrossections.size(); i++){frame = circleXCircleCrossections.get(i).renderOnImage(frame);if(!circleXCircleCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < points.size(); i++){frame = points.get(i).renderOnImage(frame);if(!points.get(i).exists){objectsFailedtorender++;}}
        player.renderOnImage(frame);

        if (objectsFailedtorender != 0){
            g.setPaint(Color.RED);
            g.setFont(new Font("Plain", 0, 20));
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.drawString(String.valueOf(objectsFailedtorender) + "!", 20, 20);
            g.dispose();
        }

        return frame;

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
        for (int i = 0; i < lineXLineCrossections.size(); i++) {lineXLineCrossections.get(i).update();}
        for (int i = 0; i < lineXLineSegmentCrossections.size(); i++) {lineXLineSegmentCrossections.get(i).update();}
        for (int i = 0; i < lineSegmentXLineSegmentCrossections.size(); i++) {lineSegmentXLineSegmentCrossections.get(i).update();}
        for (int i = 0; i < circleXLineCrossections.size(); i++) {circleXLineCrossections.get(i).update();}
        for (int i = 0; i < circleXLineSegmentCrossections.size(); i++) {circleXLineSegmentCrossections.get(i).update();}
        for (int i = 0; i < circleXCircleCrossections.size(); i++) {circleXCircleCrossections.get(i).update();}
    }
}
