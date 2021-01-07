//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.swing.JFrame;

public class Main {
    //untuk menset ukuran window game diset 700 x 500
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    private static final String TITLE = "2D Snake"; //memberi judul
    private static Renderer renderer; //memanggil class renderer

    public Main() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Snake"); //memunculkan dan memberi nama judul pada window game
        frame.setBounds(0, 0, 700, 500); //mengatur batas-batas
        frame.setResizable(false); //agar tidak dapat di resize ukuran windowsnya maka resizable di set false
        frame.setDefaultCloseOperation(3); //menutup window secara default
        renderer = new Renderer(); //mendeklarasikan class renderer ke dalam kelas main
        frame.add(renderer); //memasukan class renderer ke dalam JFrame
        frame.setVisible(true);
    }
}
