package GameObject;

//Xu ly van de lien quan den (x,y)

public class Vector2D {
    public float x,y;

    public Vector2D() {
        x = 0;
        y = 0;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D Zero() {
        this.x = 0;
        this.y = 0;

        return this;
    }
}
