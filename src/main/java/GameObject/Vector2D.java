package GameObject;

public class Vector2D {
    public double x, y;
    public int angle;


    public Vector2D() {
        x = 0;
        y = 0;
        angle = 90;
    }


    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
        updateAngle();
    }


    public void changeVal(int speed) {
        x = speed * Math.cos(Math.toRadians(angle));
        y = -speed * Math.sin(Math.toRadians(angle));
    }


    public void changeX() {
        x = -x;
        updateAngle();
    }


    public void changeY() {
        y = -y;
        updateAngle();
    }


    public void normalize() {
        double length = Math.sqrt(x * x + y * y);
        if (length != 0) {
            x /= length;
            y /= length;
        }
    }

    public void scale(double factor) {
        x *= factor;
        y *= factor;
    }

    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    private void updateAngle() {
        this.angle = (int) Math.toDegrees(Math.atan2(-y, x));
    }
}