import java.awt.*;
import java.util.ArrayList;


public class Body {

    private int startingPos;
    private String name;
    private float mass;
    private int diameter;
    private Color colour;

    private Vect pos;
    private Vect vel= new Vect(0,0);
    private Vect force = new Vect(0,0);

    public Body(String name, float mass, int diameter, Color colour){
        this.name = name;
        this.mass = mass;
        this.diameter = diameter;
        this.colour = colour;
    }

    public void setStartingPos(int startingPos) {
        this.startingPos = startingPos;
    }

    public int getStartingPos() {
        return startingPos;
    }

    public void setPos(Vect pos) {
        this.pos = pos;
    }

    public void setPos(float x, float y){
        setPos(new Vect(x,y));
    }

    public Vect getPos() {
        return pos;
    }

    public void setVel(Vect vel) {
        this.vel = vel;
    }

    public void setVel(float x, float y) {
        setVel(new Vect(x,y));
    }

    public int getDiameter() {
        return diameter;
    }

    public float getMass() {
        return mass;
    }

    public String getName() {
        return name;
    }

    public Color getColour() {
        return colour;
    }

    public Vect calculateForce(Body b){
        float fx, fy ,dx, dy, force, d;

        dx = b.getPos().getX() - pos.getX();
        dy = b.getPos().getY() - pos.getY();

        d = (dx*dx + dy*dy);
        double angle = Math.atan(dy/dx);

        force = Main.gravConst * getMass() * b.getMass() / d;

        fx = dx != 0 ? (float) (force * Math.cos(angle)) * (dx/Math.abs(dx)) : 0;
        fy = dy != 0 ? (float) (force * Math.sin(angle)) : 0;

        if (dx < 0)
            fy *=-1;

        return new Vect(fx,fy);
    }

    public void update(ArrayList<Body> otherBods){
        float sx, sy, vx, vy;
        force = new Vect(0,0);

        for (Body b:otherBods) {
            if (b == this)
                continue;
            force = force.add(calculateForce(b));
        }

        vx = vel.getX() + (float) ((force.getX() / mass * Main.DELTA_T));
        vy = vel.getY() + (float) ((force.getY() / mass * Main.DELTA_T));

        double dx,dy;
        dx = (vx * Main.DELTA_T) + force.getX() / mass * Main.DELTA_T * Main.DELTA_T;
        dy = (vy * Main.DELTA_T) + force.getY() / mass * Main.DELTA_T * Main.DELTA_T;

        sx = (float) (pos.getX() + dx);
        sy = (float) (pos.getY() + dy);

        setPos(sx,sy);
        setVel(vx,vy);
    }
}
