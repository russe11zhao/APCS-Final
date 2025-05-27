import processing.core.PApplet;

public class Elements {
    public PApplet applet;
    private int x,y;
    private int speedX, speedY;
    private int color;

    public Elements(PApplet initApplet){
        applet = initApplet;
        x = 0; y = 0;
        speedX = 0; speedY = 0;
        color = applet.color(255,255,0);
    }

    public Elements(PApplet initApplet, int initX, int initY){
        applet = initApplet;
        x = initX;
        y = initY;
        speedX = 0;
        speedY = 0;
        color = applet.color(255,255,0);
    }

    public Elements(PApplet initApplet, int initX, int initY, int initXS, int initYS){
        applet = initApplet;
        x = initX;
        y = initY;
        speedX = initXS;
        speedY = initYS;
        color = applet.color(255,255,0);
    }

    public boolean collision(Snake s){
        return false;
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
    public void setSpeed(int initX, int initY){
        speedX = initX;
        speedY = initY;
    }

    public void display(){
        applet.fill(color);
        applet.stroke(color);
        int displayX = x - 10;
        int displayY = y - 10;
        applet.rect(displayX,displayY,20,20);
    }
}

