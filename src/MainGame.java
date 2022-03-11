import javax.swing.*;

public class MainGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameWindow window = new GameWindow();
                window.createAndShowCanvas();
            }
        });
    }
}
