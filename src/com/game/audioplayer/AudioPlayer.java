package com.game.audioplayer;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Jason Anthony Galicia
 */
public class AudioPlayer {
    
    private AudioInputStream audioIn;
    private Clip clip;
    
    /**
     * Plays a sound that must be through a .wav file.
     * @param file The File path in a String format
     */
    public void playSound(String file) {
        try {
            if (file == null) return;
            audioIn = AudioSystem.getAudioInputStream(AudioPlayer.class.
                    getResource(file));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch (IOException | LineUnavailableException | 
                UnsupportedAudioFileException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * This method is used if we need to stop the audio
     */
    public void stop() {
        clip.stop();
    }
}
