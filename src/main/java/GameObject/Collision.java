package GameObject;

public class Collision {
    public static boolean VaCham(GameObject objA, GameObject objB) {
        if (objA.getX() == objB.getY() && objA.getX() + objA.getW() >= objB.getX() && objB.getX() + objB.getW() >= objA.getX()) {
            return true;
        }
        return false;
    }
}
