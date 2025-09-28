package GameObject.Brick;

import Main.GamePanel;

public class BrLv1 extends BrickManager {

    public BrLv1(GamePanel gp) {
        super(gp);

        type = 1;
        hitPoints = 1;

        posX = (double) (gp.screenWidth - width) / 2;
        posY = (double) gp.screenHeight / 3;
    }
}
