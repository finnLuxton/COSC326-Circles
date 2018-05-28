import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Circles extends JFrame{

    static int generations = 0;
    static int printedGenerations = 0;
    static double subXY = 0;
    static double subRadius = 0;
    static int[][] listOfColors = new int[5][3];

    public static void main(String argv[]) {

        Scanner scanner = new Scanner(System.in);
        printedGenerations = 0;
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
        generateColours();
        printedGenerations = 0;
   
        double radius = 200;
        double parentXY = 0;
        double testX = 0;
        double testY = 0;
        // Since width and height will always be uniform, use of getWidth() and getHeight() are the same
        double centDistance = getWidth() / 2;   
                 
        // Creates a random rgb combination for single circle generation
        g.setColor(new Color(listOfColors[printedGenerations][0], listOfColors[printedGenerations][1], listOfColors[printedGenerations][2]));

        // Draws the first generation circle, and saves its values as a parentWidth and parentHeight
        if(generations >= 1){
            parentXY = centDistance/2;
            System.out.println((int)(getWidth() - radius) + " " + (int)(getHeight() - radius));
            drawCircle(g, (int)(getWidth() - radius), (int)(getHeight() - radius), (int)radius, (int)radius);
            printedGenerations++;
        }
        // If any generations are required afte the first, draw them
        if(generations > 1){
            //Draws all other circles requested from user input              
            parentXY = (parentXY + (int)radius)/3;

            radius = radius / 3;
            g.setColor(new Color(listOfColors[printedGenerations][0], listOfColors[printedGenerations][1], listOfColors[printedGenerations][2]));
            // Center
            drawCluster(g, parentXY, radius);           
        }

        System.out.println(printedGenerations);
    }
    
    // Draws the 7 circles inside the parent circles values
    public void drawCluster(Graphics g, double parentXY, double radius){
        
        g.setColor(new Color(listOfColors[printedGenerations][0], listOfColors[printedGenerations][1], listOfColors[printedGenerations][2]));
        
        // Center
        drawCircle(g, (int)(parentXY + ((parentXY/3)*2)), (int)(parentXY + ((parentXY/3)*2)), (int)radius, (int)radius);      
        // Left
        drawCircle(g, (int)parentXY, (int)(parentXY + ((parentXY/3)*2)), (int)radius, (int)radius);
        // Right
        drawCircle(g, (int)(parentXY + ((parentXY/3)*4)), (int)(parentXY + ((parentXY/3)*2)), (int)radius, (int)radius);
        // Top Left
        drawCircle(g, (int)(parentXY + ((parentXY/3))), (int)(parentXY + ((parentXY/3)/4)), (int)radius, (int)radius);
        // Top Right
        drawCircle(g, (int)(parentXY + ((parentXY/3)*3)), (int)(parentXY + ((parentXY/3)/4)), (int)radius, (int)radius);
        // Bottom Left
        drawCircle(g, (int)(parentXY + ((parentXY/3))), (int)((parentXY*2)+(parentXY/4)), (int)radius, (int)radius);
        // Bottom Right
        drawCircle(g, (int)(parentXY + ((parentXY/3)*3)), (int)((parentXY*2)+(parentXY/4)), (int)radius, (int)radius);
        printedGenerations++;
        if(printedGenerations < generations){
            System.out.println("I want to print more!");
            drawCluster(g, (parentXY + (int)radius)/3, radius/3);
        }
    }
 
    public void drawCircle(Graphics g, int x, int y, int rx, int ry){
        g.fillOval(x, y, rx, ry);
    }

    public void generateColours(){
        Random rand = new Random();
        int red = 0;
        int green = 0;
        int blue = 0;
        int[] randColor = new int[3];
        
        for(int i = 0; i < generations+1; i++){

            red = rand.nextInt(255);
            green = rand.nextInt(255);
            blue = rand.nextInt(255);
            randColor[0] = red;
            randColor[1] = green;
            randColor[2] = blue;

            listOfColors[i][0] = randColor[0];
            listOfColors[i][1] = randColor[1];
            listOfColors[i][2] = randColor[2];

        }

    }
    // #######################
    // ##   LEGACY ZONE!    ##
    // #######################

    //if(printedGenerations < generations){
        //   subXY = (parentXY + (int)radius)/3;
        //   subRadius = radius / 3;          
        //   printedGenerations++;
        //   drawCluster(g, subXY, subRadius);
    //}


} 
