import java.awt.*;

public abstract class GameObject {
    protected double x,y;
    protected int w,h;

    public GameObject(double x, double y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void update();
    public abstract void render(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, w, h);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
}
