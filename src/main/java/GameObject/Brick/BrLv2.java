package GameObject.Brick;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BrLv2 extends Brick {

    public BrLv2(GamePanel gp, double posX, double posY) {
        super(gp);

        hitPoints = 2;

        this.posX = posX;
        this.posY = posY;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Brick/BrLv2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
