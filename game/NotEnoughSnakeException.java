package game;

public class NotEnoughSnakeException extends Exception {
    static final long serialVersionUID = 1;
    NotEnoughSnakeException() {

    }
    NotEnoughSnakeException(String msg) {
        super(msg);
    }
}