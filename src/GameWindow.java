
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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
    JPanel newGamePanel;
    JPanel settingButtonPanel;
    JPanel rankingsButtonPanel;

    //JLabel
    JLabel namePromptLabel;

    //Main Menu Buttons
    JButton newGameButton;
    JButton settingButton;
    JButton rankingButton;
    JButton exitButton;
    JButton mainMenuBackButton;
    JButton submitName;

    //JTextField
    JTextField nameInput;

    //Fonts
    Font gameFont;

    public GameWindow() {
        canvas = new GameCanvas();

        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\font\\gameFont.ttf")).deriveFont(30f);
            GraphicsEnvironment graphicEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\font\\gameFont.ttf")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void createAndShowCanvas() {
        canvasFrame = new JFrame("Moon Lander");

        canvasFrame.setSize(gameWidth, gameHeight);
        canvasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvasFrame.setVisible(true);
        canvasFrame.setLocationRelativeTo(null);

        createMainMenuButtons();
        createNewGameButtons();
        createSettingButtons();
        createRankingButton();

        canvas.addKeyListener(this);
        canvasFrame.add(canvas);
    }

    public void createMainMenuBackButton() {
        mainMenuBackButton = new JButton ("Back");
        mainMenuBackButton.setBounds(0, 0, 150, 50);
        mainMenuBackButton.addActionListener(this);
        mainMenuBackButton.setVisible(true);
    }

    public void createMainMenuButtons() {
        mainMenuButtonsPanel = new JPanel();
        mainMenuButtonsPanel.setBounds((gameWidth - 400) / 2, 300, 400, 300);
        mainMenuButtonsPanel.setVisible(false);

        newGameButton = new JButton("New Game");
        newGameButton.setBounds(0, 0, 400, 60);
        newGameButton.addActionListener(this);
        mainMenuButtonsPanel.add(newGameButton);

        settingButton = new JButton("Settings");
        settingButton.setBounds(0, 75, 400, 60);
        settingButton.addActionListener(this);
        mainMenuButtonsPanel.add(settingButton);

        rankingButton = new JButton("Rankings");
        rankingButton.setBounds(0, 150, 400, 60);
        rankingButton.addActionListener(this);
        mainMenuButtonsPanel.add(rankingButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(0, 225, 400, 60);
        exitButton.addActionListener(this);
        mainMenuButtonsPanel.add(exitButton);

        createMainMenuBackButton();

        mainMenuButtonsPanel.setLayout(null);
        mainMenuButtonsPanel.setOpaque(false);

        canvasFrame.add(mainMenuButtonsPanel);
    }

    public void createNewGameButtons() {
        newGamePanel = new JPanel();
        newGamePanel.setBounds((gameWidth - 1100) / 2, 40, 1100, 600);
        newGamePanel.setBackground(new Color(0, 0, 0, (float) 0.7));
        newGamePanel.setLayout(null);
        newGamePanel.setVisible(false);

        namePromptLabel = new JLabel("Enter your name");
        namePromptLabel.setBounds(450, 200, 300, 50);
        namePromptLabel.setFont(gameFont);
        namePromptLabel.setForeground(Color.white);
        newGamePanel.add(namePromptLabel);

        nameInput = new JTextField();
        nameInput.setBounds(420, 250, 300, 50);
        newGamePanel.add(nameInput);



        canvasFrame.add(newGamePanel);
    }

    public void createSettingButtons() {
        settingButtonPanel = new JPanel();
        settingButtonPanel.setBounds((gameWidth - 1100) / 2, 40, 1100, 600);
        settingButtonPanel.setBackground(new Color(0, 0, 0, (float) 0.7));
        settingButtonPanel.setLayout(null);
        settingButtonPanel.setVisible(false);

        canvasFrame.add(settingButtonPanel);
    }

    public void createRankingButton() {
        rankingsButtonPanel = new JPanel();
        rankingsButtonPanel.setBounds((gameWidth - 1100) / 2, 40, 1100, 600);
        rankingsButtonPanel.setBackground(new Color(0, 0, 0, (float) 0.7));
        rankingsButtonPanel.setLayout(null);
        rankingsButtonPanel.setVisible(false);

        canvasFrame.add(rankingsButtonPanel);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
         if (event.getSource() == newGameButton) {
             mainMenuButtonsPanel.setVisible(false);
             newGamePanel.add(mainMenuBackButton);
             newGamePanel.setVisible(true);
         }

         if (event.getSource() == settingButton) {
             mainMenuButtonsPanel.setVisible(false);
             settingButtonPanel.add(mainMenuBackButton);
             settingButtonPanel.setVisible(true);

         }

         if (event.getSource() == rankingButton) {
             mainMenuButtonsPanel.setVisible(false);
             rankingsButtonPanel.add(mainMenuBackButton);
             rankingsButtonPanel.setVisible(true);
         }

         if (event.getSource() == mainMenuBackButton) {
             newGamePanel.setVisible(false);
             settingButtonPanel.setVisible(false);
             rankingsButtonPanel.setVisible(false);
             mainMenuButtonsPanel.setVisible(true);
             System.out.println("Called");
         }

         if (event.getSource() == exitButton) {
            System.exit(0);
         }
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
                canvas.repaint();
            }
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
