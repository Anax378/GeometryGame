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
    public static List<LineXLineCrossection> lineXLineCrossections = new ArrayList<>();
    public static List<LineXLineSegmentCrossection> lineXLineSegmentCrossections = new ArrayList<>();
    public static List<LineSegmentXLineSegmentCrossection> lineSegmentXLineSegmentCrossections = new ArrayList<>();
    public static List<Level> levels = new ArrayList<>();
    public static List<Block> blocks = new ArrayList<>();
    public static List<CircleXLineCrossection> circleXLineCrossections= new ArrayList<>();
    public static  List<CircleXLineSegmentCrossection> circleXLineSegmentCrossections = new ArrayList<>();

    public static Player player;
    public static int ticksPerSecond = tps;
    public static int ticksPerSecondC = 0;


    public static void main(String[] args){


        w = new Window();

        player = new Player(new Float[]{200f, 500f}, new Color(49, 157, 235), 10);

        points.add(new Point(new Float[]{150f, 100f}, Color.YELLOW, 10)); //new Integer[]{150, 100}     //0
        points.add(new Point(new Float[]{300f, 200f}, Color.ORANGE, 10));//new Integer[]{300, 200}      //1
        points.add(new Point(new Float[]{250f, 200f}, Color.MAGENTA, 10));//new Integer[]{300, 200}     //2
        points.add(new Point(new Float[]{100f, 400f}, Color.BLACK, 10));                                //3
        points.add(new Point(new Float[]{400f, 400f}, Color.GRAY, 10));                                 //4
        points.add(new Point(new Float[]{0f, 0f}, Color.GRAY, 10));                                 //5
        lineSegments.add(new LineSegment(player.position, points.get(0).position, Color.BLACK));
        lineSegments.add(new LineSegment(points.get(3).position, points.get(4).position, Color.BLACK));
        midPoints.add(new MidPoint(player.position, points.get(0).position, Color.RED, 10));
        circles.add(new Circle(midPoints.get(0).position, points.get(1).position, Color.RED));
        diameterCircles.add(new DiameterCircle(player.position, new Float[]{50f}, Color.BLACK));
        lines.add(new Line(player.position, points.get(2).position, Color.BLACK, new int []{w.width, w.height}));
        lines.add(new Line(points.get(0).position, points.get(1).position, Color.BLACK, new int []{w.width, w.height}));
        lines.add(new Line(midPoints.get(0).position, points.get(1).position, Color.BLACK, new int []{w.width, w.height}));
        lineXLineSegmentCrossections.add(new LineXLineSegmentCrossection(player.position, points.get(2).position,points.get(0).position,points.get(2).position, Color.GREEN, 10));
        lineSegmentXLineSegmentCrossections.add(new LineSegmentXLineSegmentCrossection(points.get(3).position, points.get(4).position, player.position, points.get(2).position, Color.BLACK, 10));


        blocks.add(new Block(new Float[]{0f, 590f}, new Float[]{100f, 600f}, new Color(200, 200, 200)));
        blocks.add(new Block(new Float[]{182f, 545f}, new Float[]{322f, 574f}, new Color(200, 200, 200)));
        blocks.add(new Block(new Float[]{310f, 500f}, new Float[]{350f, 574f}, new Color(200, 200, 200)));
        blocks.add(new Block(new Float[]{379f, 489f}, new Float[]{461f, 510f}, new Color(200, 200, 200)));

        points.add(new Point(blocks.get(1).p1, new Color(200, 200, 200), 10));
        points.add(new Point(blocks.get(1).p2, new Color(100, 100, 100), 10));

        diameterCircles.add(new DiameterCircle(points.get(6).position, new Float[]{40f}, Color.GREEN));

        circleXLineCrossections.add(new CircleXLineCrossection(midPoints.get(0).position, player.position , player.position, diameterCircles.get(0).diameter,10, Color.GREEN));

        circleXLineSegmentCrossections.add(new CircleXLineSegmentCrossection(points.get(3).position, points.get(4).position, midPoints.get(0).position, circles.get(0).diameter, 10, Color.CYAN));

        Level level1 = new Level(
                points,
                lineSegments,
                midPoints,
                circles,
                diameterCircles,
                lines,
                lineXLineCrossections,
                lineXLineSegmentCrossections,
                lineSegmentXLineSegmentCrossections,
                blocks,
                circleXLineCrossections,
                circleXLineSegmentCrossections,
                player){
            @Override
            public boolean reachedObjective() {
                if(diameterCircles.get(1).exists) {
                    return isInCircle(new float[]{player.position[0], player.position[1]}, new float[]{diameterCircles.get(1).center[0], diameterCircles.get(1).center[1]}, diameterCircles.get(1).diameter[0]);
                }
                return false;
            }
        };

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
        boolean won = false;
        boolean isInMenu = true;
        Menu menu = new Menu(levels.size());

        long startTime = System.currentTimeMillis();

        while(true) {       //  frame loop

            //while(w.mouseDown){;}

            fps++;

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

                currentLevel.update();

                w.setImage(currentLevel.getFrame());


                if(System.currentTimeMillis() - startTime >= 1000/tps){
                    ticksPerSecondC++;
                    player.update();
                    startTime = System.currentTimeMillis();
                }

                if(currentLevel.reachedObjective()){
                    if(currentLevelID + 1 < levels.size()){currentLevel = levels.get(currentLevelID + 1); currentLevelID++;
                    }else{
                        //isInMenu = true;
                    }
                    //levels.set(currentLevelID, (Level)serializeDataIn("level" + currentLevelID));
                }



            }
            if(System.currentTimeMillis() - start > 1000){
                start = System.currentTimeMillis();
                System.out.println("#".repeat(fps/100) + " ".repeat(35 - (fps/100)) + "|" + fps + " fps \t |  " + ticksPerSecondC + " tps");
                ticksPerSecond = ticksPerSecondC;
                ticksPerSecondC = 0;

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
        Object iHandler = ois.readObject();
        ois.close();
        return iHandler;
    }


}