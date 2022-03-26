
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class GameWindow implements KeyListener, ActionListener {

    //Username
    private String username;

    //Screen Dimension
    private int gameWidth = 1280;
    private int gameHeight = 720;

    //Canvas
    private GameCanvas canvas;

    //JFrames
    private JFrame canvasFrame;
    private JFrame splashFrame;

    //JPanels
    private JPanel splashPanel;
    private JPanel mainMenuButtonsPanel;
    private JPanel newGamePanel;
    private JPanel settingButtonPanel;
    private JPanel rankingsButtonPanel;
    private JPanel levelSelectPanel;


    private JLabel namePromptLabel;
    private JLabel levelSelectLabel;
    private JLabel settingDifficultyLabel;
    private JLabel rankingLabel;
    private JLabel rankingLevelOneLabel;
    private JLabel rankingLevelTwoLabel;
    private JLabel rankingLevelThreeLabel;
    private JLabel settingSoundsLabel;
  
    //Main Menu Buttons
    private JButton newGameButton;
    private JButton settingButton;
    private JButton rankingButton;
    private JButton exitButton;
    private JButton mainMenuBackButton;
    private JButton submitName;
    private JButton levelOneButton;
    private JButton levelTwoButton;
    private JButton levelThreeButton;
    private JButton levelSelectBack;
    private JButton settingEasyButton;
    private JButton settingHardButton;
    private JButton settingPlayButton;
    private JButton settingMuteButton;

    //JTextField
    private JTextField nameInput;

    //Fonts
    private Font gameFont;

    //Audio
    private Clip clip;

    //JSlider
    //JSlider soundSlider;

    public GameWindow() {
        canvas = new GameCanvas();

        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\font\\gameFont.ttf")).deriveFont(30f);
            GraphicsEnvironment graphicEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\font\\gameFont.ttf")));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src\\assets\\sounds\\Space-bleeps-electronic-music-loop.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
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
        createLevelSelectButton();

        canvas.addKeyListener(this);
        canvasFrame.add(canvas);
    }

    public void createMainMenuBackButton() {
        mainMenuBackButton = new JButton ("Back");
        mainMenuBackButton.setBorderPainted(false);
        mainMenuBackButton.setContentAreaFilled(false);
        mainMenuBackButton.setFont(gameFont);
        mainMenuBackButton.setForeground(Color.white);
        mainMenuBackButton.setBounds(0, 0, 150, 50);
        mainMenuBackButton.addActionListener(this);
        mainMenuBackButton.setVisible(true);
    }

    public void createMainMenuButtons() {
        mainMenuButtonsPanel = new JPanel();
        mainMenuButtonsPanel.setBounds((gameWidth - 400) / 2, 300, 400, 300);
        mainMenuButtonsPanel.setVisible(false);

        newGameButton = new JButton("New Game");
        newGameButton.setBorderPainted(false);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setFont(gameFont);
        newGameButton.setForeground(Color.white);
        newGameButton.setBounds(0, 0, 400, 60);
        newGameButton.addActionListener(this);
        mainMenuButtonsPanel.add(newGameButton);

        settingButton = new JButton("Settings");
        settingButton.setBorderPainted(false);
        settingButton.setContentAreaFilled(false);
        settingButton.setFont(gameFont);
        settingButton.setForeground(Color.white);
        settingButton.setBounds(0, 75, 400, 60);
        settingButton.addActionListener(this);
        mainMenuButtonsPanel.add(settingButton);

        rankingButton = new JButton("Rankings");
        rankingButton.setBorderPainted(false);
        rankingButton.setContentAreaFilled(false);
        rankingButton.setFont(gameFont);
        rankingButton.setForeground(Color.white);
        rankingButton.setBounds(0, 150, 400, 60);
        rankingButton.addActionListener(this);
        mainMenuButtonsPanel.add(rankingButton);

        exitButton = new JButton("Exit");
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFont(gameFont);
        exitButton.setForeground(Color.white);
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
        nameInput.setHorizontalAlignment(JLabel.CENTER);
        newGamePanel.add(nameInput);

        submitName = new JButton("Start");
        submitName.setBorderPainted(false);
        submitName.setContentAreaFilled(false);
        submitName.setFont(gameFont);
        submitName.setForeground(Color.white);
        submitName.setBounds(490, 320, 150, 50);
        submitName.addActionListener(this);
        newGamePanel.add(submitName);

        canvasFrame.add(newGamePanel);
    }

    public void createSettingButtons() {
        settingButtonPanel = new JPanel();
        settingButtonPanel.setBounds((gameWidth - 1100) / 2, 40, 1100, 600);
        settingButtonPanel.setBackground(new Color(0, 0, 0, (float) 0.7));
        settingButtonPanel.setLayout(null);
        settingButtonPanel.setVisible(false);

        settingDifficultyLabel = new JLabel("Difficulty");
        settingDifficultyLabel.setBounds(475, 300, 200, 50);
        settingDifficultyLabel.setFont(gameFont);
        settingDifficultyLabel.setForeground(Color.WHITE);
        settingButtonPanel.add(settingDifficultyLabel);

        settingEasyButton = new JButton("Easy");
        settingEasyButton.setBounds(450, 380, 200, 50);
        settingEasyButton.setBorderPainted(false);
        settingEasyButton.setContentAreaFilled(false);
        settingEasyButton.setFont(gameFont);
        settingEasyButton.setForeground(Color.white);
        settingEasyButton.addActionListener(this);
        settingButtonPanel.add(settingEasyButton);

        settingHardButton = new JButton("Hard");
        settingHardButton.setBounds(450, 460, 200, 50);
        settingHardButton.setBorderPainted(false);
        settingHardButton.setContentAreaFilled(false);
        settingHardButton.setFont(gameFont);
        settingHardButton.setForeground(Color.white);
        settingHardButton.addActionListener(this);
        settingButtonPanel.add(settingHardButton);

        settingSoundsLabel = new JLabel("Sounds");
        settingSoundsLabel.setBounds(490, 100, 200, 50);
        settingSoundsLabel.setFont(gameFont);
        settingSoundsLabel.setForeground(Color.WHITE);
        settingButtonPanel.add(settingSoundsLabel);

        settingPlayButton = new JButton("Play");
        settingPlayButton.setBounds(300, 180, 200, 50);
        settingPlayButton.setBorderPainted(false);
        settingPlayButton.setContentAreaFilled(false);
        settingPlayButton.setFont(gameFont);
        settingPlayButton.setForeground(Color.white);
        settingPlayButton.addActionListener(this);
        settingButtonPanel.add(settingPlayButton);

        settingMuteButton = new JButton("Mute");
        settingMuteButton.setBounds(600, 180, 200, 50);
        settingMuteButton.setBorderPainted(false);
        settingMuteButton.setContentAreaFilled(false);
        settingMuteButton.setFont(gameFont);
        settingMuteButton.setForeground(Color.white);
        settingMuteButton.addActionListener(this);
        settingButtonPanel.add(settingMuteButton);


        canvasFrame.add(settingButtonPanel);
    }

    public void createRankingButton() {
        rankingsButtonPanel = new JPanel();
        rankingsButtonPanel.setBounds((gameWidth - 1100) / 2, 40, 1100, 600);
        rankingsButtonPanel.setBackground(new Color(0, 0, 0, (float) 0.7));
        rankingsButtonPanel.setLayout(null);
        rankingsButtonPanel.setVisible(false);

        rankingLabel = new JLabel("Ranking");
        rankingLabel.setBounds(470, 100, 200, 50);
        rankingLabel.setFont(gameFont);
        rankingLabel.setForeground(Color.WHITE);
        rankingsButtonPanel.add(rankingLabel);

        rankingLevelOneLabel = new JLabel("Level 1");
        rankingLevelOneLabel.setBounds(190, 180, 200, 50);
        rankingLevelOneLabel.setFont(gameFont);
        rankingLevelOneLabel.setForeground(Color.WHITE);
        rankingsButtonPanel.add(rankingLevelOneLabel);

        rankingLevelTwoLabel = new JLabel("Level 2");
        rankingLevelTwoLabel.setBounds(480, 180, 200, 50);
        rankingLevelTwoLabel.setFont(gameFont);
        rankingLevelTwoLabel.setForeground(Color.WHITE);
        rankingsButtonPanel.add(rankingLevelTwoLabel);

        rankingLevelThreeLabel = new JLabel("Level 3");
        rankingLevelThreeLabel.setBounds(770, 180, 200, 50);
        rankingLevelThreeLabel.setFont(gameFont);
        rankingLevelThreeLabel.setForeground(Color.WHITE);
        rankingsButtonPanel.add(rankingLevelThreeLabel);

        canvasFrame.add(rankingsButtonPanel);
    }

    public void createLevelSelectButton() {
        levelSelectPanel = new JPanel();
        levelSelectPanel.setBounds((gameWidth - 1100) / 2, 40, 1100, 600);
        levelSelectPanel.setBackground(new Color(0, 0, 0, (float) 0.7));
        levelSelectPanel.setLayout(null);
        levelSelectPanel.setVisible(false);

        levelSelectLabel = new JLabel("Level Select");
        levelSelectLabel.setBounds(475, 200, 200, 50);
        levelSelectLabel.setFont(gameFont);
        levelSelectLabel.setForeground(Color.WHITE);
        levelSelectPanel.add(levelSelectLabel);

        levelOneButton = new JButton("Level 1");
        levelOneButton.setBorderPainted(false);
        levelOneButton.setContentAreaFilled(false);
        levelOneButton.setFont(gameFont);
        levelOneButton.setForeground(Color.white);
        levelOneButton.setBounds(460, 260, 200, 50);
        levelOneButton.addActionListener(this);
        levelSelectPanel.add(levelOneButton);

        levelTwoButton = new JButton("Level 2");
        levelTwoButton.setBorderPainted(false);
        levelTwoButton.setContentAreaFilled(false);
        levelTwoButton.setFont(gameFont);
        levelTwoButton.setForeground(Color.white);
        levelTwoButton.setBounds(460, 320, 200, 50);
        levelTwoButton.addActionListener(this);
        levelSelectPanel.add(levelTwoButton);

        levelThreeButton = new JButton("Level 3");
        levelThreeButton.setBorderPainted(false);
        levelThreeButton.setContentAreaFilled(false);
        levelThreeButton.setFont(gameFont);
        levelThreeButton.setForeground(Color.white);
        levelThreeButton.setBounds(460, 380, 200, 50);
        levelThreeButton.addActionListener(this);
        levelSelectPanel.add(levelThreeButton);

        levelSelectBack = new JButton("Back");
        levelSelectBack.setBorderPainted(false);
        levelSelectBack.setContentAreaFilled(false);
        levelSelectBack.setFont(gameFont);
        levelSelectBack.setForeground(Color.white);
        levelSelectBack.setBounds(460, 440, 200, 50);
        levelSelectBack.addActionListener(this);
        levelSelectPanel.add(levelSelectBack);

        canvasFrame.add(levelSelectPanel);
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

          if (event.getSource() == settingEasyButton) {
             canvas.setDifficultyMultiplier(1);
             canvas.setFuel(500);
          }

         if (event.getSource() == settingHardButton) {
             canvas.setDifficultyMultiplier(2);
             canvas.setFuel(250);
         }

        if (event.getSource() == settingPlayButton) {
            clip.start();
        }

        if (event.getSource() == settingMuteButton) {
            clip.stop();
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
         }

         if (event.getSource() == submitName) {
             username = nameInput.getText();
             System.out.println(username);
             newGamePanel.setVisible(false);
             levelSelectPanel.setVisible(true);
         }

         if (event.getSource() == levelSelectBack) {
             levelSelectPanel.setVisible(false);
             newGamePanel.setVisible(true);
         }

         if (event.getSource() == levelOneButton) {
             levelSelectPanel.setVisible(false);
             canvas.setOnSplashScreen(false);
             canvas.setGameStarted(true);
             canvas.setLevel(1);
             canvas.setScoreMultiplier(1);
             canvas.repaint();
         }

         if (event.getSource() == levelTwoButton) {
             levelSelectPanel.setVisible(false);
             canvas.setOnSplashScreen(false);
             canvas.setGameStarted(true);
             canvas.setLevel(2);
             canvas.setScoreMultiplier(2);
             canvas.repaint();
             System.out.println("Level 2");
         }

         if (event.getSource() == levelThreeButton) {
             levelSelectPanel.setVisible(false);
             canvas.setOnSplashScreen(false);
             canvas.setGameStarted(true);
             canvas.setScoreMultiplier(4);
             canvas.setLevel(3);
             System.out.println("Level 3");
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

        if (e.getKeyCode() == KeyEvent.VK_M) {
            canvas.setOnSplashScreen(true);
            canvas.setGameStarted(false);
            canvas.setLevel(0);
            mainMenuButtonsPanel.setVisible(true);
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
