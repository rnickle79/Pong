import java.awt.*;

public class Scoreboard {
    private final Font consolas = new Font("Consolas", Font.PLAIN,60);
    private final int player1X, player2X;
    private int player1Score, player2Score;

    public Scoreboard() {
        player1X = (int) (Game.WIDTH/2.0) - 80;
        player2X = (int) (Game.WIDTH/2.0) + 20;
    }

    public void player1Scored(){
        player1Score++;
    }

    public void player2Scored(){
        player2Score++;
    }

    public void update() {
        if(player1Score >= 10 || player2Score >= 10){
            Game.gameState = State.GAME_OVER;
        }
    }

    public void render(Graphics g) {
        g.setFont(consolas);
        g.setColor(Color.white);

        if(Game.gameState == State.GAME_OVER){
            g.drawString("GAME OVER", (Game.WIDTH/2)-150, Game.HEIGHT/2);
        }

        //Player 1 Score
        if(player1Score < 10){
            g.drawString(String.format("0%d", player1Score), player1X,70);
        } else {
            g.drawString(String.format("%d", player1Score), player1X, 70);
        }

        //Player 2 Score
        if(player2Score < 10){
            g.drawString(String.format("0%d", player2Score), player2X,70);
        } else {
            g.drawString(String.format("%d", player2Score), player2X,70);
        }
    }
}
