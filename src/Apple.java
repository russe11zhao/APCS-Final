import processing.core.PApplet;

public class Apple extends Elements{
    private int radius;

    public Apple(PApplet applet){
        super(applet);
        int x = ((int)(Math.random()*19))*30+15;
        int y = ((int)(Math.random()*19))*30+15;
        setPos(x,y);
        radius = 30;
    }

    public boolean collision(Snake s){
        int x = s.getX();
        int y = s.getY();
        if (x == getX()-15 && y == getY()-15) {
            return true;
        }
        return false;
    }

    public void display(){
        applet.fill(applet.color(255,0,0));
        applet.stroke(applet.color(255,0,0));

        applet.ellipse(getX(),getY(),radius,radius);
    }
}

