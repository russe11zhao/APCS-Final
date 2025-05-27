import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.RIGHT;
import static processing.core.PConstants.DOWN;
import static processing.core.PConstants.UP;

public class Snake {
    private int x,y;
    private int speedX, speedY;
    private PApplet applet;
    private String direction;
    private final int blockLength = 20;
    private ArrayList<Elements> list;
    private String tempdirection;

    public Snake(PApplet p,int initX, int initY){
        x = initX;
        y = initY;
        applet = p;
        speedX = 0;
        speedY = 0;
        //right();
        //direction = "right";
        //list = new ArrayList<Elements>();
        //Elements e = new Elements(p,initX,initY);
        //list.add(e);
    }

    public void move() {
        //Elements e = new Elements(applet, x + speedX, y + speedY);
        //list.add(e);
        //list.remove(0);
        x += speedX;
        y += speedY;
        direction = tempdirection;
    }

    public void up() {
        setSpeed(0,-30);
        tempdirection = "up";
    }
    public void down() {
        setSpeed(0,30);
        tempdirection = "down";
    }
    public void left() {
        setSpeed(-30,0);
        tempdirection = "left";
    }
    public void right() {
        setSpeed(30,0);
        tempdirection = "right";
    }

    public void setSpeed(int x, int y){
        speedX = x;
        speedY = y;
    }

    public void control(boolean team){
        if (team) {
            if (applet.keyPressed) {
                if (applet.key == 'd' && !getDirection().equals("left")) {
                    right();
                }
                if (applet.key == 'a' && !getDirection().equals("right")) {
                    left();
                }
                if (applet.key == 'w' && !getDirection().equals("down")) {
                    up();
                }
                if (applet.key == 's' && !getDirection().equals("up")) {
                    down();
                }
            }
        }
        else {
            if (applet.keyPressed) {
                if (applet.keyCode == PConstants.RIGHT && !getDirection().equals("left")) {
                    right();
                }
                if (applet.keyCode == PConstants.LEFT && !getDirection().equals("right")) {
                    left();
                }
                if (applet.keyCode == PConstants.UP && !getDirection().equals("down")) {
                    up();
                }
                if (applet.keyCode == PConstants.DOWN && !getDirection().equals("up")) {
                    down();
                }
            }
        }
    }

    public void controlFromNetwork(boolean team, String command){
        if (command == null){
            return;
        }

        switch (command.toUpperCase()){
            case "UP":
                if (!getDirection().equals("down")){
                    up();
                }
                break;
            case "DOWN":
                if(!getDirection().equals("up")){
                    down();
                }
                break;
            case "LFET":
                if (!getDirection().equals("right")){
                    left();
                }
                break;
            case "RIGHT":
                if (!getDirection().equals("left")){
                    right();
                }
                break;
        }
    }

    public void display(){
        for(Elements e : list) {
            e.display();
        }
    }
    public String getDirection() {
        return direction;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }

    public void setPos(int initX, int initY){
        x = initX;
        y = initY;
    }
}

