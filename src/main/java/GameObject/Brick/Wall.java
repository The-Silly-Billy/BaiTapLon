package GameObject.Brick;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wall extends Brick {

    public Wall(GamePanel gp, double posX, double posY) {
        super(gp);

        hitPoints = 0;
        isWall = true;

        this.posX = posX;
        this.posY = posY;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Brick/Wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
