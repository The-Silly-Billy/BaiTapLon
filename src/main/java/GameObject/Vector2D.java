package GameObject;

public class Vector2D {

    public double x,y;
    public int angle;

    public Vector2D() {
        x = 0;
        y = 0;
        angle = 90;
    }

    public void changeVal(int speed) {
        x = speed * Math.cos(Math.toRadians(angle));
        y = - speed * Math.sin(Math.toRadians(angle));
    }

    public void changeX() {
        x = -x;
    }

    public void changeY() {
        y = -y;
    }
}
