package GameUI;

import GameUI.Button;
import Main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static GameUI.GameState.*;

public class PauseGame {
    private Button[] buttons = new Button[5];
    private BufferedImage backgroundImg;
    private BufferedImage[] imgs;
    private GamePanel gp;
    public GameState state = PAUSED;
    private int buttonNumber = 0;
    private int pauseMenuX, pauseMenuY;

    private static final int PAUSE_MENU_WIDTH = 360;
    private static final int PAUSE_MENU_HEIGHT = 524;
    private static final int xCenterOffset = PAUSE_MENU_WIDTH / 2;
    private static final int yCenterOffset = PAUSE_MENU_HEIGHT / 2;

    public PauseGame( GamePanel gamePanel) {
        this.gp = gamePanel;
        this.pauseMenuX = (int) (gamePanel.screenWidth / 2 - xCenterOffset);
        this.pauseMenuY = (int) (gamePanel.screenHeight / 2 - yCenterOffset);
        loadButtons();
        loadBackground();
    }

    private void loadButtons() {
        buttons[0] = new Button(pauseMenuX + 236, pauseMenuY + 219,0, LoadMat.MUSIC_ON, PAUSED);
        buttons[1] = new Button(pauseMenuX + 236, pauseMenuY + 161,0, LoadMat.MUSIC_ON, PAUSED);
        buttons[2] = new Button(pauseMenuX + 47, pauseMenuY + 414,0, LoadMat.HOME_BUTTON, PAUSED);
        buttons[3] = new Button(pauseMenuX + 148, pauseMenuY + 414,0, LoadMat.PLAY, PAUSED);
        buttons[4] = new Button(pauseMenuX + 249, pauseMenuY + 414,0, LoadMat.RESTART,PAUSED);
        buttons[0].setKeyOn(true);
        for(Button b : buttons) {
            b.setScale(2);
        }
    }

    private void loadBackground() {
        backgroundImg = LoadMat.GetSpriteAtlas(LoadMat.PAUSE_BACKGROUND);
    }

    public void update() {
        for(Button pause : buttons) {
            pause.update();
        }
    }

    public void resetButtons() {
        for( Button b : buttons) {
            b.reset();
        }
    }


    public void draw (Graphics g) {
        g.drawImage(backgroundImg,pauseMenuX,pauseMenuY, PAUSE_MENU_WIDTH, PAUSE_MENU_HEIGHT, null);

        for(Button pause : buttons) {
            pause.draw(g);
        }
    }

    public void setState( GameState state) {
        this.state = state;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            buttonNumber++;
            if (buttonNumber >= buttons.length)
                buttonNumber = 0; // wrap around to first
            resetButtons();
            buttons[buttonNumber].setKeyOn(true);
        }

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            buttonNumber--;
            if (buttonNumber < 0)
                buttonNumber = buttons.length - 1; // wrap around to last
            resetButtons();
            buttons[buttonNumber].setKeyOn(true);
        }

        if (code == KeyEvent.VK_ENTER) {
            state = buttons[buttonNumber].getState();
//            this.setState(state);

            gp.setState(state);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.setState(PLAYING);
        }
    }


}
