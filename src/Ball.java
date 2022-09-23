import java.awt.*;
import java.util.Random;

public class Ball extends GameObject{
    private final Random random;
    private int dirX, dirY;

    public Ball() {
        super((Game.WIDTH/2.0)-10, (Game.HEIGHT/2.0)-10, 20,20);
        random = new Random();
        dirX = random.nextInt(2);
        dirY = random.nextInt(2);
        if(dirX == 0) dirX--;
        if(dirY == 0) dirY--;
    }

    public void changeSpeed(){
        // Random speed
        int xSpeed = random.nextInt(4)+2;
        int ySpeed = random.nextInt(4)+2;
        if(dirX > 0) dirX = xSpeed;
        if(dirX < 0) dirX = -xSpeed;
        if(dirY > 0) dirY = ySpeed;
        if(dirY < 0) dirY = -ySpeed;
    }

    public void reset(){
        x=(Game.WIDTH/2.0)-10;
        y=(Game.HEIGHT/2.0)-10;
        dirX = random.nextInt(2);
        dirY = random.nextInt(2);
        if(dirX == 0) dirX--;
        if(dirY == 0) dirY--;
    }

    @Override
    public void update() {
        y += dirY;
        x += dirX;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval((int)x, (int)y, w, h);
    }

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }
}
