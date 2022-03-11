import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameCanvas extends Canvas implements KeyListener, ActionListener {

    Rocket rocket;
    public boolean started;

    GameCanvas(){
        started = false;
        rocket = new Rocket(10,50,500);
        addKeyListener(this);
        setBackground(Color.black);
        setFocusable(true);

    }

    public void paint(Graphics g){
        
        BufferedImage image = null;
        try {
            image = ImageIO.read(GameCanvas.class.getResource("/assets/SplashScreen720p.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graphics2D gd = (Graphics2D)g;
        super.paint(g);
        BufferedImage gameView = bufferedGame();
        //gd.drawImage(gameView,0,0,null);
        g.drawImage(image, 0, 0, this);
    }

    public BufferedImage bufferedGame(){
        BufferedImage b = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g = b.createGraphics();





        if (started) {
            g.setPaint(Color.white);
            g.draw(rocket.getShape());
        }

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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
