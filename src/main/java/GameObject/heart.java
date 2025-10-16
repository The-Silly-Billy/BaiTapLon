package GameObject;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class heart extends GameObject{
    GamePanel gp;
    BufferedImage image;

    public heart (GamePanel gp,int posX,int posY){
        this.gp=gp;
        this.posX=posX;
        this.posY=posY;
        width= gp.originalTileSize*2;
        height=gp.originalTileSize;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void render(Graphics2D g2) {
        g2.drawImage(image, (int) posX ,  (int) posY , width, height, null);
    }


}
