import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable{
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 1280/2;
    public static final int FPS_SET = 120;
    public static final int UPS_SET = 200;
    public static State gameState;

    private final Ball ball;
    private final Paddle player1;
    private final Paddle player2;
    private final Scoreboard scoreboard;

    private static Game game = null;

    private Game() {
        ball = new Ball();
        player1 = new Paddle(1);
        player2 = new Paddle(2);
        scoreboard = new Scoreboard();
    }

    public static Game getInstance(){
        if(game == null){
            game = new Game();
        }
        return game;
    }

    public void startGame(){
        initGameWindow();
        gameState = State.RUNNING;
        new Thread(this).start();
    }

    private void initGameWindow(){
        //JPanel
        setVisible(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(new KeyInputs());
        setFocusable(true);
        requestFocusInWindow();

        //JFrame
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void checkCollision(){
        // Ball collision with top/bottom of window
        //   *Switch direction
        if(ball.getY() <= 0 || ball.getY() >= Game.HEIGHT - 20 ) {
            ball.setDirY(-ball.getDirY());
        }

        // Ball collision with paddles
        //   *Switch direction
        //   *Randomly change speed if player is moving paddle
        if(ball.getBounds().intersects(player1.getBounds())){
            ball.setX(ball.getX() + 10); // prevent multiple collisions
            ball.setDirX(-ball.getDirX());
            if(player1.isUp() || player1.isDown()){
                ball.changeSpeed();
            }
        } else if (ball.getBounds().intersects(player2.getBounds())){
            ball.setX(ball.getX() - 10); // prevent multiple collisions
            ball.setDirX(-ball.getDirX());
            if(player2.isUp() || player2.isDown()){
                ball.changeSpeed();
            }
        }
    }

    private void checkGoal(){
        if(ball.getX() < -20){
            scoreboard.player2Scored();
            ball.reset();
        } else if(ball.getX() > Game.WIDTH){
            scoreboard.player1Scored();
            ball.reset();
        }
    }

    public Paddle getPlayer1() {
        return player1;
    }

    public Paddle getPlayer2() {
        return player2;
    }

    private void update(){
        checkGoal();
        checkCollision();
        if(gameState == State.RUNNING) {
            ball.update();
            player1.update();
            player2.update();
            scoreboard.update();
        }
    }

    private void render(Graphics g) {
        ball.render(g);
        player1.render(g);
        player2.render(g);
        scoreboard.render(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Background color
        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);

        // Middle line
        g.setColor(Color.white);
        g.fillRect(WIDTH/2,0, 1, HEIGHT);

        render(g);
    }

/* ---------------------------- Game Loop ------------------------------------*/
    @Override
    public void run() {
        double nanosPerFrame = 1_000_000_000.0 / FPS_SET;
        double nanosPerUpdate= 1_000_000_000.0 / UPS_SET;
        double deltaUpdate = 0;
        double deltaFrame = 0;
        //int frames = 0;
        //int updates = 0;
        long lastCheck = System.currentTimeMillis();
        long previousTime = System.nanoTime();
        long currentTime;

        while(true){
            currentTime = System.nanoTime();
            deltaUpdate += (currentTime - previousTime) / nanosPerUpdate;
            deltaFrame += (currentTime - previousTime) / nanosPerFrame;
            previousTime = currentTime;
            if(deltaUpdate >= 1){
                update();
                //updates++;
                deltaUpdate--;
            }
            if(deltaFrame >= 1){
                repaint(); // calls paintComponent()
                //frames++;
                deltaFrame--;
            }
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames + "| UPS: " + updates);
                //frames = 0;
                //updates = 0;
            }
        }
    }

/* ------------------------------Main ----------------------------------------*/
    public static void main(String[] args) {
        Game.getInstance().startGame();
    }
}
