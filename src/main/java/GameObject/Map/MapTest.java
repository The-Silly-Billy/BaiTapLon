package GameObject.Map;

import GameObject.Brick.*;
import Main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
-----------MAP DE TEST GACH-----------
--------------------------------------

   !! KHONG DUOC XOA MAP NAY DI !!

 -------------------------------------
 */
public class MapTest {

    GamePanel gp;

    public List<Brick> list = new ArrayList<>();

    public MapTest(GamePanel gp) {
        this.gp = gp;

        list.add(new BrBlue(gp, 48 + 146, 300));
        list.add(new BrGreen(gp, 96 + 146, 300));
        list.add(new BrLightBlue(gp, 144 + 146, 300));
        list.add(new BrPink(gp, 192 + 146, 300));
        list.add(new BrRed(gp, 240 + 146, 300));
        list.add(new BrWhite(gp, 288 + 146, 300));
        list.add(new BrYellow(gp, 336 + 146, 300));
        list.add(new Wall(gp, 384 + 146, 300));

    }

    public void render(Graphics2D g2) {
        for (Brick brick : list) {
            g2.drawImage(brick.image, (int) brick.posX, (int) brick.posY, brick.width, brick.height, null);
        }
    }
}
