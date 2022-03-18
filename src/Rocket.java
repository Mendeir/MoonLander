import java.awt.*;
import java.awt.desktop.AboutEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Rocket {
    //Rocket Attributes
    private double horizontalForce;
    private double verticalForce;
    private double rotation;
    private int fuel;
    private boolean isThrusting;

    //Camera
    private double scale;

    //Rocket Parts
    private Point2D rocketTop;
    private Point2D rocketLefTip;
    private Point2D rocketRightTip;
    private double thrustAmount;

    //Rocket Shape
    private Shape rocketShape;
    private Shape flameShape;


    //Rocket Initialization
    public Rocket (int xPosition, int yPosition, int fuel){
        this.horizontalForce = 10;
        this.verticalForce = 10;
        this.rotation = 0;
        this.scale = 1;
        this.fuel = fuel;
        this.isThrusting = false;
        this.thrustAmount = 0.1;
        rocketTop = new  Point2D.Double(xPosition, yPosition);
        rocketShape =  initializeLanderShape(rocketTop);
        flameShape = initializeThrusterShape(rocketTop);
    }

    public Shape getNewRotatedShape(Shape shape, double rotation){
        AffineTransform affine = new AffineTransform();
        Rectangle rect = shape.getBounds();
        affine.rotate(Math.toRadians(rotation), rect.getX() + rect.width/2, rect.getY() + rect.height/2);
        Shape result_shape = affine.createTransformedShape(shape);
        return result_shape;
    }

    public Shape getNewRotatedShape(Shape shape, double rotation, Point2D center){
        AffineTransform at = new AffineTransform();
        Rectangle bounds = shape.getBounds();
        at.rotate(Math.toRadians(rotation), center.getX(), center.getY());
        Shape result_shape = at.createTransformedShape(shape);
        return result_shape;
    }

    //method to create the initial lander shape based on given center:
    public Shape initializeLanderShape(Point2D center){
        double cx = (double) center.getX();
        double cy = (double) center.getY();
        Path2D lander = new Path2D.Double();
        //if(zoomed){ scale = 4.5;} //scale up if the lander is landing - TAKEN OUT
        scale=1;
        //body rect:
        lander.moveTo(cx-6*scale,cy-3*scale);
        lander.lineTo(cx+6*scale,cy-3*scale);
        lander.lineTo(cx+6*scale,cy+3*scale);
        lander.lineTo(cx-6*scale,cy+3*scale);
        lander.lineTo(cx-6*scale,cy-3*scale);
        //Cockpit:
        lander.moveTo(cx-4*scale,cy-4*scale);
        lander.lineTo(cx-6*scale,cy-6*scale);
        lander.lineTo(cx-6*scale,cy-8*scale);
        lander.lineTo(cx-4*scale,cy-10*scale);
        lander.lineTo(cx+4*scale,cy-10*scale);
        lander.lineTo(cx+6*scale,cy-8*scale);
        lander.lineTo(cx+6*scale,cy-6*scale);
        lander.lineTo(cx+4*scale,cy-4*scale);
        //Thruster Rect:
        lander.moveTo(cx-4*scale,cy+4*scale);
        lander.lineTo(cx-4*scale,cy+6*scale);
        lander.lineTo(cx+4*scale,cy+6*scale);
        lander.lineTo(cx+4*scale,cy+4*scale);
        //lander legs:
        lander.moveTo(cx-4*scale,cy+4*scale);
        lander.lineTo(cx-8*scale,cy+10*scale);
        lander.moveTo(cx+4*scale,cy+4*scale);
        lander.lineTo(cx+8*scale,cy+10*scale);
        //set lander leg tips to be used for landing calculations
        rocketLefTip = new Point2D.Double(cx-8*scale,cy+10*scale);
        rocketRightTip = new Point2D.Double(cx+8*scale,cy+10*scale);
        //return finished lander
        return lander;
    }

    public Shape initializeThrusterShape(Point2D _lander_center){
        double cx = (double) _lander_center.getX();
        double cy = (double) _lander_center.getY();
        Path2D thruster_flame = new Path2D.Double();
        //if(zoomed){ scale = 4.5;} //scale up if the lander is landing - TAKEN OUT
        scale = 1; //otherwise leave the lander scaled down
        //if the lander is thrusting, draw the flame:
        if(thrustAmount > 0.1){
            thruster_flame.moveTo(cx-3*scale,cy+6*scale);
            thruster_flame.lineTo(cx,cy+6+(16*thrustAmount)*scale);
            thruster_flame.lineTo(cx+3*scale,cy+6*scale);
        }
        return thruster_flame;
    }

    // setters and getters
    public void setShape(Shape new_lander_shape){ rocketShape = new_lander_shape; }
    public Shape getShape(){ return rocketShape; }
    public Shape getFlameShape(){ return flameShape; }
    public double getHorizontalForce(){ return horizontalForce;}
    public double getVerticalForce(){ return verticalForce;}
    public void setHorizontalForce(double new_force){ horizontalForce = new_force; }
    public void setVerticalForce(double new_force){ verticalForce = new_force; }
    public void setRocketTop(Point2D top){ rocketTop = top; }
    public Point2D getRocketTop(){ return rocketTop; }
    public double getX(){ return rocketTop.getX(); }
    public double getY(){ return rocketTop.getY(); }
    public void setRotation(double new_rotation){ rotation = new_rotation; }
    public void setFlameShape(Shape new_shape){ flameShape = new_shape; }
    public void setThrusting(boolean gas){ isThrusting = gas; }
    public boolean getThrusting(){ return isThrusting; }
    public void setThrustAmount(double amount){ thrustAmount = amount; }
    public double getThrustAmount() {return thrustAmount; }
}
