package GameObject.Brick;

import Main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map1 {

    GamePanel gp;

    public List<Brick> list = new ArrayList<>();

    public Map1(GamePanel gp) {
        this.gp = gp;

        for (int i = 50; i <= 175; i += 25) {
            for (int j = 48; j <= 672; j += 48) {
                list.add(new BrLv1(gp, j, i));
            }
        }
    }

    public void render(Graphics2D g2) {
        for (Brick brick : list) {
            g2.drawImage(brick.image, (int) brick.posX, (int) brick.posY, brick.width, brick.height, null);
        }
    }
}
