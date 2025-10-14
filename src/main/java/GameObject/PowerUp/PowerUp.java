package GameObject.PowerUp;

import GameObject.GameObject;
import Main.GamePanel;

import java.awt.image.BufferedImage;

public class PowerUp extends GameObject {

    GamePanel gp;
    public BufferedImage image;
    int duration;   //tgian hieu luc
    boolean isAppear;
    public int index;

//        ExpandPad,
//        FireBall,     index = 0
//        Triple,
//        PlusLife,
//        ShrinkPad,
//        SlowPad

    public PowerUp(GamePanel gp) {
        this.gp = gp;
        this.speed = 3;
        isAppear = true;
    }

    public void update() {
        while(isAppear) {
            posY -= speed;
            if(posY >= gp.screenHeight) {
                isAppear = false;
            }
        }
    }

    public boolean isEffect() {
        if(duration >= 0) {
            duration--;
            return true;
        }
        return false;
    }
}
