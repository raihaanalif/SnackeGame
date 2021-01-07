//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GamePlay implements ActionListener, KeyListener {
    private Renderer renderer;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int MENU = 0;
    private static final int START = 1;
    private static final int GAMEOVER = 2;
    private static final int PAUSE = 3;
    private int gamestat;
    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;
    private int dificulty;
    private Snake snake;
    private Food food;
    private Timer timer;
    private Random random;
    private int score;
    private int move;
    int time;
    private boolean canmove;
    private ImageIcon title;

    public GamePlay(Renderer renderer) {
        this.renderer = renderer;
        this.random = new Random();
        this.gamestat = 0;
        this.dificulty = 1;
        this.title = new ImageIcon("title.png");
    }

    public void gamestart() {
        this.snake = new Snake();
        this.food = new Food();
        this.score = 0;
        this.move = 1;
        this.gamestat = 1;
        this.canmove = true;
        this.time = 270 / this.dificulty;
        this.timer = new Timer(this.time, this);
        this.timer.start();
    }

    public void render(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.gamestat == 0) {
            g.setColor(Color.WHITE);
            this.title.paintIcon(this.renderer, g, 225, 100);
            g.setFont(new Font("arial", 1, 20));
            g.drawString("SPACE to Start", 275, 270);
            switch(this.dificulty) {
                case 1:
                    g.drawString("<< Dificulty: Easy >>", 245, 300);
                    break;
                case 2:
                    g.drawString("<< Dificulty: Medium >>", 230, 300);
                    break;
                case 3:
                    g.drawString("<< Dificulty: Hard >>", 247, 300);
            }
        } else {
            g.setColor(Color.WHITE);
            g.drawRect(24, 24, 426, 426);
            g.setFont(new Font("arial", 0, 20));
            switch(this.dificulty) {
                case 1:
                    g.drawString("EASY", 545, 125);
                    break;
                case 2:
                    g.drawString("MEDIUM", 535, 125);
                    break;
                case 3:
                    g.drawString("HARD", 550, 125);
            }

            g.drawString("Score: " + this.score, 530, 200);
            g.drawString("Length: " + this.snake.getSnakeloc().size(), 530, 225);
            if (this.gamestat == 1) {
                g.drawString("SPACE to Pause", 500, 275);
            } else if (this.gamestat == 3) {
                g.drawString("SPACE to Continue", 490, 275);
            }

            this.snake.render(g, this.renderer);
            this.food.render(g);
            if (this.gamestat == 2) {
                g.setFont(new Font("arial", 1, 50));
                g.drawString("GAME OVER", 200, 225);
                g.setFont(new Font("arial", 1, 20));
                g.drawString("SPACE to restart", 265, 255);
            }
        }

    }

    public void actionPerformed(ActionEvent ae) {
        if (this.gamestat == 1) {
            if (!this.snake.move(this.move)) {
                this.gamestat = 2;
                this.timer.stop();
            }

            if (this.snake.eat(this.food)) {
                this.food.move();
                this.snake.grow();
                this.score += 10;
            }
        }

        this.renderer.repaint();
        this.canmove = true;
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
        if (this.gamestat == 0) {
            if (ke.getKeyCode() == 32) { //ASCI spasi
                this.gamestart();
                this.gamestat = 1;
            }

            if (ke.getKeyCode() == 39) { //ASCI right
                ++this.dificulty;
                if (this.dificulty > 3) {
                    this.dificulty = 1;
                }
            } else if (ke.getKeyCode() == 37) { //ASCII left
                --this.dificulty;
                if (this.dificulty < 1) {
                    this.dificulty = 3;
                }
            }

            this.renderer.repaint();
        } else if (this.gamestat == 1) {
            if (this.canmove) {
                switch(ke.getKeyCode()) {
                    case 32:
                        this.gamestat = 3;
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    default:
                        break;
                    case 37:
                        if (this.move != 1) {
                            this.move = 3;
                        }
                        break;
                    case 38:
                        if (this.move != 2) {
                            this.move = 0;
                        }
                        break;
                    case 39:
                        if (this.move != 3) {
                            this.move = 1;
                        }
                        break;
                    case 40:
                        if (this.move != 0) {
                            this.move = 2;
                        }
                }

                this.canmove = false;
            }
        } else if (this.gamestat == 2) {
            if (ke.getKeyCode() == 32) {
                this.gamestart();
                this.gamestat = 1;
            } else if (ke.getKeyCode() == 10) {
                this.gamestat = 0;
            }

            this.renderer.repaint();
        } else if (this.gamestat == 3 && ke.getKeyCode() == 32) {
            this.gamestat = 1;
        }

    }

    public void keyReleased(KeyEvent ke) {
    }
}
