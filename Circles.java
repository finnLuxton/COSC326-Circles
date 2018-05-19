import javax.swing.*;
import java.awt.*;

public class Circles extends JFrame{
    
    public static void main(String argv[]) {
        Circles c = new Circles();
    }
    
    public Circles() {
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void paint(Graphics g){
        // Write some pseudocode down on paper whydontcha?
        
        // Clears it, right?
        super.paint(g);

        // Getting dimensions of window
        int width = getWidth();
        int height = getHeight();
        System.out.println("Width: " + width + " Height: " + height);
        // Getting center of window
        int centWidth = width / 2;
        int centHeight = height / 2;
        System.out.println("Center of screen = " + centWidth + "," + centHeight);
        
        // Make sure that color is the same for each generation
        g.setColor(Color);
        for(i = 0; i < 2; i++){
            circleCount = 
            drawCircle(g, centWidth, centHeight);
        }
    }

    public void drawCircle(Graphics g, int x, int y){
        //x, y, width, height
        //g.fillOval(x/2, y/2, 400, 400);

        g.fillOval(x/2, y/2, 400, 400);
    }

} 
