

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
 

/**
 *
 * @author Muhajir
 */
public class Food {
    
    private Location location;
    private Random random;
    
    public Food() {
        random = new Random();
        location = new Location((random.nextInt(15) * 25) + 25, (random.nextInt(15) * 25) + 25);
    }
    
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(location.getX(), location.getY(), 25, 25);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public void move() {
        location = new Location((random.nextInt(15) * 25) + 25,
                                (random.nextInt(15) * 25) + 25);
    }
    
}