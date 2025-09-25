package GameObject;

public class GameObject {
    private float x,y;                              // Vi tri tren man hinh
    private int width, height;                    // Kich co vat the

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getW() {
        return width;
    }

    public int getH() {
        return height;
    }

    public static void update() {}
    public static void render() {}
}
