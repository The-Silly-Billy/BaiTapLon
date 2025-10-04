package GameObject.Map;

import GameObject.Brick.*;
import Main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map5 {

    GamePanel gp;

    public List<Brick> list = new ArrayList<>();

    public Map5(GamePanel gp) {
        this.gp = gp;

        for(int i = 0; i < 12; i++) {
            list.add(new BrBrown(gp, 96, 50 + i * 25));
            list.add(new BrBrown(gp, 288, 50 + i * 25));
        }

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                list.add(new BrWhite(gp, 144 + j * 48, 75 + i * 25));
            }
        }

        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                list.add(new BrYellow(gp, 144 + j * 48, 125 + i * 25));
            }
        }

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                list.add(new BrPink(gp, 144 + j * 48, 275 + i * 25));
            }
        }

        for(int i = 0; i < 3; i++) {
            if(i == 1) {
                list.add(new BrBrown(gp, 144 + i + 48, 325));
            } else {
                list.add(new Wall(gp, 144 + i * 48, 325));
            }
        }

        list.add(new BrBrown(gp, 336, 100));
        list.add(new BrBrown(gp, 336, 225));

        for(int i = 0; i < 4; i++) {
            list.add(new BrBrown(gp, 384, 125 + i * 25));
        }
    }

    public void render(Graphics2D g2) {
        for (Brick brick : list) {
            g2.drawImage(brick.image, (int) brick.posX, (int) brick.posY, brick.width, brick.height, null);
        }
    }
}
