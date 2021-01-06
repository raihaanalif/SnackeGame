//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.swing.JFrame;

public class Main {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    private static final String TITLE = "2D Snake";
    private static Renderer renderer;

    public Main() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Snake");
        frame.setBounds(0, 0, 700, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        renderer = new Renderer();
        frame.add(renderer);
        frame.setVisible(true);
    }
}
