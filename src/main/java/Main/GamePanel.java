package Main;

import GameObject.Brick.*;
import GameObject.Map.*;
import GameObject.GameObject;
import GameObject.Ball;
import GameObject.Paddle;
import GameObject.Heart;
import GameObject.PowerUp.PowerUp;
import GameUI.PauseGame;
import GameUI.StartMenu;
import GameUI.GameState;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{


    //Cai dat pixel
    public final int originalTileSize = 16;                         // 1 khung pixel 16x16
    final int scale = 3;                                            // phong dai khung len 3 lan
    final int tileSize = originalTileSize * scale;                  // 48 pixel

    //Kich co man hinh
    final int maxScreenCol = 11;
    final int maxScreenRow = 15;

    public final int screenWidth = tileSize * maxScreenCol;        //576 pixels
    public final int screenHeight = tileSize * maxScreenRow;       //768 pixels

    //576-40= 536 rong
    //150-16-10 = 124 cao

    StartMenu menu = new StartMenu(this);
    PauseGame pauseGame = new PauseGame(this);
    GameState state = GameState.MENU;
    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;

    Random rand = new Random();

    //Player
    Paddle paddle = new Paddle(this, keyH);
    //Ball
    Ball ball = new Ball(this, keyH);
    //Brick Map
    Map6 map = new Map6(this);
    //PowerUp
    //hearts
    List<Heart> heartList=new ArrayList<>();
    int scoreplayer=0;
    Font customFont=null;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);       //de tim hieu them ^^

        this.addKeyListener(keyH);
        this.setFocusable(true);//de tim hieu them ^^
        setupHearts();
        try {
            InputStream inputStream=getClass().getResourceAsStream("/Font/Jersey25-Regular.ttf");
            customFont =Font.createFont(Font.TRUETYPE_FONT,inputStream);
            GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("loi");
        }
    }
    //ham add 3 oject heart vao list
    public void setupHearts(){
        for (int i=0;i<3;i++){
            heartList.add(new Heart(this,10+50*i,screenHeight-50));//posY lay toa do bang cach thu
        }
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

        while (gameThread != null) {

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

        switch (state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                paddle.update();
                ball.update();
                break;
            case GAME_OVER:
                // handle game over later
                break;
            case QUIT:
                System.exit(0);
                break;
            case PAUSED:
                pauseGame.update();
                break;
        }

        //Va cham voi pad
        int vaChamVan = GameObject.isCollideBnR(ball, paddle);

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

            int vaChamGach = GameObject.isCollideBnR(ball, brick);

            if(vaChamGach != 0 ) {

                if (vaChamGach == 1 || vaChamGach == 3) {
                    if (!(brick instanceof BrWall)) {
                        scoreplayer++;
                        repaint();
                    }

                    ball.move.changeX();
                    brick.takeHit(ball);
                }
                if (vaChamGach == 2 || vaChamGach == 4)  {
                    if (!(brick instanceof BrWall)) {
                        scoreplayer++;
                        repaint();
                    }

                    ball.move.changeY();
                    brick.takeHit(ball);
                }
                if(vaChamGach == 5) {
                    if (!(brick instanceof BrWall)) {
                        scoreplayer++;
                        repaint();
                    }

                    ball.move.changeX();
                    ball.move.changeY();
                    brick.takeHit(ball);
                }

                break;
            }

            if (brick.isDestroy()) {
                map.list.remove(i);
                i--;
            }
        }

        //Va cham voi PowerUp



        //Update tut mau
        //khi het ba mang out game
        if(ball.posY > screenHeight - ball.radius) {
            if (!heartList.isEmpty()) {
                heartList.remove(heartList.size() - 1);//xoa mot heart khi chet
                ball.initPos();
                paddle.initPos();
            }
            ball.initPos();
            paddle.initPos();
        }

    }

    //method ve
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        switch (state) {
            case MENU:
                menu.draw(g2);
                break;
            case PLAYING:
                paddle.render(g2);
                ball.render(g2);

                map.render(g2);
                for (Heart h : heartList) {
                    h.render(g2);
                }
                if (customFont != null) {

                    g2.setFont(customFont.deriveFont(Font.PLAIN, 20f));
                }
                g2.setColor(Color.white);

                g2.drawString("Score : "+scoreplayer,screenWidth-100,screenHeight -40);
                break;
            case GAME_OVER:
                //Game over
            case PAUSED:
                paddle.render(g2);
                ball.render(g2);

                map.render(g2);
                for (Heart h : heartList) {
                    h.render(g2);
                }
                if (customFont != null) {

                    g2.setFont(customFont.deriveFont(Font.PLAIN, 20f));
                }
                g2.setColor(Color.white);

                g2.drawString("Score : "+scoreplayer,screenWidth-100,screenHeight -40);
                pauseGame.draw(g2);
                break;

        }
        g2.dispose();
    }

    public GameState getState() {
        return this.state;
    }

    public StartMenu getMenu() {
        return menu;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
