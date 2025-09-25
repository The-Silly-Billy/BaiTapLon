package GameObject;

public class PowerUp extends GameObject{
    Vector2D pos;

    private int duration;    //tgian hieu luc
    private enum Type {
        ExpandPad,
        FireBall,
        Triple,
        PlusLife,
        ShrinkPad,
        SlowPad
    }
}
