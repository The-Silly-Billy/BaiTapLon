package GameObject;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;

public class Ball extends GameObject{

    GamePanel gp;
    KeyHandler keyH;

    int radius;
    public int speedX, speedY;
    public boolean isPlay;

    public Ball(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        width = gp.originalTileSize;
        height = gp.originalTileSize;

        radius = width / 2;

        initPos();
    }

    public void initPos() {
        posX = gp.screenWidth / 2;
        posY = gp.screenHeight - 110;

        speedX = speedY = 0;
        isPlay = false;
    }

    public void update() {
        if(!isPlay && keyH.spacePressed) {
            speedX = -2;
            speedY = -4;
            isPlay = true;
        }

        posX += speedX;
        posY += speedY;

        if(posY - radius < 0) {
            posY = radius;
            speedY = -speedY;
        }

        if(posX < radius) {
            posX = radius;
            speedX = -speedX;
        }

        if(posX > gp.screenWidth - radius) {
            posX = gp.screenWidth - radius;
            speedX = -speedX;
        }

        if(posY > gp.screenHeight - radius) {
            initPos();
        }

        if(!isPlay) {
            if(keyH.rightPressed) {
                posX += 4;
            }
            if(keyH.leftPressed) {
                posX -= 4;
            }
            if(posX < (gp.originalTileSize * 4 - 4) / 2) {
                posX = (gp.originalTileSize * 4 - 4) / 2;
            }
            if(posX > gp.screenWidth - (gp.originalTileSize * 4 - 4) / 2) {
                posX = gp.screenWidth - (gp.originalTileSize * 4 - 4) / 2;
            }
        }
    }

    public void render(Graphics2D g2) {
        g2.setColor(Color.yellow);

        g2.fillOval(posX - radius, posY - radius, width, height);
    }

}
