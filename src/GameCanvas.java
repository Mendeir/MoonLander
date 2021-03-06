import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

public class GameCanvas extends JPanel implements KeyListener, ActionListener{

    private Rocket rocket;
    private Timer timer;

    private int time,time_min,time_ten, time_sec;
    private int xPos;
    private int yPos;
    private int fuel;
    private int delay;
    private int gameScore;
    private int scoreMultiplier;
    private int difficultyMultiplier;
    private int finalGameScore;
    private boolean landed;
    private double rotation;

    private final double THRUST_CONSTANT = 0.5;
    private final double GRAVITY_CONSTANT = 0.3;

    private Terrain terrain = new Terrain();
    private int level;

    //Game Conditions
    private boolean successfulLanding;
    private boolean failLanding;
    private boolean gameStarted;
    private boolean onSplashScreen;
    private boolean onMainMenu;
    private JPanel mainMenuPanel;

    public GameCanvas(){
        //Rocket Attributes
        xPos = 10;
        yPos = 50;
        fuel = 500;
        delay = 100;

        //Rocket State
        landed = false;
        successfulLanding = false;
        failLanding = false;
        rotation = 0;

        //Game Information
        level = 0;
        time = 0;
        gameScore = 5000;
        difficultyMultiplier = 1;
        onSplashScreen = true;
        gameStarted = false;
        rocket = new Rocket(xPos,yPos,fuel);
        timer = new Timer(delay, this);
        timer.setInitialDelay(1000);
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

        if (!landed)
            gameScore -= 1;

        if (landed) {
            timer.restart();
            timer.stop();

        }

        if (gameStarted) {
            Graphics2D graphic2D = (Graphics2D)graphic;

            BufferedImage gameView = bufferedGame();

            timer.start();

            graphic2D.drawImage(gameView,0,0,null);
            terrain.draw(graphic);


        }

        if(level == 1){
            terrain.levelOneLander(graphic);

        }

        if(level == 2){
            terrain.levelTwoLander(graphic);

        }

        if(level == 3){
            terrain.levelThreeLander(graphic);

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

        if(checkLandingCollision()){
            successfulLanding = true;
            landed = true;
            System.out.println("LANDED");
        }

        if(checkTerrainCollision()){
            failLanding = true;
            landed = true;
            System.out.println("GameOver");
        }


        g.draw(rocket.getFlameShape());

        paintDetails(g);
        if(landed){
            paintResult(g);
        }
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

            fuel--;
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

    public void paintDetails(Graphics2D g2){
        Font font = new Font("Monospaced", Font.PLAIN,14);
        g2.setFont(font);
        g2.drawString("Time: " + time_min + ":" + time_ten + time_sec,1000,40);
        g2.drawString("Fuel: " + fuel,1000,90);
        g2.drawString("Score: " + gameScore, 1000, 140);
    }

    public void paintResult(Graphics2D g2){
        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\font\\gameFont.ttf")).deriveFont(30f);
            g2.setFont(font);
            if(successfulLanding){
                g2.drawString("Level Finished",500,200);

            }
            if(failLanding){
                g2.drawString("Game Over",500,200);
            }
            g2.drawString("Press m to continue...",500,300);
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }
    }

    private boolean checkTerrainCollision()
    {
        /* Get bounding box of lander. */
        Rectangle2D boundingBox = rocket.getBoundingBox();

        /* Check intersection with terrain. */
        return terrain.shape().intersects(boundingBox);
    }

    private boolean checkLandingCollision()
    {
        Rectangle2D boundingBox = rocket.getBoundingBox();

        return terrain.getLandingPad().intersects(boundingBox);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        timeRocket();
        if(!landed && time % 50 == 0){
            updateRocketMomentum();
            updateRocketPosition();
        }

        rocket.setShape(rocket.initializeLanderShape(rocket.getRocketTop()));
        rocket.setShape(rocket.getNewRotatedShape(rocket.getShape(),rotation));
        rocket.setFlameShape(rocket.initializeThrusterShape(rocket.getRocketTop()));
        rocket.setFlameShape(rocket.getNewRotatedShape(rocket.getFlameShape(),rotation,rocket.getRocketTop()));
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void timeRocket(){
        time += delay;
        time_sec += time / 1000;
        if(time >= 1000){ time = 0; }

        if(time_sec > 9){
            time_sec = 0;
            time_ten += 1;
        }
        if(time_ten > 5){
            time_ten = 0;
            time_min += 1;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            if(fuel != 0)
                rocket.setThrusting(true);
            else
                rocket.setThrusting(false);
        }

        if(e.getKeyCode() == e.VK_LEFT){
            rotation -= 1.5;
            rocket.setRotation(rotation);
        }

        if(e.getKeyCode() == e.VK_RIGHT){
            rotation += 1.5;
            rocket.setRotation(rotation);
        }

        if(e.getKeyCode() == e.VK_M) {
            if (landed) {
                System.out.println(gameScore);
                finalGameScore += fuel + gameScore;
                scoreMultiplier *= difficultyMultiplier;
                finalGameScore *= scoreMultiplier;
                xPos = 10;
                yPos = 50;
                rotation = 0;
                Point2D new_rocketTop = new Point2D.Double(xPos,yPos);
                rocket.setRocketTop(new_rocketTop);
                successfulLanding = false;
                failLanding = false;
                rocket.setHorizontalForce(0);
                rocket.setVerticalForce(0);
                System.out.println(finalGameScore);
            }
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

    public void setLevel(int level) { this.level = level; }

    public void setScoreMultiplier(int scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
    }

    public void setDifficultyMultiplier(int difficultyMultiplier) {
        this.difficultyMultiplier = difficultyMultiplier;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getFinalGameScore() {
        return finalGameScore;
    }

    public int getLevel() {
        return level;
    }

    public void setLanded(boolean landed) { this.landed = landed; }

    public void setTime(int time) {
        this.time = time;
    }

    public void setTime_min(int time_min) {
        this.time_min = time_min;
    }

    public void setTime_ten(int time_ten) {
        this.time_ten = time_ten;
    }

    public void setTime_sec(int time_sec) {
        this.time_sec = time_sec;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
}
