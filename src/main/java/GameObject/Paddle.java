package GameObject;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;

public class Paddle extends GameObject {

    GamePanel gp;
    KeyHandler keyH;

    Vector2D pos;
    int speed;
    int width, height;

    public Paddle(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        this.width = gp.originalTileSize * 3;
        this.height = gp.originalTileSize;

        initPos();
    }

    public void initPos() {
        pos = new Vector2D((gp.screenWidth - width) / 2, gp.screenHeight - 100);
        speed = 4;
    }

    public void update() {
        if(keyH.rightPressed) {
            pos.x += speed;
        }
        if(keyH.leftPressed) {
            pos.x -= speed;
        }
    }

    public void render(Graphics2D g2) {

        g2.setColor(Color.white);

        g2.fillRect(pos.x,pos.y, width, height);
    }
    public void movement() {}
    public void powerUp() {
        //nhan suc manh = true
        // scale = 2;
    }

}
