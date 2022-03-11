
import javax.swing.*;

public class GameWindow {

    //Screen Dimension
    int gameWidth = 1280;
    int gameHeight = 720;

    //JFrames
    JFrame frame = new JFrame("Moon Lander");
    JFrame splashFrame;

    //JPanels
    JPanel splashPanel;

    GameWindow(){

    }

    public void createAndShowCanvas() {
        frame.setVisible(true);
        frame.setBounds(350,40, gameWidth, gameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GameCanvas canvas = new GameCanvas();
        frame.add(canvas);
    }

    public void createAndShowSplashScreen () {
        splashFrame = new JFrame("Splash Screen");
        splashPanel = new JPanel();


    }

}
