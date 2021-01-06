//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {
    private Location location;
    private Random random = new Random();

    public Food() {
        this.location = new Location(this.random.nextInt(15) * 25 + 25, this.random.nextInt(15) * 25 + 25);
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(this.location.getX(), this.location.getY(), 25, 25);
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void move() {
        this.location = new Location(this.random.nextInt(15) * 25 + 25, this.random.nextInt(15) * 25 + 25);
    }
}
