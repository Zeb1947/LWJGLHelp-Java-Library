package org.lwjglhelp.libraries;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class SoundManager {
    // Play the sound file
    public static void playSound(String soundFile) {
        try {
        	soundFile = Paths.get("src", soundFile).toAbsolutePath().toString();
        	
            File file = new File(soundFile);  // Path to your sound file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();  // Play the sound
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();  // Handle exceptions
        }
    }
}