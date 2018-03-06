package someRPG;

import java.io.*;
//import java.net.URL;
import javax.sound.sampled.*;
//import javax.swing.*;

public class SoundPlayer {
	
	Clip currentClip;

// Constructor
public SoundPlayer() {   
  
}

public void playSound(String path) {
	 if(currentClip == null) {
	 try {
	       // Open an audio input stream.
	  	 File soundFile = new File("./resources/" + path);
	  	 AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
	       // Get a sound clip resource.
	       Clip clip = AudioSystem.getClip();
	       
	       // Open audio clip and load samples from the audio input stream.
	       clip.open(audioIn);
	       clip.start();
	       currentClip = clip;
	    } catch (UnsupportedAudioFileException e) {
	       e.printStackTrace();
	       System.out.println("Audio file format not supported");
	    } catch (IOException e) {
	       e.printStackTrace();
	       System.out.println("Audio file not found");
	    } catch (LineUnavailableException e) {
	       e.printStackTrace();
	       System.out.println("L");
	    }
	 }
	   
}

public void stopSound() {
	 if(currentClip != null) {
	 if(currentClip.isActive()) {
	 currentClip.stop();
	 }
	 }
}
}
