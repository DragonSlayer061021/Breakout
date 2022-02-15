import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {

   private RandomGenerator ran;
    private Ball ball;
    private Paddle paddle;
    private Brick brick;
    public int lives=3;
    /*
    1)don't have lives
    2)All of the bricks only take one hit
    3)What happens if i run out of live?
    4)How do i know how many live i have left?
    5)how do i know how many bricks i have broken?
    CHALLENGES
    6)powerups
    7)levels
     */

    private int numBIR;


    @Override

    public void init(){
        numBIR=(int) (getWidth()/(Brick.WIDTH+5.0));

        for (int row = 10; row <=0 ; row++) {
            for (int col = 0; col<numBIR; col++) {
                Brick brick = new Brick(430-col/(Brick.WIDTH+5),Brick.HEIGHT+row*(Brick.HEIGHT+5),Brick.rC[row],row);
                pause(5);
                add(brick);
            }
        }

        ball = new Ball(getWidth()/2, 350, 10, this.getGCanvas());
        add(ball);

        paddle = new Paddle(230, 430, 85 ,10);
        add(paddle);


    }
    @Override

    public void run(){
        addMouseListeners();
        waitForClick();
        gameLoop();
    }

    /**
     *
     * makes sure the paddle doesn't go off-screen
     * @param me
     */
    public void mouseMoved(MouseEvent me){
        // make sure that the paddle doesn't go offscreen
        if((me.getX() < getWidth() - paddle.getWidth()/2)&&(me.getX() > paddle.getWidth() / 2)){
            paddle.setLocation(me.getX() - paddle.getWidth()/2, paddle.getY());
        }
    }

    private void gameLoop(){
        while(true){
            // move the ball
            ball.HandleMove();

            // handle collisions
            handleCollisions();

            // handle losing the ball
            if(ball.lost==true){
                ball.lost=false;
                handleLoss();
            }

            pause(10);
        }
    }

    private void handleCollisions(){
       ball.HandleMove();
        //obj can store what we hit
        GObject obj = null;

        // check to see if it is about to his something
        if (obj==null){
            //check top right
            obj=this.getElementAt(ball.getX()+ball.getWidth(),ball.getY());
        }
        if(obj==null){
           //check top left
            obj=this.getElementAt(ball.getX(),ball.getY());
        }
        if (obj==null){
            //check bottom left
            obj=this.getElementAt(ball.getX(),ball.getY()+ball.getHeight());
        }
        if (obj==null){
            //check bottom right
            obj=this.getElementAt(ball.getX()+ball.getWidth(),ball.getY()+ball.getHeight());
        }
        //check if contact
        if (obj!=null){
            //see what was hit
            if (obj instanceof Paddle){

                if (ball.getX()<(paddle.getX()-(paddle.getWidth()-(paddle.getWidth())/6)*2)){
                    ball.lBOunce();
                    //hit left side of paddle
                }else if (ball.getX()>(paddle.getX())+(paddle.getX())+(paddle.getWidth()/6)*2){
                    ball.rBounce();
                    //hit right side of paddle
                }else {
                    ball.bounce();
                    //hit middle of paddle
                }
            }

            if (obj instanceof Brick){
                ball.bounce();
                if (Brick.bLives<0){
                this.remove(obj);
                }
                brick.Hit();

            }

        }

        // if by the end of the method obj is still null, we hit nothing
    }

    private void handleLoss(){
       ball.lost=false;
        reset();
    }

    private void reset(){
        ball.setLocation(getWidth()/2,350);
        paddle.setLocation(250,430);
    }

    public static void main(String[] args) {
        new Breakout().start();
    }
}
