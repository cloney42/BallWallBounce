
import javax.swing.JFrame;
import java.util.Random;

public class Launch {

    Random r = new Random();
    static int count = 0;

    public static void main(String[] args) {
        Launch l = new Launch();
    }
    BallWallBounce b = new BallWallBounce();

    public Launch() {
        if (count == 0) {
            JFrame f = new JFrame();
            f.setTitle("Pong");
            f.addKeyListener(b);
            f.add(b);
            f.setBounds(200, 0, 1280, 720);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            f.setVisible(true);
            count++;
        }
        loop();
    }

    public void loop() {
        while (true) {
            b.ball();
            b.repaint();
            try {
                Thread.sleep(10);
                if (b.aiToggle) {
                    if (b.ballY > b.aiY + 75) {
                        if (b.aiY + 150 >= 690) {
                        } else {
                            b.aiY += b.ballSpeed - 1;
                        }
                    }
                    if (b.ballY < b.aiY + 75) {
                        if (b.aiY <= 0) {
                        } else 
                        {
                            b.aiY -= b.ballSpeed - 1;
                        }
                    }
                }
                if (checkHit() == true) { // player
                    b.paddleHits++;
                    if (b.paddleHits == 5) {
                        // b.ballSpeed++;
                        b.paddleHits = 0;
                    }
                    b.pHitToggle = true;
                    b.wall = "player";
                } else if (b.wall.equals("left") && b.wallHits > 0) {
                    b.reset();
                }
                if (aiCheckHit() == true) { // ai
                    b.paddleHits++;
                    if (b.paddleHits == 5) {
                        // b.ballSpeed++;
                        b.paddleHits = 0;
                    }
                    b.pHitToggle = true;
                    b.wall = "ai";
                } else if (b.wall.equals("right") && b.wallHits > 0) {
                    b.reset();
                }
            } catch (InterruptedException ex) {

            }

        }

    }

    public boolean checkHit() {
        if (b.ballX <= 125 && b.ballX >= 100 && b.xToggle == false) { // player
            if (b.ballY + 50 >= b.playerY && b.ballY < b.playerY + 150) {
                return true;
            }
        }
        return false;
    }

    public boolean aiCheckHit() { // ai
        if (b.ballX >= 1065 && b.ballX <= 1090 && b.xToggle == true) {
            if (b.ballY + 45 > b.aiY && b.ballY < b.aiY + 150) {
                return true;
            }
        }
        return false;
    }

}
