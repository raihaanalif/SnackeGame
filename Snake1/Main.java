/**
 *
 * @author Muhajir
 */

import javax.swing.*;

public class Main {
    
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    private static final String TITLE = "2D Snake";
    
    private static Renderer renderer;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        renderer = new Renderer();
        frame.add(renderer);
        
        frame.setVisible(true);
    }
    
}