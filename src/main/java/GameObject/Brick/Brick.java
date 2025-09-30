package GameObject.Brick;

import GameObject.GameObject;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick extends GameObject {

    GamePanel gp;
    int hitPoints, type;
    public BufferedImage image;

    public Brick(GamePanel gp) {
        this.gp = gp;

        width = gp.originalTileSize * 3;                //48
        height = gp.originalTileSize * 2 - 7;           //25
    }

    public void takeHit() {
        hitPoints --;
    }

    public boolean isDestroy() {
        if(hitPoints > 0) {
            return false;
        }
        return true;
    }
}
