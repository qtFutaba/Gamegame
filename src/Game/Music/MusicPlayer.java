package Game.Music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer
{
    private Clip clip;
    private FloatControl volumeControl;

    public void play(String filePath)
    {
        try
        {
            // Load the audio file
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get the clip
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Get the volume control (if available)
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Start playback
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    public void stop()
    {
        if (clip != null && clip.isRunning())
        {
            clip.stop();
        }
    }

    public void loop()
    {
        if (clip != null)
        {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            // Convert volume percentage (0-100) to the scale the volume control expects
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float range = max - min;
            float gain = min + (range * (volume / 100f));
            volumeControl.setValue(gain);
        }
    }
}
