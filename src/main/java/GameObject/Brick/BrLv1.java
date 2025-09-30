package GameObject.Brick;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BrLv1 extends Brick {

    public BrLv1(GamePanel gp, double posX, double posY) {
        super(gp);

        type = 1;
        hitPoints = 1;

        this.posX = posX;
        this.posY = posY;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Brick/BrLv1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
