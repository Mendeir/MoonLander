import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Terrain
{
    int stars = 100;
    boolean fix =false;
     int[] terrainX_axis = {-100 ,30 ,40 ,100,140,160,180,200,220,230,300,310,330,350,
             360,400,410,435,460,465,500,545,560,575,580,600,640,665,710,750,780,800,
             830,860,890,910,960,1010,1060,1110,1160,1210,1240,1280,1300,0};

     int[] terrainY_axis = {800,500,570,570,400,450,445,530,540,500,500,570,565,570,
             565,600,450,400,410,450,460,530,505,515,530,550,450,450,550,550,350,380,
             530,500,580,470,430,500,450,580,650,650,490,490,700,700};

    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint LightToDark = new GradientPaint(0, 200, Color.LIGHT_GRAY,
                50, 700, Color.DARK_GRAY);

        g2.setPaint(LightToDark);
        g2.fillPolygon(terrainX_axis, terrainY_axis, terrainX_axis.length);

    }

    public Shape shape()
    {
        return new Polygon(terrainX_axis, terrainY_axis, terrainX_axis.length);
    }



//    public void drawStars(Graphics g){
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.WHITE);
//        for (int count = 0; count <= stars; count++) {
//            int x_axis = (int) (Math.random() *1500) % 2000;
//            int y_axis = (int) (Math.random() * 1500) % 2000;
//            g2.drawLine(x_axis, y_axis, x_axis, y_axis);
//        }
//    }
}