import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Terrain
{
    Rectangle2D landingPad;

    private int[] terrainX_axis = {
             -100, 30 ,40 ,
             100,140,160,
             180,200,220,
             230,300,310,
             330,350, 360,
             400,410,435,
             460,465,500,
             545,560,575,
             580,600,640,
             665,710,750,
             780,800,830,
             860,890,910,
             960,1010,1060,
             1110,1160,1210,
             1240,1280,1300,0
     };



    private int[] terrainY_axis = {
             800, 500,570,
             570,400,450,
             445,530,540,
             500,500,570,
             565,570,565,
             600,450,400,
             410,450,460,
             530,505,515,
             530,550,450,
             450,550,550,
             350,380,530,
             500,580,470,
             430,500,450,
             580,650,650,
             490,490,700,
             700
     };

    Terrain(){
        landingPad = new Rectangle2D.Double(0,0,0,0);
    }

    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint LightToDark = new GradientPaint(0, 200, Color.LIGHT_GRAY,
                50, 700, Color.DARK_GRAY);

        drawStars(g);

        g2.setPaint(LightToDark);
        g2.fillPolygon(terrainX_axis, terrainY_axis, terrainX_axis.length);

    }

    public Shape shape()
    {
        return new Polygon(terrainX_axis, terrainY_axis, terrainX_axis.length);
    }

    public void drawStars(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));

        g2.drawLine(50, 150, 50, 150);
        g2.drawLine(80, 370, 80, 370);
        g2.drawLine(150, 250, 150, 250);
        g2.drawLine(200, 200, 200, 200);
        g2.drawLine(100, 50, 100, 50);


        g2.drawLine(250, 100, 250, 100);
        g2.drawLine(300, 200, 300, 200);
        g2.drawLine(350, 50, 350, 50);
        g2.drawLine(400, 100, 400, 100);
        g2.drawLine(250, 250, 250, 250);
        g2.drawLine(300, 350, 300, 350);
        g2.drawLine(200, 350, 200, 350);
        g2.drawLine(350, 500, 350, 500);
        g2.drawLine(400, 600, 400, 600);

        g2.drawLine(450, 200, 450, 200);
        g2.drawLine(500, 50, 500, 50);
        g2.drawLine(550, 100, 550, 100);
        g2.drawLine(600, 200, 600, 200);
        g2.drawLine(450, 350, 450, 350);
        g2.drawLine(500, 450, 500, 450);
        g2.drawLine(550, 550, 550, 550);
        g2.drawLine(600, 480, 600, 480);
        g2.drawLine(600, 350, 600, 350);


        g2.drawLine(650, 100, 650, 100);
        g2.drawLine(700, 200, 700, 200);
        g2.drawLine(750, 50, 750, 50);
        g2.drawLine(800, 100, 800, 100);
        g2.drawLine(630, 250, 630, 250);
        g2.drawLine(680, 400, 680, 400);
        g2.drawLine(730, 500, 730, 500);
        g2.drawLine(780, 480, 780, 480);
        g2.drawLine(780, 350, 780, 350);

        g2.drawLine(850, 200, 850, 200);
        g2.drawLine(900, 50, 900, 50);
        g2.drawLine(950, 100, 950, 100);
        g2.drawLine(1050, 200, 1050, 200);
        g2.drawLine(850, 350, 850, 350);
        g2.drawLine(900, 450, 900, 450);
        g2.drawLine(900, 250, 900, 250);
        g2.drawLine(950, 480, 950, 480);
        g2.drawLine(1050, 350, 1050, 350);

        g2.drawLine(1100, 100, 1100, 100);
        g2.drawLine(1150, 200, 1150, 200);
        g2.drawLine(1200, 50, 1200, 50);
        g2.drawLine(1250, 100, 1250, 100);
        g2.drawLine(1280, 200, 1280, 200);
        g2.drawLine(1100, 400, 1100, 400);
        g2.drawLine(1150, 500, 1150, 500);
        g2.drawLine(1200, 300, 1200, 300);
        g2.drawLine(1200, 350, 1200, 350);
        g2.drawLine(1250, 480, 1250, 480);
        g2.drawLine(1250, 350, 1250, 350);
    }



    public void levelOneLander(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Courier",Font.BOLD,10));
        g2.drawString("Land Here",238,510);

        g2.setPaint(new Color(200,200,200));
        g2.fillRoundRect(230,490,70,10,5,5);

        landingPad = new Rectangle2D.Double(230,490,70,10);
    }
    public void levelTwoLander(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Courier",Font.BOLD,12));
        g2.drawString("2x",646,460);

        g2.setPaint(new Color(200,200,200));
        g2.fillRoundRect(640,440,25,10,5,5);

        landingPad = new Rectangle2D.Double(640,440,25,10);
    }
    public void levelThreeLander(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Courier",Font.BOLD,12));
        g2.drawString("4x",1180,660);

        g2.setPaint(new Color(200,200,200));
        g2.fillRoundRect(1160,640,50,10,5,5);

        landingPad = new Rectangle2D.Double(1160,640,50,10);
    }



    public int[] getTerrainX_axis() {
        return terrainX_axis;
    }

    public int[] getTerrainY_axis() {
        return terrainY_axis;
    }

    public Rectangle2D getLandingPad() {
        return landingPad;
    }

}