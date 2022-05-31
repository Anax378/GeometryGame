package Game;

import levelParts.Block;
import shapes.*;
import shapes.Point;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Window w;

    public static Level currentLevel;
    static int currentLevelID;

    public static int tps = 100;

    public static List<Point> points = new ArrayList<>();
    public static List<LineSegment> lineSegments = new ArrayList<>();
    public static List<MidPoint> midPoints = new ArrayList<>();
    public static List<Circle> circles = new ArrayList<>();
    public static List<DiameterCircle> diameterCircles = new ArrayList<>();
    public static List<Line> lines = new ArrayList<>();
    public static List<LineXLineCrossection> libeXLineCrossections = new ArrayList<>();
    public static List<LineXLineSegmentCrossection> lineXLineSegmentCrossections = new ArrayList<>();
    public static List<LineSegmentXLineSegmentCrossection> lineSegmentXLineSegmentCrossections = new ArrayList<>();
    public static List<Level> levels = new ArrayList<>();
    public static List<Block> blocks = new ArrayList<>();
    public static Player player;


    public static void main(String[] args) throws IOException, ClassNotFoundException {


        w = new Window();

        player = new Player(new Integer[]{200, 500}, new Color(49, 157, 235), 10);

        points.add(new Point(new Integer[]{150, 100}, Color.YELLOW, 10)); //new Integer[]{150, 100}     //0
        points.add(new Point(new Integer[]{300, 200}, Color.ORANGE, 10));//new Integer[]{300, 200}      //1
        points.add(new Point(new Integer[]{200, 200}, Color.MAGENTA, 10));//new Integer[]{300, 200}     //2
        points.add(new Point(new Integer[]{100, 400}, Color.BLACK, 10));                                //3
        points.add(new Point(new Integer[]{400, 400}, Color.GRAY, 10));                                 //4
        points.add(new Point(new Integer[]{0, 0}, Color.GRAY, 10));                                 //5
        lineSegments.add(new LineSegment(player.position, points.get(0).position, Color.BLACK));
        lineSegments.add(new LineSegment(points.get(3).position, points.get(4).position, Color.BLACK));
        midPoints.add(new MidPoint(player.position, points.get(0).position, Color.RED, 10));
        circles.add(new Circle(midPoints.get(0).position, points.get(1).position, Color.BLACK));
        diameterCircles.add(new DiameterCircle(player.position, 50, Color.BLACK));
        lines.add(new Line(player.position, points.get(2).position, Color.BLACK, new int []{w.width, w.height}));
        lines.add(new Line(points.get(0).position, points.get(1).position, Color.BLACK, new int []{w.width, w.height}));
        lines.add(new Line(midPoints.get(0).position, points.get(1).position, Color.BLACK, new int []{w.width, w.height}));
        lineXLineSegmentCrossections.add(new LineXLineSegmentCrossection(player.position, points.get(2).position,points.get(0).position,points.get(2).position, Color.GREEN, 10));
        lineSegmentXLineSegmentCrossections.add(new LineSegmentXLineSegmentCrossection(points.get(3).position, points.get(4).position, player.position, points.get(2).position, Color.BLACK, 10));

        blocks.add(new Block(new Integer[]{100, 100}, new Integer[]{200, 200}, new Color(200, 200, 200)));

        Level level1 = new Level(
                points,
                lineSegments,
                midPoints,
                circles,
                diameterCircles,
                lines,
                libeXLineCrossections,
                lineXLineSegmentCrossections,
                lineSegmentXLineSegmentCrossections,
                blocks,
                player);

        levels.add(level1);

        //serializeDataOut(level1, "level0");

        //levels.add((Level) serializeDataIn("level0"));
        //levels.add((Level) serializeDataIn("level1"));
        //levels.add((Level) serializeDataIn("level2"));
        //levels.add((Level) serializeDataIn("level3"));
        //levels.add((Level) serializeDataIn("level4"));
        //levels.add((Level) serializeDataIn("level4"));


        currentLevel = levels.get(0);
        currentLevelID = 0;


        int fps = 0;
        long start = System.currentTimeMillis();

        boolean isInMenu = true;
        Menu menu = new Menu(levels.size());

        long startTime = System.currentTimeMillis();

        while(true) {       //  frame loop
            fps++;
            java.awt.Point mousePosition = w.frame.getMousePosition();

            if(w.isEscDown){isInMenu = true;}

            if(isInMenu){
                player.mouseInHitbox = false;

                menu.update();
                w.setImage(menu.getFrame());

                if(menu.levelChosen){
                    currentLevel = levels.get(menu.selectedLevel - 1);
                    currentLevelID = menu.selectedLevel-1;
                    menu.levelChosen = false;
                    isInMenu = false;
                }

            }

            else {

                if(w.isRDown){
                    //levels.set(currentLevelID, (Level)serializeDataIn("level" + currentLevelID));
                    currentLevel = levels.get(currentLevelID);
                }
                if(System.currentTimeMillis() - startTime >= 1000/tps){
                    player.update();
                    startTime = System.currentTimeMillis();
                }


                for (int i = 0; i < currentLevel.midPoints.size(); i++) {currentLevel.midPoints.get(i).update();}
                for (int i = 0; i < currentLevel.circles.size(); i++) {currentLevel.circles.get(i).update();}
                for (int i = 0; i < currentLevel.lines.size(); i++) {currentLevel.lines.get(i).update();}
                for (int i = 0; i < currentLevel.lineXLineCrossections.size(); i++) {currentLevel.lineXLineCrossections.get(i).update();}
                for (int i = 0; i < currentLevel.lineXLineSegmentCrossections.size(); i++) {currentLevel.lineXLineSegmentCrossections.get(i).update();}
                for (int i = 0; i < currentLevel.lineSegmentXLineSegmentCrossections.size(); i++) {currentLevel.lineSegmentXLineSegmentCrossections.get(i).update();}

                w.setImage(currentLevel.getFrame());
                if(currentLevel.reachedObjective()){
                    if(currentLevelID + 1 < levels.size()){currentLevel = levels.get(currentLevelID + 1); currentLevelID++;}
                }
            }
            if(System.currentTimeMillis() - start > 1000){
                start = System.currentTimeMillis();
                System.out.println("#".repeat(fps/100) + " ".repeat(35 - (fps/100)) + "|" + fps + " fps");
                fps = 0;
            }
        }



    }

    public static void serializeDataOut(Object ish, String fileName)throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(ish);
        oos.close();
    }

    public static Object serializeDataIn(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Object iHandler= (Object) ois.readObject();
        ois.close();
        return iHandler;
    }


}