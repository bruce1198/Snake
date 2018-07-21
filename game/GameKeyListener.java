package game;

import java.awt.event.*;

public class GameKeyListener implements KeyListener {
    Player p1;
    GameKeyListener(Player player) {
        p1 = player;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(Main.window==1) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    if(BeginWindow.choice==0)
                        Main.window = 2;
                    break;
                case KeyEvent.VK_UP:
                    if(BeginWindow.choice!=0) {
                        BeginWindow.choice--;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(BeginWindow.choice!=1)
                        BeginWindow.choice++;
                    break;
            }
        }
        else if(Main.window==2) {
            try {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if(!p1.getSnake().RIGHT) {
                            p1.getSnake().LEFT = true;
                            p1.getSnake().RIGHT = false;
                            p1.getSnake().UP = false;
                            p1.getSnake().DOWN = false;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(!p1.getSnake().LEFT) {
                            p1.getSnake().LEFT = false;
                            p1.getSnake().RIGHT = true;
                            p1.getSnake().UP = false;
                            p1.getSnake().DOWN = false;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(!p1.getSnake().DOWN) {
                            p1.getSnake().LEFT = false;
                            p1.getSnake().RIGHT = false;
                            p1.getSnake().UP = true;
                            p1.getSnake().DOWN = false;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(!p1.getSnake().UP) {
                            p1.getSnake().LEFT = false;
                            p1.getSnake().RIGHT = false;
                            p1.getSnake().UP = false;
                            p1.getSnake().DOWN = true;
                        }
                        break;
                }
            } catch (NotEnoughSnakeException exception) {
                Main.reset = true;
            }
        }
        //System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                p1.numberOfSnack--;
                break;
            case KeyEvent.VK_R:
                Main.reset = true;
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}