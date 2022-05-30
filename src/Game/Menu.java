package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu {

    public int levelCount;
    int selectedLevel = 1;

    int[] LBC = new int[]{255, 230};
    int[] LBWH = new int[]{20, 20};

    int[] RBC = new int[]{300, 230};
    int[] RBWH = new int[]{20, 20};

    int[] SBC = new int[]{255, 255};
    int[] SBWH = new int[]{65, 30};

    public boolean levelChosen = false;

    public Menu(int levelCount){
        this.levelCount = levelCount;
    }

    int lastClickedCount = 0;

    public void update(){

        java.awt.Point p = Main.w.frame.getMousePosition();
        if(p != null) {
            if (Main.w.mouseDown & Main.w.clickCount != lastClickedCount) {

                p.y = p.y - 36;

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

        g2d.fillRect(LBC[0], LBC[1], LBWH[0], LBWH[1]); //left button
        g2d.fillRect(RBC[0], RBC[1], RBWH[0], RBWH[1]); //right button

        g2d.fillRect(SBC[0], SBC[1], SBWH[0], SBWH[1]);//select button

        return image;
    }

}
