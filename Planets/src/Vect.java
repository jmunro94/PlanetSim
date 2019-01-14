public class Vect {

    private float x,y;

    public Vect(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public Vect add(Vect a){
        return new Vect(x+a.x,y+a.y);
    }

}
