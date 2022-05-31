package Game;

import levelParts.Block;
import shapes.*;
import shapes.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
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
        this.player = player;
        this.blocks = blocks;



    }

    public boolean reachedObjective(){return false;}

    public BufferedImage getFrame(){
        BufferedImage frame = new BufferedImage(Main.w.width, Main.w.height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = frame.createGraphics();
        g.setPaint(new Color(255, 255, 255));
        g.fillRect(0, 0, Main.w.width, Main.w.height);

        int objectsFailedtorender = 0;
        for(int i = 0;i < blocks.size(); i++){frame = blocks.get(i).renderOnImage(frame);if(!blocks.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < circles.size(); i++){frame = circles.get(i).renderOnImage(frame);if(!circles.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < diameterCircles.size(); i++){frame = diameterCircles.get(i).renderOnImage(frame);if(!diameterCircles.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lines.size(); i++){frame = lines.get(i).renderOnImage(frame);if(!lines.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineSegments.size(); i++){frame = lineSegments.get(i).renderOnImage(frame);if(!lineSegments.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineXLineCrossections.size(); i++){frame = lineXLineCrossections.get(i).renderOnImage(frame);if(!lineXLineCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineXLineSegmentCrossections.size(); i++){frame = lineXLineSegmentCrossections.get(i).renderOnImage(frame);if(!lineXLineSegmentCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < lineSegmentXLineSegmentCrossections.size(); i++){frame = lineSegmentXLineSegmentCrossections.get(i).renderOnImage(frame);if(!lineSegmentXLineSegmentCrossections.get(i).exists){objectsFailedtorender++;}}
        for(int i = 0;i < midPoints.size(); i++){frame = midPoints.get(i).renderOnImage(frame);if(!midPoints.get(i).exists){objectsFailedtorender++;}}
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

}
