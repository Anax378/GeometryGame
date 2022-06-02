package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Window {

    public volatile boolean mouseDown = false;
    volatile boolean isEscDown = false;
    volatile boolean isRDown = false;

    public volatile boolean isSpaceDown = false;
    public volatile boolean isADown = false;
    public volatile boolean isDDown = false;

    public volatile int clickCount = 0;

    public JFrame frame;
    JPanel panel;
    JLabel label;
    Icon icon;
    BufferedImage image;
    public int width;
    public int height;

    Window(){
        width = 600;
        height = 600;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        icon = new ImageIcon(image);

        frame = new JFrame();
        panel = new JPanel();
        label = new JLabel(icon);

        panel.add(label);

        frame.add(panel);
        frame.setSize(width + 12, height + 42);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {

                    mouseDown = true;
                    clickCount++;

                    Point mousePosition = frame.getMousePosition();
/*
                    mousePosition.y = mousePosition.y - 36;
                    int a = Math.abs(mousePosition.y - Main.currentLevel.points.get(0).position[1]);
                    int b = Math.abs(mousePosition.x - Main.currentLevel.points.get(0).position[0]);
                    boolean isInHitbox = Math.sqrt(a * a + b * b) < Main.currentLevel.points.get(0).diameter;
                    if(isInHitbox){mouseDownInHitbox = true;}
*/


                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mouseDown = false;
                }
            }



        });

        frame.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){isEscDown = true;}
                if(e.getKeyCode() == KeyEvent.VK_R){isRDown = true;}
                if(e.getKeyCode() == KeyEvent.VK_SPACE){isSpaceDown = true;}
                if(e.getKeyCode() == KeyEvent.VK_A){isADown = true;}
                if(e.getKeyCode() == KeyEvent.VK_D){isDDown = true;}
            }
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){isEscDown = false;}
                if(e.getKeyCode() == KeyEvent.VK_R){isRDown = false;}
                if(e.getKeyCode() == KeyEvent.VK_SPACE){isSpaceDown = false;}
                if(e.getKeyCode() == KeyEvent.VK_A){isADown = false;}
                if(e.getKeyCode() == KeyEvent.VK_D){isDDown = false;}

            }
        });




    }

    void setImage(BufferedImage image){
        Icon icon = new ImageIcon(image);
        label.setIcon(icon);
        SwingUtilities.updateComponentTreeUI(frame);
    }
}