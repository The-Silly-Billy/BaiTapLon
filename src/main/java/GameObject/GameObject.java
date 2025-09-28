package GameObject;

import java.awt.*;

public class GameObject {
    public int posX,posY;
    public int width, height;
    int speed;

    public static int isCollide(GameObject cir, GameObject rec) {
        int closeX = cir.posX;
        int closeY = cir.posY;
        int r = cir.width / 2;

        //Xem xem ball nam o ben nao so voi gach/paddle
        if(cir.posX > rec.posX + rec.width) {
            closeX = rec.posX + rec.width;          //ben phai (3)
        } else if (cir.posX < rec.posX) {
            closeX = rec.posX;                      //ben trai (1)
        }

        if(cir.posY > rec.posY + rec.height) {
            closeY = rec.posY + rec.height;         //ben duoi (4)
        } else if (cir.posY < rec.posY) {
            closeY = rec.posY;                      //ben tren (2)
        }

        //Tinh khoang cach tu tam qua bong den canh gan nhat cua gach/paddle
        int distX = closeX - cir.posX;
        int distY = closeY - cir.posY;
        int distance = (distX * distX) + (distY * distY);

        if(distance <= r * r) {
            if(distY == 0) {
                if(closeX - rec.posX == 0) {
                    return 1;
                } else {
                    return 3;
                }
            } else {
                if(closeY - rec.posY == 0) {
                    return 2;
                } else {
                    return 4;
                }
            }
        }

        return 0;
    }
}
