import acm.graphics.GRect;
import java.awt.Color;

public class Brick extends GRect {

    public static final Color[] rC= {Colores.hue1,Colores.hue2,Colores.hue3,Colores.hue4,Colores.hue5,Colores.hue6,Colores.hue7,Colores.hue8,Colores.hue9,Colores.hue10};
    public static final int WIDTH = 44;
    public static final int HEIGHT= 20;
    public static int bLives;
    public Brick(double x, double y, Color color, int bLives ){
     super(x,y,WIDTH,HEIGHT);
     this.setFillColor(color);
     this.setFilled(true);

    }
   public void Hit(){
        this.setFillColor(rC[bLives]);
   }
}
