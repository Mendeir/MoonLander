import java.awt.*;

public class Terrain
{
    int stars = 500;

     int[] terrainX_axis = {0 ,30 ,40 ,100,140,160,180,200,220,230,300,310,330,350,
             360,400,410,435,460,465,500,545,560,575,580,600,630,655,710,0};

     int[] terrainY_axis = {500,450,520,520,350,400,395,480,490,450,450,520,515,520,
             515,550,400,350,360,400,410,480,455,465,480,500,400,400,600,600};

    public void draw(Graphics g)
    {
        GradientPaint LightToDark = new GradientPaint(0, 200, Color.LIGHT_GRAY,
                50, 700, Color.DARK_GRAY);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        for (int count = 0; count <= stars; count++) {
            int x = (int) (Math.random() * 2000) % 700;
            int y = (int) (Math.random() * 2000) % 700;
            g.drawLine(x, y, x, y);
        }
        g2.setPaint(LightToDark);
        g2.fillPolygon(terrainX_axis, terrainY_axis, terrainX_axis.length);
    }

//    public Shape shape()
//    {
//        return new Polygon(terrainX_axis, terrainY_axis, terrainX_axis.length);
//    }
}