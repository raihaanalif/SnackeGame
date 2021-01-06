
/**
 *
 * @author Muhajir
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class Renderer extends JPanel {
    
    GamePlay gameplay;
    
    public Renderer() {
        gameplay = new GamePlay(this);
        addKeyListener(gameplay);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        
        gameplay.render( (Graphics2D) g);
    }
    
}