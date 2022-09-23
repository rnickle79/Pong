import java.awt.*;

public class Paddle extends GameObject {
    private final int id;
    private boolean up;
    private boolean down;
    private final int speed;

    public Paddle(int id) {
        //Player 1 on left
        super(0, (Game.HEIGHT / 2.0) - 50, 20, 100);
        //Player 2 on right
        if(id==2) x= Game.WIDTH - 20;
        this.id = id;
        speed = 5;
    }

    @Override
    public void update() {
        if(up && !down){
            y -= speed;
        } else if(down && !up) {
            y += speed;
        }
        if(y <= 0) y=0;
        if(y >= Game.HEIGHT - 100) y= Game.HEIGHT - 100;
    }

    @Override
    public void render(Graphics g) {
        if(id == 1){
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }

        g.fillRect((int)x,(int)y,w,h);
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}

