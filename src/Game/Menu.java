package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public int levelCount;
    public boolean[] completedLevels;
    int selectedLevel = 1;

    int[] LBC = new int[]{255, 230};
    int[] LBWH = new int[]{20, 20};

    int[] RBC = new int[]{300, 230};
    int[] RBWH = new int[]{20, 20};

    int[] SBC = new int[]{255, 255};
    int[] SBWH = new int[]{65, 30};

    public boolean levelChosen = false;

    public Menu(int levelCount) throws IOException {
        this.levelCount = levelCount;

        File progressFile = new File("progress.txt");
        if(progressFile.createNewFile()){
            FileWriter fileWriter = new FileWriter("progress.txt");
            fileWriter.write("0");
            fileWriter.close();
        }
        else{
            Scanner scanner = new Scanner(progressFile);
            String binString = scanner.nextLine();
            completedLevels = new boolean[binString.length()];
            for(int i = 0; i < binString.length(); i++){
                if(binString.charAt(i) == '1'){completedLevels[i] = true;}else{completedLevels[i] = false;}
            }
        }

    }

    int lastClickedCount = 0;

    public void update(){

        java.awt.Point p = Main.w.label.getMousePosition();
        if(p != null) {
            if (Main.w.mouseDown & Main.w.clickCount != lastClickedCount) {

                //p.y = p.y - 36;

                if (p.x < LBC[0]+LBWH[0] & p.x > LBC[0] & p.y > LBC[1] & p.y < LBWH[1]+LBC[1]) {if(selectedLevel > 1){selectedLevel--;}}
                if (p.x < RBC[0]+RBWH[0] & p.x > RBC[0] & p.y > RBC[1] & p.y < RBWH[1]+RBC[1]) {if(selectedLevel < levelCount){selectedLevel++;}}
                if (p.x < SBC[0]+SBWH[0] & p.x > SBC[0] & p.y > SBC[1] & p.y < SBWH[1]+SBC[1]) {levelChosen = true;}

            }
            lastClickedCount = Main.w.clickCount;

        }

    }

    public BufferedImage getFrame(){
        BufferedImage image = new BufferedImage(Main.w.width, Main.w.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setPaint(Color.WHITE);
        g2d.fillRect(0, 0, Main.w.width, Main.w.height);

        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g2d.drawString("Menu", 230, 70);

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g2d.drawString(String.valueOf(selectedLevel), 280, 250);

        if(completedLevels[selectedLevel - 1]){g2d.setPaint(Color.green);}

        g2d.fillPolygon(new int[]{LBC[0], LBC[0]+LBWH[0], LBC[0]+LBWH[0]}, new int[]{LBC[1] + (LBWH[1]/2), LBC[1], LBC[1]+LBWH[1]}, 3);
        g2d.fillPolygon(new int[]{RBC[0], RBC[0], RBC[0]+RBWH[0]}, new int[]{RBC[1], RBC[1]+RBWH[1], RBC[1]+(RBWH[1]/2)}, 3);

        g2d.setPaint(Color.black);

        g2d.fillRect(SBC[0], SBC[1], SBWH[0], SBWH[1]);//select button

        return image;
    }
    public void markLevelAsCompleted(int id) throws IOException {
        if(id >= completedLevels.length){return;}
        completedLevels[id] = true;
        String binString = "";
        for(int i = 0; i < completedLevels.length; i++){
            if(completedLevels[i]){binString = binString + "1";}else{binString = binString + "0";}
        }
        FileWriter fileWriter = new FileWriter("progress.txt");
        fileWriter.write(binString);
        fileWriter.close();

    }

}
