package GameObject.PowerUp;

import GameObject.Paddle;
import Main.GamePanel;

public class Expandpad  extends PowerUp{
    public Expandpad(GamePanel gp, int time, double posX, double posY, Paddle paddle){
        super(gp);
        this.posX=posX;
        this.posY=posY;
        this.duration=time*60;
        paddle.width=(gp.originalTileSize * 5 - 10)*2;
    }
}
