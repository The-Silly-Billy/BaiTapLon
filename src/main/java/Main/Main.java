package Main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        //Xem nta day cach dung SWING JAVAX trong video nay nhe =)))) May cai t code cx la tu video nay ra day
        //https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=1&ab_channel=RyiSnow
        
        //khoi tao window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("A R K A N O I D");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}

/*
----MOT SO THU CAN HOAN THANH----
---------------------------------

1. Pad va Ball
    - Doi cach va cham Ball vs Pad

2. Brick
    - Them tinh nang bi vo cua gach
    - Them cac loai gach khac
    - Khoi tao nhieu Gach luc bat dau

3. Mau
    - Them thong so dem mau, dem diem

4. Them cac man hinh khac (MENU, SETTING, ...)

5. Them PowerUp

---------------------------------
 */
