package Main;

import GameObject.Brick.*;
import GameObject.Map.*;
import GameObject.GameObject;
import GameObject.Ball;
import GameObject.Paddle;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{

    //Cai dat pixel
    public final int originalTileSize = 16;                         // 1 khung pixel 16x16
    final int scale = 3;                                            // phong dai khung len 3 lan
    final int tileSize = originalTileSize * scale;                  // 48 pixel

    //Kich co man hinh
    final int maxScreenCol = 11;
    final int maxScreenRow = 15;

    public final int screenWidth = tileSize * maxScreenCol;        //768 pixels
    public final int screenHeight = tileSize * maxScreenRow;       //576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Random rand = new Random();

    //Player
    Paddle paddle = new Paddle(this, keyH);
    //Ball
    Ball ball = new Ball(this, keyH);
    //Brick Map
    Map1 map = new Map1(this);

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
        int vaChamVan = GameObject.isCollide(ball, paddle);

        if(vaChamVan == 2) {
            ball.move.changeY();
            if(ball.move.x >= 0) {
                if(keyH.rightPressed) {
                    if(ball.posX >= paddle.posX && ball.posX <= paddle.posX + ((double) paddle.width) / 4) {
                        ball.move.angle = rand.nextInt(90 - ball.move.angle + 1) + ball.move.angle;
                        ball.move.changeVal(ball.speed);
                    }
                    if(ball.posX >= paddle.posX + ((double) (paddle.width * 3) / 4) && paddle.posX <= paddle.posX + paddle.width) {
                        ball.move.angle = rand.nextInt(ball.move.angle - 10 + 1) + 10;
                        ball.move.changeVal(ball.speed);
                    }
                }

                if(keyH.leftPressed) {
                    if(ball.posX >= paddle.posX && ball.posX <= paddle.posX + ((double) paddle.width) / 4) {
                        ball.move.angle = rand.nextInt(ball.move.angle - 10 + 1) + 10;
                        ball.move.changeVal(ball.speed);
                        ball.move.changeX();
                    } else if(ball.posX >= paddle.posX + ((double) (paddle.width * 3) / 4) && paddle.posX <= paddle.posX + paddle.width) {
                        ball.move.angle = rand.nextInt(90 - ball.move.angle + 1) + ball.move.angle;
                        ball.move.changeVal(ball.speed);
                        ball.move.changeX();
                    } else {
                        ball.move.changeX();
                    }
                }
            }

            if(ball.move.x <= 0) {
                if(keyH.leftPressed) {
                    if(ball.posX >= paddle.posX && ball.posX <= paddle.posX + ((double) paddle.width) / 4) {
                        ball.move.angle = rand.nextInt(ball.move.angle - 10 + 1) + 10;
                        ball.move.changeVal(ball.speed);
                        ball.move.changeX();
                    }
                    if(ball.posX >= paddle.posX + ((double) (paddle.width * 3) / 4) && paddle.posX <= paddle.posX + paddle.width) {
                        ball.move.angle = rand.nextInt(90 - ball.move.angle + 1) + ball.move.angle;
                        ball.move.changeVal(ball.speed);
                        ball.move.changeX();
                    }
                }

                if(keyH.rightPressed) {
                    if(ball.posX >= paddle.posX && ball.posX <= paddle.posX + ((double) paddle.width) / 4) {
                        ball.move.angle = rand.nextInt(90 - ball.move.angle + 1) + ball.move.angle;
                        ball.move.changeVal(ball.speed);
                    } else if(ball.posX >= paddle.posX + ((double) (paddle.width * 3) / 4) && paddle.posX <= paddle.posX + paddle.width) {
                        ball.move.angle = rand.nextInt(ball.move.angle - 10 + 1) + 10;
                        ball.move.changeVal(ball.speed);
                    } else {
                        ball.move.changeX();
                    }
                }
            }
        }

        if(vaChamVan == 1|| vaChamVan == 3) {
            ball.move.changeX();
        }

        if(vaChamVan == 5 && ball.posY <= paddle.posY) {
            ball.move.changeX();
            ball.move.changeY();
        }

        //Va cham vs gach
        for(int i = 0; i < map.list.size(); i++) {
            Brick brick = map.list.get(i);

            int vaChamGach = GameObject.isCollide(ball, brick);

            if(vaChamGach != 0) {
                if (vaChamGach == 1 || vaChamGach == 3) {
                    ball.move.changeX();
                    brick.takeHit();
                }
                if (vaChamGach == 2 || vaChamGach == 4) {
                    ball.move.changeY();
                    brick.takeHit();
                }
                if(vaChamGach == 5) {
                    ball.move.changeX();
                    ball.move.changeY();
                    brick.takeHit();
                }

                break;
            }


            if (brick.isDestroy()) {
                map.list.remove(i);
                i--;
            }
        }

        //Reset lai vi tri cu sau khi chet
        //Update tut mau
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

        map.render(g2);

        g2.dispose();
    }
}
