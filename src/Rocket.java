import java.awt.*;
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
        this.verticalForce = 0;
        this.rotation = 0;
        this.scale = 1;
        this.fuel = fuel;
        this.isThrusting = false;
        rocketTop = new  Point2D.Double(xPosition, yPosition);
        rocketShape =  initializeLanderShape(rocketTop);
        flameShape = initializeThrusterShape(rocketTop);
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
}
