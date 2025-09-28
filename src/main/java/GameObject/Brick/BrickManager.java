package GameObject.Brick;

import GameObject.GameObject;
import Main.GamePanel;

import java.awt.*;

public class BrickManager extends GameObject {

    GamePanel gp;
    int hitPoints, type;

    public BrickManager(GamePanel gp) {
        this.gp = gp;

        width = gp.originalTileSize * 3;
        height = gp.originalTileSize * 2;
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

    public void render(Graphics2D g2) {
        g2.setColor(Color.red);

        g2.fillRect((int) posX, (int) posY, width, height);
    }
}
