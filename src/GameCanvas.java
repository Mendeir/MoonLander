import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

public class GameCanvas extends JPanel implements KeyListener, ActionListener{

    private Rocket rocket;

    Timer timer;
    public int xPos = 80;
    public int yPos = 50;
    private double rotation = 0;
    private final double THRUST_CONSTANT = 0.5;
    private final double GRAVITY_CONSTANT = 0.3;

    Terrain terrain = new Terrain();



    //Game Conditions
    private boolean gameStarted;
    private boolean onSplashScreen;
    private boolean onMainMenu;

    private JPanel mainMenuPanel;

    public GameCanvas(){
        onSplashScreen = true;
        gameStarted = false;

        rocket = new Rocket(xPos,yPos,500);
        timer = new Timer(100, this);



        addKeyListener(this);
        setBackground(Color.black);
        setFocusable(true);

    }

    public void paintComponent(Graphics graphic){
        super.paintComponent(graphic);

        if (onSplashScreen) {
           splashWindow(graphic);
        }

        if (onMainMenu) {
           mainMenuWindow(graphic);
        }


        if (gameStarted) {
            Graphics2D graphic2D = (Graphics2D)graphic;

            BufferedImage gameView = bufferedGame();

            if (gameStarted) {
                timer.start();
            }
            graphic2D.drawImage(gameView,0,0,null);
            terrain.draw(graphic);

        }
    }

    public void splashWindow(Graphics graphic) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(GameCanvas.class.getResource("/assets/images/SplashScreen720p.jpg")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        graphic.drawImage(image, 0, 0, this);
    }

    public void mainMenuWindow(Graphics graphic) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(GameCanvas.class.getResource("/assets/images/TitleScreen720p.jpg")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        graphic.drawImage(image, 0, 0, this);

    }

    public BufferedImage bufferedGame(){
        BufferedImage buffed = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffed.createGraphics();
        g.setPaint(Color.white);
        g.draw(rocket.getShape());

        if(rocket.getThrusting() && rocket.getThrustAmount() < 1){
            rocket.setThrustAmount(rocket.getThrustAmount() + 0.1 );
        }else if(!rocket.getThrusting() && rocket.getThrustAmount() > 0){
            rocket.setThrustAmount(rocket.getThrustAmount() - 0.1 );
        }

        g.draw(rocket.getFlameShape());

        return buffed;
    }

    public void updateRocketMomentum(){
        double x_position = rocket.getHorizontalForce();
        double y_position = rocket.getVerticalForce();
        y_position += GRAVITY_CONSTANT;
        if(rocket.getThrusting()){
            double angle = 90 - Math.abs(rotation);
            double new_y = (Math.sin(Math.toRadians(angle))) * THRUST_CONSTANT;
            double new_x = (Math.cos(Math.toRadians(angle))) * THRUST_CONSTANT;
            if(rotation < 0){
                new_x = -new_x;
            }
            else if(rotation == 0){
                new_x = 0;
            }
            x_position += new_x;
            y_position -= new_y;
        }
        rocket.setHorizontalForce(x_position);
        rocket.setVerticalForce(y_position);
    }

    public void updateRocketPosition(){
        double x_movement = rocket.getHorizontalForce() /5;
        double y_movement = rocket.getVerticalForce() /5;
        Point2D new_rocketTop = new Point2D.Double(rocket.getX()+x_movement,rocket.getY()+y_movement);
        rocket.setRocketTop(new_rocketTop);
    }

    public void gameTimer(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        updateRocketMomentum();
        updateRocketPosition();
        rocket.setShape(rocket.initializeLanderShape(rocket.getRocketTop()));
        rocket.setShape(rocket.getNewRotatedShape(rocket.getShape(),rotation));
        rocket.setFlameShape(rocket.initializeThrusterShape(rocket.getRocketTop()));
        rocket.setFlameShape(rocket.getNewRotatedShape(rocket.getFlameShape(),rotation,rocket.getRocketTop()));
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            rocket.setThrusting(true);
        }

        if(e.getKeyCode() == e.VK_LEFT){
            rotation -= 1.5;
            rocket.setRotation(rotation);
        }

        if(e.getKeyCode() == e.VK_RIGHT){
            rotation += 1.5;
            rocket.setRotation(rotation);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            rocket.setThrusting(false);
        }
    }

    //Setter and Getter

    public boolean isOnSplashScreen() {
        return onSplashScreen;
    }

    public void setOnSplashScreen(boolean onSplashScreen) {
        this.onSplashScreen = onSplashScreen;
    }

    public boolean isOnMainMenu() {
        return onMainMenu;
    }

    public void setOnMainMenu(boolean onMainMenu) {
        this.onMainMenu = onMainMenu;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
}
