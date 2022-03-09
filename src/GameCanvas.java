import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;

public class GameCanvas extends Canvas implements KeyListener, ActionListener {
    Rocket rocket;

    GameCanvas(){

        rocket = new Rocket(10,50,500);
        setBackground(Color.black);
        setFocusable(true);

    }


    public void paint(Graphics2D g){
        g.draw(rocket.getShape());
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

        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){

        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
