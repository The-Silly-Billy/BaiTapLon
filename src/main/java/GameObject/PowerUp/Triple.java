package GameObject.PowerUp;

import GameObject.Ball;
import Main.GamePanel;

public class Triple extends PowerUp{
    public Triple(GamePanel gp, int time, double posX, double posY, Ball ball){
        super(gp);
        this.duration=time*60;
        this.posX=posX;
        this.posY=posY;

    }
}
