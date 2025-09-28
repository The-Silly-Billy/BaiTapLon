package GameObject;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.util.Random;

public class Ball extends GameObject{

    GamePanel gp;
    KeyHandler keyH;

    Random rand = new Random();

    public int radius;
    public boolean isPlay;
    public int speed;
    int angle;
    public Vector2D move;

    public Ball(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        width = gp.originalTileSize;
        height = gp.originalTileSize;

        radius = width / 2;

        initPos();
    }

    public void initPos() {
        posX = (double) gp.screenWidth / 2;
        posY = (double) gp.screenHeight - 110;

        speed = 4;
        angle = rand.nextInt(61) + 60;

        move = new Vector2D();

        isPlay = false;
    }

    public void update() {
        if(!isPlay && keyH.spacePressed) {
            move.changeVal(speed, angle);
            isPlay = true;
        }

        posX += move.x;
        posY += move.y;

        if(posY - radius < 0) {
            posY = radius;
            move.changeY();
        }

        if(posX < radius) {
            posX = radius;
            move.changeX();
        }

        if(posX > gp.screenWidth - radius) {
            posX = gp.screenWidth - radius;
            move.changeX();
        }

        //Di chuyen trai phai luc bat dau
        if(!isPlay) {
            if(keyH.rightPressed) {
                posX += 4;
            }
            if(keyH.leftPressed) {
                posX -= 4;
            }
            if(posX < (double) (gp.originalTileSize * 4 - 4) / 2) {
                posX = (double) (gp.originalTileSize * 4 - 4) / 2;
            }
            if(posX > gp.screenWidth - (double) (gp.originalTileSize * 4 - 4) / 2) {
                posX = gp.screenWidth - (double) (gp.originalTileSize * 4 - 4) / 2;
            }
        }
    }

    public void render(Graphics2D g2) {
        g2.setColor(Color.yellow);

        g2.fillOval((int) (posX - radius), (int) (posY - radius), width, height);
    }

}
