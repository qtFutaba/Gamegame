package Game.Music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer
{
    private Clip clip;

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
}
