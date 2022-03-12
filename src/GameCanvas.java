import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;

public class GameCanvas extends Canvas implements KeyListener, ActionListener {

    Rocket rocket;
    public int xPos = 80;
    public int yPos = 50;
    private double rotation = 0;

    GameCanvas(){

        rocket = new Rocket(xPos,yPos,500);

        addKeyListener(this);
        setBackground(Color.black);
        setFocusable(true);

    }

    public void paint(Graphics g){

        Graphics2D gd = (Graphics2D)g;
        super.paint(g);
        BufferedImage gameView = bufferedGame();
        gd.drawImage(gameView,0,0,null);

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

    public void updateRocketPosition(){
        double x_movement = rocket.getHorizontalForce() /5;
        double y_movement = rocket.getVerticalForce() /5;
        Point2D new_rocketTop = new Point2D.Double(rocket.getX()+x_movement,rocket.getY()+y_movement);
        rocket.setRocketTop(new_rocketTop);
    }

    public void rocketMovement(){
        rocket.setShape(rocket.initializeLanderShape(rocket.getRocketTop()));
        rocket.setShape(rocket.getNewRotatedShape(rocket.getShape(),rotation));
        repaint();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action performed");

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){

            rocket.setFlameShape(rocket.initializeThrusterShape(rocket.getRocketTop()));
            rocket.setFlameShape(rocket.getNewRotatedShape(rocket.getFlameShape(),rotation,rocket.getRocketTop()));
            repaint();
            rocket.setThrusting(true);
            System.out.println(rocket.getThrusting());


        }

        if(e.getKeyCode() == e.VK_LEFT){
            System.out.println("left");
            rotation -= 1.5;
            rocket.setRotation(rotation);
            rocketMovement();
        }

        if(e.getKeyCode() == e.VK_RIGHT){
            System.out.println("right");
            rotation += 1.5;
            rocket.setRotation(rotation);
            rocketMovement();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            rocket.setThrusting(false);
            System.out.println(rocket.getThrusting());
            repaint();
        }
    }
}
