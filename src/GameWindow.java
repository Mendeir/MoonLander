
import javax.swing.JFrame;

public class GameWindow {

    JFrame frame = new JFrame("Moon Lander");

    GameWindow(){
        frame.setVisible(true);
        frame.setBounds(350,40,700,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GameCanvas canvas = new GameCanvas();
        frame.add(canvas);
    }

}
