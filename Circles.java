import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Random;

public class Circles extends JFrame{

    static int generations = 0;
    static int printedGenerations = 0;
    static double subHeight = 0;
    static double subWidth = 0;
    static double subRadius = 0;
    
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

        super.paint(g);
        printedGenerations = 0;
        int i;
        Random rand = new Random();
        float redRand = 0;
        float greenRand = 0;
        float blueRand = 0;
   
        double radius = 400;
        
        double parentWidth = 0;
        double parentHeight = 0;

        
        int width = getWidth();
        int height = getHeight();
        
        double centWidth = getWidth() / 2;
        double centHeight = getHeight() / 2;    
                 
        // Creates a random rgb combination for single circle generation
        redRand = rand.nextFloat();
        greenRand = rand.nextFloat();
        blueRand = rand.nextFloat();
        g.setColor(new Color(redRand, greenRand, blueRand));

        // Draws the first generation circle, and saves its values as a parentWidth and parentHeight
        if(generations >= 1){
            parentHeight = centHeight/2;
            parentWidth = centWidth/2;
            drawCircle(g, (int)parentWidth, (int)parentHeight, (int)radius, (int)radius);
            printedGenerations++;
           
        }
        if(generations > 1){
            //Draws all other circles requested from user input              
            parentHeight = (parentHeight + (int)radius)/3;
            parentWidth = (parentWidth + (int)radius)/3;
            radius = radius / 3;
            // Center
            drawCluster(g, parentHeight, parentWidth, radius);
            
        }
        
    }

    /*Draw circle using depth first methodology
      Draw parent
       Draw Child
       if child has a child -> Draw Child
        if child has a child -> draw Child
          etc
    */
    
    // Draws the 7 circles inside the parent circles values
    public void drawCluster(Graphics g, double parentHeight, double parentWidth, double radius){
        
        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        g.setColor(new Color(red, green, blue));
        System.out.println("Printed Generations :" + printedGenerations + " Generations :" + generations);
        // Center
        drawCircle(g, (int)(parentWidth + ((parentWidth/3)*2)), (int)(parentHeight + ((parentHeight/3)*2)), (int)radius, (int)radius);

        // If a generation should be drawn inside the center circle, then draw it now using the center circles values
        if(printedGenerations < generations){
            // Set current circles values as sub values, to be called within draw cluster
            subHeight = (int)(parentHeight + ((parentHeight/3)*2));
            subWidth = (int)(parentWidth + ((parentWidth/3)*2));
            subRadius = radius / 3;
            
            printedGenerations++;
            drawCluster(g, subWidth, subHeight, subRadius);
        }
        
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
        printedGenerations++;
    }
 
    public void drawCircle(Graphics g, int x, int y, int rx, int ry){
        g.fillOval(x, y, rx, ry);
    }

    
} 
