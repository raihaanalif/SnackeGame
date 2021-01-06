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

/**
 *
 * @author Muhajir
 */
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
        random = new Random();
        gamestat = MENU;
        dificulty = EASY;
        
        title = new ImageIcon("title.png");        
    }

    public void gamestart() {
        snake = new Snake();
        food = new Food();
        score = 0;
        move = RIGHT;
        gamestat = START;
        canmove = true;
        
        time = 270 / dificulty;
        timer = new Timer(time, this);
        timer.start();
    }

    public void render(Graphics2D g) {
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gamestat == MENU) {
            g.setColor(Color.WHITE);
            title.paintIcon(renderer, g, 225, 100);
            g.setFont(new Font("arial", Font.BOLD, 20));
            g.drawString("SPACE to Start", 275, 270);
            
            switch (dificulty) {
                case EASY:
                    g.drawString("<< Dificulty: Easy >>", 245, 300);
                    break;
                
                case MEDIUM:
                    g.drawString("<< Dificulty: Medium >>", 230, 300);
                    break;
                    
                case HARD:
                    g.drawString("<< Dificulty: Hard >>", 247, 300);
                    break;
                    
                default:
                    break;
            }
        } else {
            g.setColor(Color.WHITE);
            g.drawRect(24, 24, 426, 426);

            g.setFont(new Font("arial", Font.PLAIN, 20));
            
            switch (dificulty) {
                case EASY:
                    g.drawString("EASY", 545, 125);
                    break;
                
                case MEDIUM:
                    g.drawString("MEDIUM", 535, 125);
                    break;
                    
                case HARD:
                    g.drawString("HARD", 550, 125);
                    break;
                    
                default:
                    break;
            }
            
            g.drawString("Score: " + score, 530, 200);
            g.drawString("Length: " + snake.getSnakeloc().size(), 530, 225);
            
            if (gamestat == START)
                g.drawString("SPACE to Pause", 500, 275);
            else if (gamestat == PAUSE)
                g.drawString("SPACE to Continue", 490, 275);
            
            snake.render(g, renderer);
            food.render(g);

            if (gamestat == GAMEOVER) {
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("GAME OVER", 200, 225);
                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("SPACE to restart", 265, 255);
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (gamestat == START) {
            if (!snake.move(move)) {
                gamestat = GAMEOVER;
                timer.stop();
            }

            if (snake.eat(food)) {
                food.move();
                snake.grow();
                score += 10;
            }

        }

        renderer.repaint();        
        canmove = true;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

        if (gamestat == MENU) {
            if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                gamestart();
                gamestat = START;
            } if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                dificulty += 1;
                if (dificulty > 3) dificulty = 1;
            } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                dificulty -= 1;
                if (dificulty < 1) dificulty = 3;
            }
            
            renderer.repaint();
        } else if (gamestat == START) {
            if (canmove) {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if (move != LEFT) {
                            move = RIGHT;
                        }
                        break;

                    case KeyEvent.VK_LEFT:
                        if (move != RIGHT) {
                            move = LEFT;
                        }
                        break;

                    case KeyEvent.VK_UP:
                        if (move != DOWN) {
                            move = UP;
                        }
                        break;

                    case KeyEvent.VK_DOWN:
                        if (move != UP) {
                            move = DOWN;
                        }
                        break;
                        
                    case KeyEvent.VK_SPACE:
                        gamestat = PAUSE;
                        break;

                    default:
                        break;
                }

                canmove = false;
            }
        } else if (gamestat == GAMEOVER) {
            if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                gamestart();
                gamestat = START;
            } else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                gamestat = MENU;
            }
            
            renderer.repaint();
        } else if (gamestat == PAUSE) {
            if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                gamestat = START;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

}