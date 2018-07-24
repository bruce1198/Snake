package game;

import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class GameKeyListener implements KeyListener {
    Player p1;
    Player p2;
    static Clip doo;
    static Clip re;
    static Clip mi;
    static Clip fa;
    static AudioInputStream doIn;
    static AudioInputStream reIn;
    static AudioInputStream miIn;
    static AudioInputStream faIn;
    GameKeyListener(Player player, Player player2) throws IOException,
                                                            LineUnavailableException{
        p1 = player;
        p2 = player2;
        try { 
            doIn = AudioSystem.getAudioInputStream(new File(".\\source\\music\\do.wav"));
            doo = AudioSystem.getClip();
            reIn = AudioSystem.getAudioInputStream(new File(".\\source\\music\\re.wav"));
            re = AudioSystem.getClip();
            miIn = AudioSystem.getAudioInputStream(new File(".\\source\\music\\mi.wav"));
            mi = AudioSystem.getClip();
            faIn = AudioSystem.getAudioInputStream(new File(".\\source\\music\\fa.wav"));
            fa = AudioSystem.getClip();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Can't get the music.");
        }
    }
    public void set(Player p, Player p2) {
    	this.p1 = p;
    	this.p2 = p2;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(Main.window==1) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    if(BeginWindow.choice==0)
                        Main.window = 2;
                    	Main.PAUSE = false;
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
                        if(!Main.PAUSE && p1.getSnake().dir!=0 && GameWindow.valid[0]) {
                            p1.getSnake().dir = 1;
                            //play(1);
                            GameWindow.valid[0] = false;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(!Main.PAUSE && p1.getSnake().dir!=1 && GameWindow.valid[0]) {
                            p1.getSnake().dir = 0;
                            //play(2);
                            GameWindow.valid[0] = false;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(!Main.PAUSE && p1.getSnake().dir!=3 && GameWindow.valid[0]) {
                            p1.getSnake().dir = 2;
                            //play(3);
                            GameWindow.valid[0] = false;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(!Main.PAUSE && p1.getSnake().dir!=2 && GameWindow.valid[0]) {
                            p1.getSnake().dir = 3;
                            //play(4);
                            GameWindow.valid[0] = false;
                        }
                        break;
                    //player2
                    case KeyEvent.VK_A:
                        if(!Main.PAUSE && p2.getSnake().dir!=0 && GameWindow.valid[1]) {
                            p2.getSnake().dir = 1;
                            GameWindow.valid[1] = false;
                        }
                        break;
                    case KeyEvent.VK_D:
                        if(!Main.PAUSE && p2.getSnake().dir!=1 && GameWindow.valid[1]) {
                            p2.getSnake().dir = 0;
                            GameWindow.valid[1] = false;
                        }
                        break;
                    case KeyEvent.VK_W:
                        if(!Main.PAUSE && p2.getSnake().dir!=3 && GameWindow.valid[1]) {
                            p2.getSnake().dir = 2;
                            GameWindow.valid[1] = false;
                        }
                        break;
                    case KeyEvent.VK_S:
                        if(!Main.PAUSE && p2.getSnake().dir!=2 && GameWindow.valid[1]) {
                            p2.getSnake().dir = 3;
                            GameWindow.valid[1] = false;
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                    	if(!Main.GAMEOVER)
                    		Main.PAUSE = !Main.PAUSE;
                        break;
                }
            } catch (NotEnoughSnakeException exception) {
                Main.GAMEOVER = true;
            } catch (Exception ex) {

            }
        }
        //System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {

        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    private void play(int choice) throws LineUnavailableException,
                                            IOException{
        switch(choice) {
            case 1:
                doo.open(doIn);
                doo.start();
                break;
            case 2:
                re.open(reIn);
                re.start();
                break;
            case 3:
                mi.open(miIn);
                mi.start();
                break;
            case 4:
                fa.open(faIn);
                fa.start();
                break;
        }
    }
}