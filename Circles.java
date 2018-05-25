import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Random;

public class Circles extends JFrame{

    static int generations = 0;
    static int printedGenerations = 0;
    public static void main(String argv[]) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter generations");
        generations = scanner.nextInt();
        scanner.close();
        
        Circles c = new Circles();


    }
    
    public Circles() {
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void paint(Graphics g){

        // Clears it, right?
        super.paint(g);
        int i;
        Random rand = new Random();
        float redRand = 0;
        float greenRand = 0;
        float blueRand = 0;
        // Radius
        double radius = 400;
        
        // This is bad
        double parentWidth = 0;
        double parentHeight = 0;
        
        // Getting dimensions of window
        int width = getWidth();
        int height = getHeight();
        
        // Getting center of window
        double centWidth = getWidth() / 2;
        double centHeight = getHeight() / 2;    
        
        // Currently only accepts up to 2 generations
        
        for(i = 1; i <= generations; i++){
            // Creates a random rgb combination for single circle generation
            redRand = rand.nextFloat();
            greenRand = rand.nextFloat();
            blueRand = rand.nextFloat();
            g.setColor(new Color(redRand, greenRand, blueRand));
            
            if(i == 1){
                parentHeight = centHeight/2;
                parentWidth = centWidth/2;
                drawCircle(g, (int)parentWidth, (int)parentHeight, (int)radius, (int)radius);
                printedGenerations++;
            }else{
                parentHeight = (parentHeight + (int)radius)/3;
                parentWidth = (parentWidth + (int)radius)/3;
                radius = radius / 3;
                // Center
                drawCluster(g, parentHeight, parentWidth, radius);
                printedGenerations++;
            }


        }
        System.out.println(printedGenerations);
    }


    
    // Draws the 7 circles inside the parent circles values
    public void drawCluster(Graphics g, double parentHeight, double parentWidth, double radius){
        // Center
        drawCircle(g, (int)(parentWidth + ((parentWidth/3)*2)), (int)(parentHeight + ((parentHeight/3)*2)), (int)radius, (int)radius);
        // Left
        drawCircle(g, (int)parentWidth, (int)(parentHeight + ((parentHeight/3)*2)), (int)radius, (int)radius);
        // Right
        drawCircle(g, (int)(parentWidth + ((parentWidth/3)*4)), (int)(parentHeight + ((parentHeight/3)*2)), (int)radius, (int)radius);
        // Top Left
        drawCircle(g, (int)(parentWidth + ((parentWidth/3))), (int)(parentHeight + ((parentHeight/3)/4)), (int)radius, (int)radius);
        // Top Right
        drawCircle(g, (int)(parentWidth + ((parentWidth/3)*3)), (int)(parentHeight + ((parentHeight/3)/4)), (int)radius, (int)radius);
        // Bottom Left
        drawCircle(g, (int)(parentWidth + ((parentWidth/3))), (int)((parentHeight*2)+(parentHeight/4)), (int)radius, (int)radius);
        // Bottom Right
        drawCircle(g, (int)(parentWidth + ((parentWidth/3)*3)), (int)((parentHeight*2)+(parentHeight/4)), (int)radius, (int)radius);
    }


    
    public void drawCircle(Graphics g, int x, int y, int rx, int ry){
        g.fillOval(x, y, rx, ry);
    }

    
} 
