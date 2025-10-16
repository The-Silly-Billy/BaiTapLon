package GameUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import Main.GamePanel;
import GameUI.Button;
import Main.GamePanel;
import Main.KeyHandler;

import static GameUI.GameState.MENU;

public class StartMenu {
    private Button[] buttons = new Button[3];
    private BufferedImage backgroundImg;
    private int menyX, menuY, menuWidth, menuHeight;
    private GamePanel gamePanel;
    public GameState state = MENU;
    private int buttonNumber = 0;

    public StartMenu( GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        loadButtons();
    }



    private void loadButtons() {
        buttons[0] = new Button(gamePanel.screenWidth / 2 , gamePanel.screenHeight  / 2 - 2* Button.B_HEIGHT_DEFAULT,0, GameState.PLAYING);
        buttons[1] = new Button(gamePanel.screenWidth / 2 , gamePanel.screenHeight / 2 ,1, GameState.OPTION);
        buttons[2] = new Button(gamePanel.screenWidth / 2 , gamePanel.screenHeight / 2 + 2* Button.B_HEIGHT_DEFAULT,2, GameState.QUIT);
    }

    public GameState getState() {
        return this.state;
    }

    public void setState( GameState state) {
        this.state = state;
    }

    public void update() {
        for(Button b : buttons) {
            b.update();
        }
    }

    public void resetButtons() {
        for( Button b : buttons) {
            b.reset();
        }
    }

    public void draw (Graphics g) {
        for(Button b : buttons) {
            b.draw(g);
        }
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
            this.setState(state);

            gamePanel.setState(state);
        }
    }
}
