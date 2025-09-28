package GameObject;

import Main.GamePanel;

import java.awt.*;

public class Brick extends GameObject{

    GamePanel gp;

    public Brick(GamePanel gp) {
        this.gp = gp;

        width = gp.originalTileSize * 3;
        height = gp.originalTileSize * 2;

        initPos();
    }

    public void initPos() {
        posX = (gp.screenWidth - width) / 2;
        posY = gp.screenHeight / 3;
    }

    public void render(Graphics2D g2) {
        g2.setColor(Color.green);

        g2.fillRect(posX, posY, width, height);
    }
}
