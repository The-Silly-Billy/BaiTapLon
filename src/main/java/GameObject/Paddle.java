package GameObject;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;

public class Paddle extends GameObject {

    GamePanel gp;
    KeyHandler keyH;

    public Paddle(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        width = gp.originalTileSize * 4 - 4;
        height = gp.originalTileSize - 6;

        initPos();
    }

    public void initPos() {
        posX = (gp.screenWidth - width) / 2;
        posY = gp.screenHeight - 100;

        speed = 4;
    }

    public void update() {
        if(keyH.rightPressed) {
            posX += speed;
        }
        if(keyH.leftPressed) {
            posX -= speed;
        }
        if(posX < 0) {
            posX = 0;
        }
        if(posX > gp.screenWidth - width) {
            posX = gp.screenWidth - width;
        }
    }

    public void render(Graphics2D g2) {

        g2.setColor(Color.white);

        g2.fillRect(posX, posY, width, height);
    }

}
