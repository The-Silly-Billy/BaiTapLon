package GameObject;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Ball extends GameObject{

    GamePanel gp;
    KeyHandler keyH;
    BufferedImage image;

    Random rand = new Random();

    public int radius;
    public boolean isPlay;
    public int speed;
    public Vector2D move;
    public int ballDamage;

    public Ball(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        width = gp.originalTileSize;
        height = gp.originalTileSize;

        radius = width / 2;

        ballDamage = 1;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Ball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        initPos();
    }

    public void initPos() {
        posX = (double) gp.screenWidth / 2;
        posY = (double) gp.screenHeight - 66 - 8;

        speed = 8;

        move = new Vector2D();

//        move.angle = rand.nextInt(90 - 60 + 1) + 60;

        isPlay = false;
    }

    public void update() {
        if(!isPlay && keyH.spacePressed) {
            move.changeVal(speed);
            isPlay = true;
        }

        posX += move.x;
        posY += move.y;
        //neu bong ra khoi man hinh ben tren
        if((int) (posY - radius) < 0) {
            posY = radius;
            move.changeY();
        }
       //neu bong ra khoi man hinh ben trai
        if((int) (posX - radius) < 0) {
            posX = radius;
            move.changeX();
        }
        //neu bong ra khoi man hinh ben phai
        if((int) posX > gp.screenWidth - radius) {
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
            //chua hieu
            if(posX < (double) (gp.originalTileSize * 5 - 10) / 2) {
                posX = (double) (gp.originalTileSize * 5 - 10) / 2;
            }
            if(posX > gp.screenWidth - (double) (gp.originalTileSize * 5 - 10) / 2) {
                posX = gp.screenWidth - (double) (gp.originalTileSize * 5 - 10) / 2;
            }
        }
    }

    public void render(Graphics2D g2) {
        g2.drawImage(image, (int) posX - radius, (int) posY - radius, width, height, null);
    }

}
