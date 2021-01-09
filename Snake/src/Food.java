//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics2D;

public class Food {
    private Location location;  //melakukan pemanggilan class location
    private Random random = new Random(); //deklarasi pemanggilan fungsi random dari library java
    public int foodColor=0;

    public Food() {
        this.location = new Location(this.random.nextInt(15) * 25 + 25, this.random.nextInt(15) * 25 + 25); //penempatan makanan ular dilakukan secara random
    }

    public void render(Graphics g) { // mengatur tampilan dari makanan ular
        g.setColor(Color.WHITE);
        g.fillOval(this.location.getX(), this.location.getY(), 25, 25);
        Graphics2D g2 = (Graphics2D) g;
        if(foodColor==0) {
            g2.setColor(Color.RED);
            foodColor++;
            g2.fillOval(this.location.getX(), this.location.getY(), 25, 25);
        }
        else if(foodColor==1){
            g2.setColor(Color.blue);
            foodColor++;
            g2.fillOval(this.location.getX(), this.location.getY(), 25, 25);
        }
        else if (foodColor==2){
            g2.setColor(Color.MAGENTA);
            foodColor=0;
            g2.fillOval(this.location.getX(), this.location.getY(), 25, 25);
        }

    }

    public Location getLocation() { //mendapatkan koordinat/lokasi dari makanan, kemudian nilainya dimasukkan ke location
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void move() {
        this.location = new Location(this.random.nextInt(15) * 25 + 25, this.random.nextInt(15) * 25 + 25); //pergerakan makanan ular di dalam game setelah dimakan dilakukan secara random
    }
}
