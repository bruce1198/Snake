package snake.client;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.JFrame;

public class MusicThread extends Thread {
	public static float volume = 0.4f;
	
	Clip clip;
	FloatControl fc;
	JFrame jFrame;
	MusicThread(JFrame jFrame) throws IOException, LineUnavailableException {
        AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(".\\source\\music\\bgm.wav"));
	        clip = AudioSystem.getClip();
		} catch (UnsupportedAudioFileException e1) {}
        clip.open(audioInputStream);
        fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        this.jFrame = jFrame;
	}
	@Override
	public void run() {
        clip.loop(-1);
		while(true) {
			if(jFrame.isFocused()) {
				double gain = volume;
	        	float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
	        	fc.setValue(dB);
	        	/*try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
		}
	}
}
