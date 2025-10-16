package GameUI;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import GameUI.StartMenu;

public class Button {
    private int xPos, yPos, rowIndex;
    private BufferedImage[] imgs;
    private GameState state;
    private boolean keyOn, keyPressed;
    private int index;

    protected static final int B_WIDTH_DEFAULT = 140;
    protected static final int B_HEIGHT_DEFAULT = 56;
    protected static final int xCenterPoint = B_WIDTH_DEFAULT / 2;
    protected  static final int yCenterPoint = B_HEIGHT_DEFAULT / 2;

    public Button(int xPos, int yPos, int rowIndex, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        this.keyOn = false;
        this.keyPressed = false;
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadMat.GetSpriteAtlas(LoadMat.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
    }

    public void draw( Graphics g) {
        g.drawImage(imgs[index], xPos - xCenterPoint, yPos - yCenterPoint, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT, null);
    }

    public void update() {
        index =0;
        if(keyOn) {
            index = 1;
        }
        if (keyPressed) {
            index = 2;
        }
    }

    public boolean isKeyOn() {
        return this.keyOn;
    }

    public void setKeyOn( boolean keyOn) {
        this.keyOn = keyOn;
    }

    public boolean isKeyPressed() {
        return this.keyPressed;
    }

    public void setKeyPressed( boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    public GameState getState() {
        return this.state;
    }
    public void reset() {
        keyOn = false;
        keyPressed = false;
    }



}
