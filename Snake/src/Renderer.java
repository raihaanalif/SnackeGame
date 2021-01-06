//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Renderer extends JPanel {
    GamePlay gameplay = new GamePlay(this);

    public Renderer() {
        this.addKeyListener(this.gameplay);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 500);
        this.gameplay.render((Graphics2D)g);
    }
}
