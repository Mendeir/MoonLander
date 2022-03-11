import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameCanvas extends Canvas implements KeyListener, ActionListener {

    private Rocket rocket;

    //Game Conditions
    private boolean gameStarted;
    private boolean onSplashScreen;
    private boolean onMainMenu;

    private JPanel mainMenuPanel;

    GameCanvas(){
        onSplashScreen = true;
        gameStarted = false;

        rocket = new Rocket(10,50,500);
        addKeyListener(this);
        setBackground(Color.black);
        setFocusable(true);

    }

    public void paint(Graphics graphic){
        super.paint(graphic);

        if (onSplashScreen) {
           splashWindow(graphic);
        }

        if (onMainMenu) {
           mainMenuWindow(graphic);
        }

        if (gameStarted) {
            Graphics2D graphic2D = (Graphics2D)graphic;

            BufferedImage gameView = bufferedGame();
            graphic2D.drawImage(gameView,0,0,null);
        }

    }

    public void splashWindow(Graphics graphic) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(GameCanvas.class.getResource("/assets/images/SplashScreen720p.jpg"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        graphic.drawImage(image, 0, 0, this);
    }

    public void mainMenuWindow(Graphics graphic) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(GameCanvas.class.getResource("/assets/images/TitleScreen720p.jpg"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        graphic.drawImage(image, 0, 0, this);
    }

    public BufferedImage bufferedGame(){
        BufferedImage b = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g = b.createGraphics();

        g.setPaint(Color.white);
        g.draw(rocket.getShape());

        return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("up");
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("left");
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println("right");
        }

        if (e.getKeyCode() == KeyEvent.VK_F) {
            if (onSplashScreen) {
                onSplashScreen = false;
                onMainMenu = true;
            }

            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
