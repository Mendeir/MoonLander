
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow implements KeyListener, ActionListener {

    //Screen Dimension
    int gameWidth = 1280;
    int gameHeight = 720;

    //Canvas
    GameCanvas canvas;

    //JFrames
    JFrame canvasFrame;
    JFrame splashFrame;

    //JPanels
    JPanel splashPanel;
    JPanel mainMenuButtonsPanel;

    //Main Menu Buttons
    JButton newGameButton;
    JButton settingButton;
    JButton rankingButton;
    JButton exitButton;

    public GameWindow() {
        canvas = new GameCanvas();
    }

    public void createAndShowCanvas() {
        canvasFrame = new JFrame("Moon Lander");

        canvasFrame.setSize(gameWidth, gameHeight);
        canvasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvasFrame.setVisible(true);
        canvasFrame.setLocationRelativeTo(null);

        //Main Menu Buttons
        createMainMenuButtons();


        canvas.addKeyListener(this);
        canvasFrame.add(canvas);
    }

    public void createMainMenuButtons() {
        mainMenuButtonsPanel = new JPanel();
        mainMenuButtonsPanel.setBounds((gameWidth - 400) / 2, 300, 400, 300);
        mainMenuButtonsPanel.setBackground(Color.BLUE);
        mainMenuButtonsPanel.setVisible(false);

        newGameButton = new JButton("New Game");
        newGameButton.setBounds(0, 0, 400, 75);
        settingButton = new JButton("Settings");
        settingButton.setBounds(0, 75, 400, 75);
        rankingButton = new JButton("Rankings");
        rankingButton.setBounds(0, 150, 400, 75);
        exitButton = new JButton("Exit");
        exitButton.setBounds(0, 225, 400, 75);

        mainMenuButtonsPanel.setLayout(null);
        mainMenuButtonsPanel.add(newGameButton);
        mainMenuButtonsPanel.add(settingButton);
        mainMenuButtonsPanel.add(rankingButton);
        mainMenuButtonsPanel.add(exitButton);


        canvasFrame.add(mainMenuButtonsPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action Performed");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("up");
        }

        if (e.getKeyCode() == KeyEvent.VK_F) {
            if(canvas.isOnSplashScreen()) {
                canvas.setOnSplashScreen(false);
                canvas.setOnMainMenu(true);
                mainMenuButtonsPanel.setVisible(true);
                System.out.println("Press F");
            }

            canvas.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    /*public void createAndShowSplashScreen () {
        splashFrame = new JFrame("Splash Screen");
        splashPanel = new JPanel();

        splashFrame.setSize(gameWidth, gameHeight);
        splashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splashFrame.setLocationRelativeTo(null);
        splashFrame.setVisible(true);

        ImageIcon img = new ImageIcon("SplashScreen.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, gameWidth, gameHeight);

        //splashPanel.add(background);
        splashFrame.add(background);

    }*/

}
