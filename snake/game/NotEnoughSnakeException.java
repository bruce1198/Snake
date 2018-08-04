package snake.game;

public class NotEnoughSnakeException extends Exception {
    static final long serialVersionUID = 1;
    public NotEnoughSnakeException() {

    }
    public NotEnoughSnakeException(String msg) {
        super(msg);
    }
}