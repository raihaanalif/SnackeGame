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
        java.net.URL titleresource = getClass().getResource("title.png");
        this.title = new ImageIcon(titleresource); //menampilkan logo dari gamenya itu sendiri
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
            //menampilkan tulisan dan lain sebagainya yang diperlukan di laman menu
            g.setColor(Color.WHITE);
            this.title.paintIcon(this.renderer, g, 225, 100);
            g.setFont(new Font("arial", 1, 20)); //mengatur font tulisan yang ada pada menu
            g.drawString("SPACE to Start", 275, 270); //mengatur letak tulisan yang ada di menu
            switch(this.dificulty) {
               //mengatur berbagai tulisan dificulty yang ada di laman menu
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
                    g.drawString("HARD", 545, 125);
            }
            //memasukan komponen yang diperlukan di dalam game seperti score, dificulty, panjang ular dan sebagainya
            g.drawString("Score: " + this.score, 530, 200);
            g.drawString("Length: " + this.snake.getSnakeloc().size(), 530, 225);
            if (this.gamestat == 1) {
                //bila gamenya berjalan, maka terdapat tulisan pause di sisi kanan
                g.drawString("SPACE to Pause", 500, 275);
            } else if (this.gamestat == 3) {
                //bila gamenya sedang berhenti sejenak/pause maka terdapat tapilan untuk melanjutkan atau keluar dari game
                g.drawString("SPACE to Continue", 490, 275);
                g.drawString("ESC to Exit", 515, 300);
            }

            this.snake.render(g, this.renderer);
            this.food.render(g);
            if (this.gamestat == 2) {
                //bila game over, maka terdapat tulisan keterangan game over serta dapat mengulang kebali dengan menekan spasi
                g.setFont(new Font("arial", 1, 50));
                g.drawString("GAME OVER", 200, 225);
                g.setFont(new Font("arial", 1, 20));
                g.drawString("SPACE to restart", 265, 255);
                g.setFont(new Font("arial", 1, 20));
                g.drawString("ESC to Exit", 295, 300);
            }
        }

    }

    public void actionPerformed(ActionEvent ae) {
        if (this.gamestat == 1) {
            //bila game berjalan dan mengenai badannya maka gamestat akan berubah statusnya menjadi 2 / game over
            if (!this.snake.move(this.move)) {
                this.gamestat = 2;
                this.timer.stop();
            }

            if (this.snake.eat(this.food)) {
                //bila ular memakan makanannya maka panjang ular bertambah dan poinnya bertambah 10
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
            //jika gamestat statusnya 0 / masih di menu bila menekan spasi gamestat akan berubah status menjadi 1 / gamenya berjalan 
            if (ke.getKeyCode() == 32) { //ASCI spasi
                this.gamestart();
                this.gamestat = 1;
            }
            //dimenu bila kita menekan arrow right dificulty akan berubah menjadi lebih besar/sulit 
            if (ke.getKeyCode() == 39) { //ASCI right
                ++this.dificulty;
                if (this.dificulty > 3) {
                    this.dificulty = 1;
                }
            } else if (ke.getKeyCode() == 37) { //ASCII left
                //dimenu bila menekan arrow left dificultynya akan berkurang menjadi lebih kecil/mudah
                --this.dificulty;
                if (this.dificulty < 1) {
                    this.dificulty = 3;
                }
            }

            this.renderer.repaint();
        } else if (this.gamestat == 1) {
            //fungsi ini adalah fungsi pergerakan ular
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
            //bila gamestatus 2 / gameover maka kita dapat mengulang gamenya dengan menekan tombol spasi atau keluar dari game dengan menekan
            //tombol esc
            if (ke.getKeyCode() == 32) {
                this.gamestart();
                this.gamestat = 1;
            } else if (ke.getKeyCode() == 27) {
                System.exit(0);
            }

            this.renderer.repaint();
        } else if(this.gamestat == 3 && ke.getKeyCode() == 32) {
            //bila game pause dan kita menekan spasi maka game akan berubah statusnya menjadi 1 / kembali berjalan
            this.gamestat = 1;
        } else if(this.gamestat == 3 && ke.getKeyCode() == 27){
            //bila game pause lalu kita menekan esc maka kita akan keluar dari game
//            this.gamestat = 0;
//            this.dificulty = 1;
            System.exit(0);
        }
    }
    public void keyReleased(KeyEvent ke) {
    }
}
