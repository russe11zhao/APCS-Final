import processing.core.*;
import java.util.ArrayList;

public class SnakeGame extends PApplet {
    private Snake s;
    private ArrayList<Snake> snake;
    private ArrayList<Snake> snake2;
    private Snake s2;
    private int delay;
    private ArrayList<Elements> fieldList;
    private boolean loss1 = false;
    private boolean loss2 = false;
    private boolean eat = false;
    private boolean eat2 = false;
    private boolean tie = false;
    public static boolean isServer = false;

    public void settings(){
        size(600, 600);
    }

    public void setup()
    {
        s = new Snake(this, 120,60);
        s2 = new Snake(this, 360,420);
        s.right();
        s2.left();
        Snake s1 = new Snake(this, 90,60);
        Snake s5 = new Snake(this, 60,60);
        Snake s3 = new Snake(this, 390,420);
        Snake s4 = new Snake(this, 420,420);
        snake = new ArrayList<Snake>();
        snake2 = new ArrayList<Snake>();
        fieldList = new ArrayList<Elements>();
        snake2.add(s2);
        snake2.add(s3);
        snake2.add(s4);
        snake.add(s);
        snake.add(s1);
        snake.add(s5);
    }

    public void draw()
    {
        if (!(loss1) && !(loss2) && !(tie)) {
            background(255);
            for (int x = 0; x < 600; x += 30) {
                for (int y = 0; y < 600; y += 30) {
                    if (x % 60 == 0) {
                        if (y % 60 == 0) {
                            fill(100, 255, 100);
                        } else {
                            fill(0, 175, 0);
                        }
                    } else {
                        if (!(y % 60 == 0)) {
                            fill(100, 255, 100);
                        } else {
                            fill(0, 175, 0);
                        }
                    }
                    rect(x, y, 30, -30);
                }
            }
            if (delay >= 10) {
                Snake temp = new Snake(this, s.getX(), s.getY());
                snake.add(1, temp);
                s.move();
                for (int i = 0; i < fieldList.size(); i++) {
                    if (fieldList.get(i).collision(s)) {
                        fieldList.remove(i);
                        eat = true;
                    }
                }
                if (!eat) {
                    snake.remove(snake.size() - 1);
                }
                else {
                    eat = false;
                }
            }
            for (int i = 0; i < snake.size(); i++) {
                int x = snake.get(i).getX();
                int y = snake.get(i).getY();
                fill(255, 0, 0);
                rect(x, y, 30, 30);
            }
            for (int i = 1; i<snake.size(); i++) {
                if (s.getX() == snake.get(i).getX() && s.getY() == snake.get(i).getY()) {
                    loss1 = true;
                }
            }
            //s.display();
            s.control(true);

            int num = (int) (Math.random() * 50);
            if (num < 1) {
                Apple a = new Apple(this);
                boolean inside = false;
                for (int i = 0; i < snake.size(); i++) {
                    int x = snake.get(i).getX();
                    int y = snake.get(i).getY();
                    if (a.getX() == x && a.getY() == y) {
                        inside = true;
                    }
                }
                for (int i = 0; i < snake2.size(); i++) {
                    int x = snake2.get(i).getX();
                    int y = snake2.get(i).getY();
                    if (a.getX() == x && a.getY() == y) {
                        inside = true;
                    }
                }
                if (!inside) {
                    fieldList.add(a);
                }
            }
            if (delay >= 10) {
                Snake temp = new Snake(this, s2.getX(), s2.getY());
                snake2.add(1, temp);
                s2.move();
                for (int i = 0; i < fieldList.size(); i++) {
                    if (fieldList.get(i).collision(s2)) {
                        fieldList.remove(i);
                        eat2 = true;
                    }
                }
                if (!eat2) {
                    snake2.remove(snake2.size() - 1);
                }
                else {
                    eat2 = false;
                }
                delay = 0;
            } else {
                delay++;
            }
            for (int i = 0; i < snake2.size(); i++) {
                int x = snake2.get(i).getX();
                int y = snake2.get(i).getY();
                fill(0, 0, 255);
                rect(x, y, 30, 30);
            }
            for (int i = 1; i<snake2.size(); i++) {
                if (s2.getX() == snake2.get(i).getX() && s2.getY() == snake2.get(i).getY()) {
                    loss2 = true;
                }
            }
            //s.display();
            String command = commandPressed();
            s2.controlFromNetwork(false, command);

            for (int i = 0; i < fieldList.size(); i++) {
                fieldList.get(i).display();
            }
            if (s.getX() > 600 || s.getY()>545 || s.getX() < 0 || s.getY() < 0 ) {
                loss1 = true;
            }
            if (s2.getX() > 600 || s2.getY()>545 || s2.getX() < 0 || s2.getY() < 0 ) {
                loss2 = true;
            }
            if (s2.getX() == s.getX() && s2.getY() == s.getY()) {
                tie = true;
            }
            for (int i = 1; i<snake.size(); i++) {
                if (s2.getX() == snake.get(i).getX() && s2.getY() == snake.get(i).getY()) {
                    loss2 = true;
                }
            }
            for (int i = 1; i<snake2.size(); i++) {
                if (s.getX() == snake2.get(i).getX() && s.getY() == snake2.get(i).getY()) {
                    loss1 = true;
                }
            }
        }
        else {
            if (tie) {
                background(0,255,0);
            }
            else if (loss1) {
                background(0, 0, 255);
            }
            else if (loss2) {
                background(255,0,0);
            }
        }
    }

    public String commandPressed(){
        try{
            String command = Server.clientInput.readLine();
            return command;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
