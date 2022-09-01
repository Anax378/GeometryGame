package Game;

import levelParts.Block;
import levelParts.Mover;
import levelParts.Orb;
import levelParts.Player;
import shapes.*;
import shapes.Point;
import shapes.Polygon;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Window w;

    public static Level currentLevel;
    public static int currentLevelID;

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
    public static List<CircleXLineCrossection> circleXLineCrossections= new ArrayList<>();
    public static List<CircleXLineSegmentCrossection> circleXLineSegmentCrossections = new ArrayList<>();
    public static List<CircleXCircleCrossection> circleXCircleCrossections = new ArrayList<>();
    public static List<LineParallel> lineParallels = new ArrayList<>();
    public static List<LinePerpendicular> linePerpendiculars = new ArrayList<>();
    public static List<Polygon> polygons = new ArrayList<>();
    public static List<Block> blocks = new ArrayList<>();
    public static List<Orb> orbs = new ArrayList<>();
    public static List<Mover> movers = new ArrayList<>();

    public static int ticksPerSecond = tps;
    public static int ticksPerSecondC = 0;

    public static float deathAltitude = 1000;

    public static boolean isInMenu = true;

    public static boolean isTimeToRestart = false;
    public static boolean isTimeToNext = false;
    public static boolean allowGreening = true;
    public static boolean interlock = false;

    public static boolean reachedObjective = false;

    public static float zoomModifier = 1.5f;
    public static float scrollSensitivity = -0.02f;


    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, URISyntaxException {


        w = new Window();

        Color blockColor = new Color(100 ,100, 100);
        Color characterColor = new Color(49, 157, 235);
        Color controllerColor = Color.red;
        Color orbColor = new Color(93, 241, 241);
        Color moverColor = new Color(238, 147, 147);

        int[] resolution = new int[]{w.width, w.height};



/*
        points.add(new Point(new Float[]{150f, 100f}, Color.YELLOW, 10)); //new Integer[]{150, 100}     //0
        points.add(new Point(new Float[]{300f, 200f}, Color.ORANGE, 10));//new Integer[]{300, 200}      //1
        points.add(new Point(new Float[]{250f, 200f}, Color.MAGENTA, 10));//new Integer[]{300, 200}     //2
        points.add(new Point(new Float[]{100f, 400f}, Color.BLACK, 10));                                //3
        points.add(new Point(new Float[]{400f, 400f}, Color.GRAY, 10));                                 //4
        points.add(new Point(new Float[]{0f, 0f}, Color.GRAY, 10));//5

        movers.add(new Mover(new Float[]{240f, 510f}, new Color(0, 87, 255, 255), 10));

        lineSegments.add(new LineSegment(player.position, points.get(0).position, Color.BLACK));                //0
        lineSegments.add(new LineSegment(points.get(3).position, points.get(4).position, Color.BLACK));         //1

        midPoints.add(new MidPoint(player.position, points.get(0).position, Color.RED, 10));

        circles.add(new Circle(midPoints.get(0).position, points.get(1).position, Color.RED));

        lines.add(new Line(player.position, points.get(2).position, Color.BLACK, new int []{w.width, w.height}));
        lines.add(new Line(points.get(0).position, points.get(1).position, Color.BLACK, new int []{w.width, w.height}));
        lines.add(new Line(midPoints.get(0).position, points.get(1).position, Color.BLACK, new int []{w.width, w.height}));

        lineXLineSegmentCrossections.add(new LineXLineSegmentCrossection(player.position, points.get(2).position,points.get(0).position,points.get(2).position, Color.GREEN, 10));

        lineSegmentXLineSegmentCrossections.add(new LineSegmentXLineSegmentCrossection(points.get(3).position, points.get(4).position, player.position, points.get(2).position, Color.BLACK, 10));

        blocks.add(new Block(new Float[]{0f, 590f}, new Float[]{100f, 600f}, new Color(200, 200, 200)));
        blocks.add(new Block(new Float[]{182f, 545f}, new Float[]{322f, 574f}, new Color(200, 200, 200)));
        blocks.add(new Block(new Float[]{310f, 500f}, new Float[]{350f, 574f}, new Color(200, 200, 200)));
        blocks.add(new Block(new Float[]{379f, 489f}, new Float[]{461f, 510f}, new Color(200, 200, 200)));
        blocks.add(new Block(new Float[]{0f, 595f}, new Float[]{600f, 630f}, new Color(100, 100, 100)));

        points.add(new Point(blocks.get(1).p1, new Color(200, 200, 200), 10));
        points.add(new Point(blocks.get(1).p2, new Color(100, 100, 100), 10));

        diameterCircles.add(new DiameterCircle(player.position, new Float[]{50f}, Color.BLACK));
        diameterCircles.add(new DiameterCircle(points.get(6).position, new Float[]{40f}, Color.GREEN));

        circleXCircleCrossections.add(new CircleXCircleCrossection(diameterCircles.get(0).center, diameterCircles.get(0).diameter, diameterCircles.get(1).center, diameterCircles.get(1).diameter,10, Color.BLUE));

        circleXLineCrossections.add(new CircleXLineCrossection(midPoints.get(0).position, player.position , player.position, diameterCircles.get(0).diameter,10, Color.GREEN));

        circleXLineSegmentCrossections.add(new CircleXLineSegmentCrossection(points.get(3).position, points.get(4).position, midPoints.get(0).position, circles.get(0).diameter, 10, Color.CYAN));

        lineParallels.add(new LineParallel(points.get(0).position, player.position, points.get(2).position, Color.GREEN, new int[]{w.width, w.height}));

        linePerpendiculars.add(new LinePerpendicular(points.get(3).position, points.get(4).position, midPoints.get(0).position, Color.BLUE, new int[]{w.width, w.height}));

        testingPoint = new Point(new Float[]{0f, 0f}, Color.GREEN, 20);
        points.add(testingPoint);

        polygons.add(new Polygon(new Float[][]{player.position, circleXLineSegmentCrossections.get(0).position1, midPoints.get(0).position, circleXLineSegmentCrossections.get(0).position2}, Color.GREEN));
        polygons.add(new Polygon(new Float[][]{points.get(3).position, points.get(4).position, midPoints.get(0).position}, Color.RED));

        orbs.add(new Orb(movers.get(0).position, Color.cyan, 35));
*/

        Player player = new Player(new Float[]{61.03695f, 414.02752f}, characterColor, 10);
        Level level1 = new Level(player){
            @Override
            public boolean reachedObjective() {
                if(diameterCircles.get(0).exists) {
                    if(player.position[0] == null | player.position[1] == null | diameterCircles.get(0).center[0] == null | diameterCircles.get(0).center[1] == null | diameterCircles.get(0).diameter[0] == null){return false;}
                    return isInCircle(new float[]{player.position[0], player.position[1]}, new float[]{diameterCircles.get(0).center[0], diameterCircles.get(0).center[1]}, diameterCircles.get(0).diameter[0]/2f);
                }
                return false;
            }
        };

        level1.add(new Block(new Float[]{31.25028f, 432.78209f}, new Float[]{465.91503f, 464.77518f}, blockColor));
        level1.add(new DiameterCircle(new Float[]{420.68342f, 374.31196f}, new Float[]{50f}, Color.green));
        level1.add(new Block(new Float[]{240.98355f, 394.86f},new Float[]{269.21425f, 453.3827f} , blockColor));

        Player player2 = new Player(new Float[]{137.21954f, 402.09832f}, characterColor, 10);

        Level level2 = new Level(player2){
            @Override
            public boolean reachedObjective() {
                if(diameterCircles.get(1).exists) {
                    if(player.position[0] == null | player.position[1] == null | diameterCircles.get(1).center[0] == null | diameterCircles.get(1).center[1] == null | diameterCircles.get(1).diameter[0] == null){return false;}
                    return isInCircle(new float[]{player.position[0], player.position[1]}, new float[]{diameterCircles.get(1).center[0], diameterCircles.get(1).center[1]}, diameterCircles.get(1).diameter[0]/2f);
                }
                return false;
            }
        };



        level2.add(new Block(new Float[]{23.83587f, 456.67625f}, new Float[]{472.4451f, 481.82251f}, blockColor));
        level2.add(new Point(new Float[]{325.23594f, 259.00052f}, Color.BLACK, 10)); //I
        level2.add(new Line(level2.player.position, level2.points.get(0).position, Color.BLACK, resolution));//q
        level2.add(new LinePerpendicular(level2.lines.get(0).dp1,level2.lines.get(0).dp2, level2.points.get(0).position, Color.BLACK, resolution));//r
        level2.add(new Point(new Float[]{335.50304f, 160.82131f}, Color.BLACK, 10));//J
        level2.add(new LinePerpendicular(level2.linePerpendiculars.get(0).p1, level2.linePerpendiculars.get(0).p2, level2.points.get(1).position, Color.BLACK, resolution));//s
        level2.add(new DiameterCircle(level2.points.get(1).position, new Float[]{400f}, Color.BLACK));//c
        level2.add(new CircleXLineCrossection(level2.linePerpendiculars.get(1).p1,level2.linePerpendiculars.get(1).p2, level2.diameterCircles.get(0).center, level2.diameterCircles.get(0).diameter,10 ,Color.BLACK,Color.BLACK));//K, L
        level2.add(new DiameterCircle(level2.circleXLineCrossections.get(0).position1, new Float[]{60f}, Color.GREEN));//d
        level2.add(new Block(new Float[]{200f, 300f}, new Float[]{250f, 450f}, blockColor));
        level2.add(new Block(new Float[]{120f, 420f}, new Float[]{220f, 440f}, blockColor));
        level2.add(new Block(new Float[]{240f, 420f}, new Float[]{340f, 440f}, blockColor));
        level2.add(new Orb(new Float[]{180f, 380f}, orbColor, 40));
        level2.add(new Orb(new Float[]{180f, 360f}, orbColor, 40));
        level2.add(new Orb(new Float[]{180f, 340f}, orbColor, 40));
        level2.add(new Orb(new Float[]{180f, 320f}, orbColor, 40));
        level2.add(new Orb(new Float[]{270f, 320f}, orbColor, 40));
        level2.add(new Orb(new Float[]{270f, 340f}, orbColor, 40));
        level2.add(new Orb(new Float[]{270f, 360f}, orbColor, 40));
        level2.add(new Orb(new Float[]{270F, 380f}, orbColor, 40));
        level2.add(new Orb(new Float[]{160f, 280f}, orbColor, 40));

        Player player3 = new Player(new Float[]{65.93962f, 444.57711f}, controllerColor, 10);
        Level level3 = new Level(player3){
            @Override
            public boolean reachedObjective() {
                if(diameterCircles.get(0).exists) {
                    if(circleXLineCrossections.get(0).position2[0] == null | circleXLineCrossections.get(0).position2[1] == null | diameterCircles.get(0).center[0] == null | diameterCircles.get(0).center[1] == null | diameterCircles.get(0).diameter[0] == null){return false;}
                    return isInCircle(new float[]{circleXLineCrossections.get(0).position2[0], circleXLineCrossections.get(0).position2[1]}, new float[]{diameterCircles.get(0).center[0], diameterCircles.get(0).center[1]}, diameterCircles.get(0).diameter[0]/2f);
                }
                return false;
            }
        };;

        level3.add(new Block(new Float[]{40f, 460f}, new Float[]{460f, 480f}, blockColor));
        level3.add(new Block(new Float[]{40f, 550f}, new Float[]{460f, 570f}, new Color(100 ,100, 100, 5)));
        level3.add(new Block(new Float[]{120f, 440f}, new Float[]{180f, 450f}, blockColor));
        level3.add(new Block(new Float[]{160f, 420f}, new Float[]{220f, 450f}, blockColor));
        level3.add(new Block(new Float[]{210f, 400f}, new Float[]{270f, 430f}, blockColor));
        level3.add(new Block(new Float[]{240f, 380f}, new Float[]{300f, 410f}, blockColor));
        level3.add(new Point(new Float[]{348.61925f, 445.42555f}, Color.BLACK, 10));//N
        level3.add(new Line(level3.player.position, level3.points.get(0).position,Color.BLACK ,resolution));//q
        level3.add(new LinePerpendicular(level3.lines.get(0).dp1, level3.lines.get(0).dp2, level3.points.get(0).position, Color.BLACK, resolution));//r
        level3.add(new Circle(level3.points.get(0).position, level3.player.position, Color.BLACK));//d
        level3.add(new CircleXLineCrossection(level3.linePerpendiculars.get(0).p1, level3.linePerpendiculars.get(0).p2, level3.circles.get(0).center, level3.circles.get(0).diameter, 10, Color.BLACK, characterColor)); // O, P
        level3.add(new DiameterCircle(new Float[]{408.80383f, 335.50203f}, new Float[]{70f}, Color.GREEN));

        Player player4 = new Player(new Float[]{69.1485f, 445.67892f}, controllerColor, 10);
        Level level4 = new Level(player4){
            @Override
            public boolean reachedObjective(){
                Float[] chPos = lineXLineSegmentCrossections.get(0).position;
                if(chPos[0] == null || chPos[1] == null || !polygons.get(0).exists){return false;}
                return polygons.get(0).isInRectangle(chPos[0], chPos[1]);

            }

        };

        level4.add(new Block(new Float[]{20f, 460f}, new Float[]{480f, 480f}, blockColor));
        level4.add(new Point(new Float[]{226.29488f, 265.10408f}, Color.BLACK, 10));//J
        level4.add(new MidPoint(level4.player.position, level4.points.get(0).position , Color.black, 10));//K
        level4.add(new Point(new Float[]{286.33088f, 169.00168f}, Color.BLACK, 10));//L
        level4.add(new LineSegment(level4.midPoints.get(0).position, level4.points.get(1).position, Color.BLACK));//n
        level4.add(new DiameterCircle(new Float[]{134.49935f, 169.79609f}, new Float[]{54.64545938330542f*2}, Color.BLACK));//d
        level4.add(new CircleXLineSegmentCrossection(level4.lineSegments.get(0).p1, level4.lineSegments.get(0).p2, level4.diameterCircles.get(0).center, level4.diameterCircles.get(0).diameter, 10, Color.BLACK, Color.BLACK));//O, N
        level4.add(new Point(new Float[]{390.64716f, 444.16668f}, Color.BLACK, 10));
        level4.add(new Point(new Float[]{438.28767f, 359.56371f}, Color.BLACK, 10));
        level4.add(new LineSegment(new Float[]{390.64716f, 444.16668f}, new Float[]{438.28767f, 359.56371f}, Color.BLACK));//r
        level4.add(new Line(level4.circleXLineSegmentCrossections.get(0).position1, level4.points.get(0).position, Color.BLACK, resolution));
        level4.add(new LineParallel(level4.lines.get(0).dp1, level4.lines.get(0).dp2, level4.points.get(1).position, Color.BLACK, resolution));//q
        level4.add(new LineXLineSegmentCrossection(level4.lineParallels.get(0).p1, level4.lineParallels.get(0).p2,level4.lineSegments.get(1).p1, level4.lineSegments.get(1).p2,  characterColor, 10));//Character
        level4.add(new Polygon(new Float[][]{new Float[]{356.97025f, 402.27589f}, new Float[]{473.60736f, 434.31002f}, new Float[]{412.00325f, 347.24289f}, new Float[]{463.7507f, 303.70932f}, new Float[]{326.5789f, 313.56598f},}, Color.GREEN));
        level4.add(new Block(new Float[]{130f, 430f}, new Float[]{200f, 440f}, blockColor));
        level4.add(new Block(new Float[]{240f, 390f}, new Float[]{290f, 400f}, blockColor));
        level4.add(new Block(new Float[]{280f, 370f}, new Float[]{310f, 400f}, blockColor));
        level4.add(new Block(new Float[]{300f, 310f}, new Float[]{350f, 380f}, blockColor));
        level4.add(new Block(new Float[]{10f, 280f}, new Float[]{290f, 290f}, blockColor));
        level4.add(new Orb(new Float[]{224.23919f, 406.58717f}, orbColor, 15*2));
        level4.add(new Orb(new Float[]{294.33704f, 335.89645f}, orbColor, 12*2));
        level4.add(new Orb(new Float[]{46.14474f, 219.85156f}, orbColor, 43*2));

        Player player5 = new Player(new Float[]{67.09871f, 428.67509f}, controllerColor, 10);
        Level level5 = new Level(player5){
            @Override
            public boolean reachedObjective(){
                Float[] chPos = lineXLineSegmentCrossections.get(0).position;
                if(chPos[0] == null || chPos[1] == null || diameterCircles.get(2).center[0] == null || diameterCircles.get(2).center[1] == null || diameterCircles.get(2).diameter[0] == null){return false;}else{
                    return isInCircle(new float[]{chPos[0], chPos[1]}, new float[]{diameterCircles.get(2).center[0], diameterCircles.get(2).center[1]}, diameterCircles.get(2).diameter[0]/2f);
                }
            }


        };

        level5.add(new Block(new Float[]{20f, 460f}, new Float[]{480f, 480f}, blockColor));
        level5.add(new DiameterCircle(new Float[]{400f, 450f},new Float[]{80f}, Color.BLACK));
        level5.add(new DiameterCircle(level5.player.position, new Float[]{47f*2f}, Color.BLACK));
        level5.add(new CircleXCircleCrossection(level5.diameterCircles.get(0).center, level5.diameterCircles.get(0).diameter, level5.diameterCircles.get(1).center, level5.diameterCircles.get(1).diameter,10 ,Color.BLACK, Color.BLACK));
        level5.add(new Line(level5.circleXCircleCrossections.get(0).position1, level5.circleXCircleCrossections.get(0).position2, Color.BLACK, resolution));
        level5.add(new Point(new Float[]{64.80194f, 244.54569f},Color.BLACK, 10));
        level5.add(new Point(new Float[]{445.79004f, 87.97524f},Color.BLACK, 10));
        level5.add(new LineSegment(level5.points.get(0).position, level5.points.get(1).position, Color.BLACK));
        level5.add(new LineXLineSegmentCrossection(level5.lines.get(0).dp1, level5.lines.get(0).dp2, level5.lineSegments.get(0).p1, level5.lineSegments.get(0).p2, characterColor, 10));
        level5.add(new Mover(new Float[]{56.18224f, 168.49919f}, moverColor, 10));
        level5.add(new Point(new Float[]{332.71138f, 59.27066f}, Color.BLACK, 10));
        level5.add(new LineSegment(level5.movers.get(0).position, level5.points.get(2).position, Color.BLACK));
        level5.add(new LineSegmentXLineSegmentCrossection(level5.lineSegments.get(0).p1, level5.lineSegments.get(0).p2, level5.lineSegments.get(1).p1, level5.lineSegments.get(1).p2, Color.BLACK, 10));
        level5.add(new DiameterCircle(level5.lineSegmentXLineSegmentCrossections.get(0).position, new Float[]{40f}, Color.GREEN));

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(level5);

        currentLevel = levels.get(0);
        currentLevelID = 0;


        int fps = 0;
        long start = System.currentTimeMillis();
        Menu menu = new Menu(levels.size());

        long startTime = System.currentTimeMillis();

        while(true) {       //  frame loop

            //while(w.mouseDown){;}

            fps++;



            if(w.isEscDown){isInMenu = true;}

            if(isInMenu){
                currentLevel.player.mouseInHitbox = false;

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
                    restartLevel(currentLevelID);
                }

                currentLevel.update();

                w.setImage(currentLevel.getFrame());


                if(System.currentTimeMillis() - startTime >= 1000/tps){
                    ticksPerSecondC++;
                    currentLevel.player.update();
                    startTime = System.currentTimeMillis();
                }
                reachedObjective = currentLevel.reachedObjective();

                if(reachedObjective || interlock){
                    interlock = true;
                    menu.markLevelAsCompleted(currentLevelID);
                    if(allowGreening){currentLevel.startGreenDisappearing = true;allowGreening = false;}
                    if(isTimeToNext){
                    if(currentLevelID == levels.size() - 1){isInMenu = true;}else{
                        currentLevel = levels.get(currentLevelID + 1);
                        menu.selectedLevel++;
                        currentLevelID++;

                    }
                        allowGreening = true;
                        interlock = false;
                    }
                    isTimeToNext = false;
                }

                if(isTimeToRestart){
                    reachedObjective = false;
                    restartLevel(currentLevelID);
                    isTimeToRestart = false;
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

    public static float getDistance(Float[] x, Float[] y){
        float a = x[0] - y[0];
        float b = x[1] - y[1];

        return (float) Math.sqrt(a*a + b*b);

    }

    public static void restartLevel(int id){
        levels.get(id).restart();
    }

}