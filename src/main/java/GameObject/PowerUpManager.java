package GameObject;

import GameObject.PowerUp.*;
import Main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PowerUpManager {
    GamePanel gp;

    public List<PowerUp> list = new ArrayList<>();

    public PowerUpManager(GamePanel gp) {
        this.gp = gp;


    }

    public void update(int index) {

    }

    public void render(Graphics2D g2) {
        for(PowerUp pu : list) {
            g2.drawImage(pu.image, (int) pu.posX, (int) pu.posY, pu.width, pu.height, null);
        }
    }
}
