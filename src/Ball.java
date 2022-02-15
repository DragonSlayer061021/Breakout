import acm.graphics.GCanvas;
import acm.graphics.GOval;

public class Ball extends GOval {
    private double deltaX= 1.25;
    private double deltaY =-1.25;
    private final GCanvas screen;
    public boolean lost=false;
    public Ball(double x, double y,double size, GCanvas screen){
        super(x,y,size,size);
        this.screen=screen;
        setFilled(true);

    }

    /**
     * makes the ball bounce of the walls
     * also may take off a life if goes under the paddle
     */
    public void HandleMove(){

        move(deltaX,-deltaY);

    /*
    check to see if the ball is too high
    check to see if the ball is too low
    check to see if the ball hits the left side of the screen
    check to see if the ball hits the left side of the screen
     */
        if (getY()<=0){
            //moving down
            deltaY *=-1;
        }
       if (getY()>=screen.getHeight()-getHeight()){
           //lose a life
           deltaX*=1;
           deltaY *=-1;
           lost = true;
       }

        if(getX() >= screen.getWidth()-getWidth()){
            // start moving left
            deltaX *= -1;
        }

        if(getX() <= 0){
            // start moving right
            deltaX *= -1;
        }

    }

    public void bounce(){
        deltaY *=-1;

    }

    public void  lBOunce(){
        deltaY *=-1;
        deltaX=-Math.abs(deltaX);
    }

    public void rBounce(){
        deltaY *= -1;
        deltaX = Math.abs(deltaX);
    }

}
