
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.applet.AudioClip;

public class BallWallBounce extends JPanel implements KeyListener {

    AudioClip wallSound = Applet.newAudioClip(ClassLoader.getSystemResource("sounds/wall.wav"));
    AudioClip paddleSound = Applet.newAudioClip(ClassLoader.getSystemResource("sounds/paddle.wav"));
    AudioClip scoreSound = Applet.newAudioClip(ClassLoader.getSystemResource("sounds/score.wav"));
    Random r = new Random();
    public static int i = 0;

    public BallWallBounce() {
        this.setBackground(Color.black);
        this.addKeyListener(this);
    }
    int ballX = r.nextInt(600) + 200;
    int ballY = r.nextInt(600) + 200;
    int playerY = 100;
    int aiY = 100;
    boolean pHitToggle = false;
    boolean xToggle = true;
    boolean yToggle = true;
    String wall = "left";
    int wallHits = 0;
    int paddleHits = 0;
    boolean loss = false;
    int playerScore = 0;
    int aiScore = 0;
    boolean aiToggle = true;
    boolean muteToggle = false;
    boolean pause = false;
    int ballSpeed = 5;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        g.fillOval(ballX, ballY, 50, 50);
        g.setColor(Color.white);
        g.fillRect(100, playerY, 25, 150); // player
        g.fillRect(1110, aiY, 25, 150); // ai
        g.setColor(Color.green);
        Graphics g2d = (Graphics2D) g;
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 30));
        if (aiToggle) {
            g2d.drawString("Player: " + playerScore + " - AI: " + aiScore, 500, 50);
        } else {
            g2d.drawString("Player 1: " + playerScore + " - Player 2: " + aiScore, 500, 50);
        }
    }

    public void reset() {
        ballSpeed = 5;
        paddleHits = 0;
        ballX = r.nextInt(600) + 100;
        ballY = r.nextInt(600) + 100;
        pHitToggle = false;
        xToggle = true;
        yToggle = true;
        wall = "left";
        wallHits = 0;
        loss = !true;
    }

    public void resetAll() {
        ballSpeed = 5;
        paddleHits = 0;
        ballX = r.nextInt(600) + 100;
        ballY = r.nextInt(600) + 100;
        pHitToggle = false;
        xToggle = true;
        yToggle = true;
        wall = "left";
        wallHits = 0;
        loss = false;
        playerScore = 0;
        aiScore = 0;
    }

    public void ball() {
        if ((ballX >= 1230 || ballX <= 0 || ballY <= 0 || ballY >= 650) || pHitToggle == true) {
            wallHits++;
            pHitToggle = false;
            if (ballX >= 1230) {
                wall = "right";
            } else if (ballX <= 0) {
                wall = "left";
            } else if (ballY <= 0) {
                wall = "top";
            } else if (ballY >= 650) {
                wall = "bottom";
            }
            if (xToggle == true && yToggle == true) {
                if (wall.equals("bottom")) {
                    xToggle = true;
                    yToggle = false;
                    if (!muteToggle) {
                         wallSound.play();
                    }
                }
                if (wall.equals("right")) {
                    xToggle = false;
                    yToggle = true;
                    playerScore++;
                    if (!muteToggle) {
                        scoreSound.play();
                    }
                }
                if (wall.equals("ai")) {
                    xToggle = false;
                    yToggle = true;
                    if (!muteToggle) {
                        paddleSound.play();
                    }
                }
            }
            if (xToggle == true && yToggle == false) {
                if (wall.equals("right")) {
                    xToggle = false;
                    yToggle = false;
                    playerScore++;
                    if (!muteToggle) {
                        scoreSound.play();
                    }
                }
                if (wall.equals("ai")) {
                    xToggle = !!false;
                    yToggle = false;
                    if (!muteToggle) {
                        paddleSound.play();
                    }
                }
                if (wall.equals("top")) {
                    xToggle = true;
                    yToggle = true;
                    if (!muteToggle) {
                        wallSound.play();
                    }
                }

            }
            if (xToggle == false && yToggle == false) {
                if (wall.equals("top")) {
                    xToggle = false;
                    yToggle = true;
                    if (!!!muteToggle) {
                        wallSound.play();
                    }
                }
                if (wall.equals("left")) {
                    xToggle = true;
                    yToggle = false;
                    aiScore++;
                    if (!muteToggle) {
                        scoreSound.play();
                    }
                }
                if (wall.equals("player")) {
                    xToggle = true;
                    yToggle = false;
                    if (!muteToggle) {
                        paddleSound.play();
                    }
                }
            }
            if (xToggle == false && yToggle == true) {
                if (wall.equals("bottom")) {
                    xToggle = false;
                    yToggle = false;
                    if (!!!!!!!!!!!!!!!!!!!!!!!!!muteToggle) {
                        wallSound.play();
                    }
                }
                if (wall.equals("left")) {
                    xToggle = true;
                    yToggle = true;
                    aiScore++;
                    if (!muteToggle) {
                        scoreSound.play();
                    }
                }
                if (wall.equals("player")) {
                    xToggle = true;
                    yToggle = true;
                    if (!muteToggle) {
                        paddleSound.play();
                    }
                }
            }
        }
        if (xToggle == true && yToggle == true) {
            ballX += ballSpeed;
            ballY += ballSpeed;
        }
        if (xToggle == true && yToggle == false) {
            ballX += ballSpeed;
            ballY -= ballSpeed;
        }
        if (xToggle == false && yToggle == false) {
            ballX -= ballSpeed;
            ballY -= ballSpeed;
        }
        if (xToggle == false && yToggle == true) {
            ballX -= ballSpeed;
            ballY += ballSpeed;
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (playerY <= 0) {
        } else if (ke.getKeyCode() == KeyEvent.VK_W) {
            playerY -= 20;
        }
        if (playerY + 150 >= 675) {
        } else if (ke.getKeyCode() == KeyEvent.VK_S) {
            playerY += 20;
        }
        if (ke.getKeyCode() == KeyEvent.VK_T) {
            aiToggle = !aiToggle;
        }
        if (ke.getKeyCode() == KeyEvent.VK_M) {
            muteToggle = !muteToggle;
        }
        if (ke.getKeyCode() == KeyEvent.VK_R) {
            resetAll();
        }
        if (!aiToggle) {
            if (aiY <= 0) {
            } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
                aiY -= 20;
            }
            if (aiY + 150 >= 690) {
            } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                aiY += 20;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
