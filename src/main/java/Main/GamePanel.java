package Main;

import GameObject.Brick.BrLv1;
import GameObject.GameObject;
import GameObject.Ball;
import GameObject.Paddle;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Cai dat pixel
    public final int originalTileSize = 16;                         // 1 khung pixel 16x16
    final int scale = 3;                                            // phong dai khung len 3 lan
    final int tileSize = originalTileSize * scale;                  // 48 pixel

    // man hinh ti le 4:3
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol;        //768 pixels
    public final int screenHeight = tileSize * maxScreenRow;       //576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Player
    Paddle paddle = new Paddle(this, keyH);
    //Ball
    Ball ball = new Ball(this, keyH);
    //Brick
    BrLv1 brLv1 = new BrLv1(this);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);       //de tim hieu them ^^

        this.addKeyListener(keyH);
        this.setFocusable(true);            //de tim hieu them ^^

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000.0 / FPS;      // tgian ve 1 khung hinh (0.0166s)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            //Setup FPS dua tren "DELTA METHOD"
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update() {

        paddle.update();
        ball.update();

        //Va cham voi pad
        int vaCham1 = GameObject.isCollide(ball, paddle);

        if(vaCham1 == 2) {
            ball.move.changeY();
            if(ball.move.x >= 0) {
                if(ball.posX >= paddle.posX && ball.posX <= paddle.posX + ((double) paddle.width / 4)) {
                    ball.move.changeVal(ball.speed, 60);
                    ball.move.changeX();

                }
                if(ball.posX >= paddle.posX + ((double) (paddle.width * 3) / 4) && paddle.posX <= paddle.posX + paddle.width) {
                    ball.move.changeVal(ball.speed, 30);
                }
            }
            if(ball.move.x <= 0) {
                if(ball.posX >= paddle.posX && ball.posX <= paddle.posX + ((double) paddle.width / 4)) {
                    ball.move.changeVal(ball.speed, 30);
                    ball.move.changeX();
                }
                if(ball.posX >= paddle.posX + ((double) (paddle.width * 3) / 4) && paddle.posX <= paddle.posX + paddle.width) {
                    ball.move.changeVal(ball.speed, 60);
                }
            }
        }

        //Va cham vs gach
        int vaChamGach = GameObject.isCollide(ball, brLv1);

        if(vaChamGach == 1 || vaChamGach == 3) {
            ball.move.changeX();
            brLv1.takeHit();
        }
        if(vaChamGach == 2 || vaChamGach == 4) {
            ball.move.changeY();
            brLv1.takeHit();
        }

        //Reset lai vi tri cu sau khi chet
        if(ball.posY > screenHeight - ball.radius) {
            ball.initPos();
            paddle.initPos();
        }
    }

    //method ve
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        paddle.render(g2);
        ball.render(g2);

        if(!brLv1.isDestroy()) {
            brLv1.render(g2);
        }

        g2.dispose();
    }
}
