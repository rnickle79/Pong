import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
    private Game game;

    public KeyInputs() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(game == null) game = Game.getInstance();
        switch(e.getKeyCode()){
            case KeyEvent.VK_W: game.getPlayer1().setUp(true); break;
            case KeyEvent.VK_UP: game.getPlayer2().setUp(true); break;
            case KeyEvent.VK_S: game.getPlayer1().setDown(true); break;
            case KeyEvent.VK_DOWN: game.getPlayer2().setDown(true); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(game == null) game = Game.getInstance();
        switch(e.getKeyCode()){
            case KeyEvent.VK_W: game.getPlayer1().setUp(false); break;
            case KeyEvent.VK_UP: game.getPlayer2().setUp(false); break;
            case KeyEvent.VK_S: game.getPlayer1().setDown(false); break;
            case KeyEvent.VK_DOWN: game.getPlayer2().setDown(false); break;
        }
    }
}
