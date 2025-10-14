package GameObject.PowerUp;

import GameObject.Ball;
import Main.GamePanel;

public class FireBall extends PowerUp {

    public FireBall(GamePanel gp, int time, double posX, double posY, Ball ball) {
        super(gp);

        this.posX = posX;
        this.posY = posY;

        this.duration = time * 60;
        ball.ballDamage = 100;
    }
}
