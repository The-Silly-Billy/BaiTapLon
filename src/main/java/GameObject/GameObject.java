package GameObject;

public class GameObject {
    public double posX,posY;
    public int width, height;
    public int speed;

    public static boolean isCollide(GameObject obj1, GameObject obj2) {
        return  obj1.posX + obj1.width >= obj2.posX &&
                obj2.posX + obj2.width >= obj1.posX &&
                obj1.posY + obj1.height >= obj2.posY &&
                obj2.posY + obj2.height >= obj1.posY;
    }

    public static int typeCollideBnR(Ball ball, GameObject brick) {
        double closeX = ball.posX;
        double closeY = ball.posY;

        if(ball.posX >= brick.posX + brick.width) {
            closeX = brick.posX + brick.width;                  //phai (3)
        } else if(ball.posX + ball.width <= brick.posX) {
            closeX = brick.posX;                                //trai (1)
        }

        if(ball.posY + ball.height <= brick.posY) {
            closeY = brick.posY;                                //tren (2)
        } else if(ball.posY >= brick.posY + brick.height) {
            closeY = brick.posY + brick.height;                 //duoi (4)
        }

        double distX = closeX - ball.posX;
        double distY = closeY - ball.posY;

        if(isCollide(ball, brick)) {
            if(distX != 0 && distY != 0) {
                return 5;
            }

            if(distX == 0) {
                if(closeY == brick.posY) {
                    return 2;
                } else {
                    return 4;
                }
            }

            if(closeX == brick.posX) {
                return 1;
            } else {
                return 3;
            }
        }

        return 0;
    }
}
