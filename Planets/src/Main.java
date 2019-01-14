import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JPanel {

    static final float gravConst = 0.67f;
    static ArrayList<Body> bods = new ArrayList<>();
    static int ofset, screenSize = 1000;
    static int scalefactor = 1;

    public static final double DELTA_T = 1 / 30000.0;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.fillRect(0,0,screenSize,screenSize);

        for (Body b:bods) {
            g.setColor(b.getColour());
            g.fillOval((int)(b.getPos().getX() - b.getDiameter()/2)/scalefactor,(int)(b.getPos().getY()- b.getDiameter()/2)/scalefactor,b.getDiameter()/scalefactor,b.getDiameter()/scalefactor);
            if (b.getName() != "Sun" && b.getName()!= "Asteroid"){
                g.setColor(b.getColour().darker().darker().darker());
            g.drawOval((ofset/2+(int)(bods.get(0).getPos().getX() - b.getStartingPos())/2)/scalefactor,
                    (ofset/2+(int)(bods.get(0).getPos().getY() - b.getStartingPos())/2)/scalefactor,
                    b.getStartingPos()/scalefactor,b.getStartingPos()/scalefactor);
            }

        }
    }

    public static void main(String[] args){

        JFrame MainFrame = new JFrame("The Solar System");
        ofset = scalefactor * screenSize/2;
        MainFrame.setSize(screenSize,screenSize);
        MainFrame.setResizable(false);
        MainFrame.setLocationRelativeTo(null);



        Main panel = new Main();
        panel.SetupBods();

        MainFrame.add(panel);
        MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainFrame.setVisible(true);

        while(true){



            MainFrame.repaint();
            for (Body b: bods) {
                b.update(bods);
            }
        }
    }

    public void SetupBods(){
        Body sun = new Body("Sun", 2000000, 25, Color.white); //mass = 1.9891x10^30
        sun.setPos(ofset,ofset);
        bods.add(sun);

        Body mercury = new Body("Mercury", 0.3f,4,Color.lightGray);
        mercury.setPos(ofset,ofset-57);mercury.setStartingPos(104);
        mercury.setVel(150,0);
        bods.add(mercury);

        Body venus = new Body("Venus", 5, 8, Color.ORANGE);
        venus.setPos(ofset, ofset-108);venus.setStartingPos(216);
        venus.setVel(112f,0f);
        bods.add(venus);

        Body earth = new Body("Earth", 6,10, Color.green); // mass = 5.972x10^24  distance = 1.5x10^8
        earth.setPos(ofset,ofset-150); earth.setStartingPos(300);
        earth.setVel(96f,0f);
        bods.add(earth);

        Body mars = new Body("Mars", 0.6f, 10, Color.RED);
        mars.setPos(ofset,ofset-227); mars.setStartingPos(454);
        mars.setVel(77.5f,0);
        bods.add(mars);

        Body jupiter = new Body("Jupiter", 317*6 , 18,Color.magenta);
        jupiter.setPos(ofset, ofset - 779); jupiter.setStartingPos(1558);
        jupiter.setVel(41.5f,0);
        bods.add(jupiter);

        Body asteroid = new Body ("Asteroid", 0.01f ,10, Color.YELLOW);
        asteroid.setPos(ofset, ofset - 600);
        asteroid.setVel(35f,5f);
        bods.add(asteroid);

        Body asteroid2 = new Body ("Asteroid2", 0.0001f ,10, Color.WHITE);
        asteroid2.setPos(ofset, ofset - 81);
        asteroid2.setVel(145f,85f);
        bods.add(asteroid2);
    }

}