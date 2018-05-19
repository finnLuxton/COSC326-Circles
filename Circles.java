import javax.swing.*;
import java.awt.*;

public class Circles extends JFrame{
    
    public static void main(String argv[]) {
        Circles c = new Circles();
    }
    
    public Circles() {
        setSize(800,800);
        setLocation(100,100);
        setVisible(true);
    }


    public void paint(Graphics g){
        int x = 50;
        int y = 50;
        g.setColor(Color.yellow);
        g.fillOval(x,y,100,100);
        g.fillOval(x+50,y+50,100,100);

    }


} 
