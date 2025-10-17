package GameObject.PowerUp;

import GameObject.Paddle;
import Main.GamePanel;

public class Shrink extends PowerUp{
    public Shrink(GamePanel gp, int time , double posX, double posY, Paddle paddle){
        super(gp);
        this.posX=posX;
        this.posY=posY;
        this.duration=time*60;
        paddle.speed=3;
    }

}
