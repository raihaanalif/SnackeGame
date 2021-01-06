import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Muhajir
 */
public class Snake {

    public static final int SNAKEDIMEN = 25;

    private static final int MINWIDTH = 25;
    private static final int MINHEIGHT = 25;
    private static final int MAXWIDTH = 450;
    private static final int MAXHEIGHT = 450;

    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    private ArrayList<Location> snakeloc;
    private ImageIcon headimage;
    private ImageIcon bodyimage;

    private int lengthOfSnake;
    private Location loc;

    public Snake() {
        snakeloc = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            snakeloc.add(new Location(100 - (SNAKEDIMEN * i), 50));
        }

        headimage = new ImageIcon("snakehead.png");
        bodyimage = new ImageIcon("snakebody.png");
    }

    public ArrayList<Location> getSnakeloc() {
        return snakeloc;
    }

    public void setLoc(int i, Location loc) {
        snakeloc.set(i, loc);
    }

    public void grow() {
        int x = snakeloc.get(snakeloc.size() - 1).getX();
        int y = snakeloc.get(snakeloc.size() - 1).getY();
        snakeloc.add(new Location(x, y));
    }

    public ImageIcon getHeadimage() {
        return headimage;
    }

    public ImageIcon getBodyimage() {
        return bodyimage;
    }

    public void render(Graphics g, Renderer renderer) {
        for (int i = 0; i < snakeloc.size(); i++) {
            int x = snakeloc.get(i).getX();
            int y = snakeloc.get(i).getY();

            if (i == 0) {
                headimage.paintIcon(renderer, g, x, y);
            } else {
                bodyimage.paintIcon(renderer, g, x, y);
            }
        }
    }
    
    public boolean eat(Food food) {
        return snakeloc.get(0).getX() == food.getLocation().getX()
                && snakeloc.get(0).getY() == food.getLocation().getY();
    }

    public boolean move(int move) {
        lengthOfSnake = snakeloc.size();

        switch (move) {
            case RIGHT:
                for (int i = lengthOfSnake - 2; i >= 0; i--) {
                    loc = snakeloc.get(i);
                    setLoc(i + 1, loc);

                    if (i == 0) {
                        setLoc(i, new Location(loc.getX() + SNAKEDIMEN, loc.getY()));
                        if (loc.getX() >= MAXWIDTH - SNAKEDIMEN) {
                            setLoc(0, new Location(MINWIDTH, loc.getY()));
                        }
                    }
                }
                break;
                
            case LEFT:
                for (int i = lengthOfSnake - 2; i >= 0; i--) {
                    loc = snakeloc.get(i);
                    setLoc(i + 1, loc);

                    if (i == 0) {
                        setLoc(i, new Location(loc.getX() - SNAKEDIMEN, loc.getY()));
                        if (loc.getX() <= MINWIDTH) {
                            setLoc(0, new Location(MAXWIDTH - SNAKEDIMEN, loc.getY()));
                        }
                    }
                }
                break;
                
            case UP:
                for (int i = lengthOfSnake - 2; i >= 0; i--) {
                    loc = snakeloc.get(i);
                    setLoc(i + 1, loc);

                    if (i == 0) {
                        setLoc(i, new Location(loc.getX(), loc.getY() - SNAKEDIMEN));
                        if (loc.getY() <= MINHEIGHT) {
                            setLoc(0, new Location(loc.getX(), MAXHEIGHT - SNAKEDIMEN));
                        }
                    }
                }
                break;
                
            case DOWN:
                for (int i = lengthOfSnake - 2; i >= 0; i--) {
                    loc = snakeloc.get(i);
                    setLoc(i + 1, loc);

                    if (i == 0) {
                        setLoc(i, new Location(loc.getX(), loc.getY() + SNAKEDIMEN));
                        if (loc.getY() >= MAXHEIGHT - SNAKEDIMEN) {
                            setLoc(0, new Location(loc.getX(), MINHEIGHT));
                        }
                    }
                }
                break;
                
            default:
                break;
        }
        
        for (int i = 1; i < lengthOfSnake; i++) {
            if (snakeloc.get(0).getX() == snakeloc.get(i).getX()
                    && snakeloc.get(0).getY() == snakeloc.get(i).getY())
                return false;
        }
        
        return true;
    }

}