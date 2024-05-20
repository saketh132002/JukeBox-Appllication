package Services;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AudioPlayerServices
{
    private Clip clip;

    public void play(String filePath)
    {
        try
        {
            File audioFile = new File(filePath);
            System.out.println(audioFile.exists());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    public void pause()
    {
        if (clip != null && clip.isRunning())
        {
            clip.stop();
        }
    }

    public void resume()
    {
        if (clip != null && !clip.isRunning())
        {
            clip.start();
        }
    }

    public void stop()
    {
        if (clip != null)
        {
            clip.stop();
            clip.close();
        }
    }

    public void forward(long milliseconds)
    {
        if (clip != null && clip.isRunning())
        {
            long newPosition = clip.getMicrosecondPosition() + (milliseconds * 1000);
            clip.setMicrosecondPosition(newPosition);
        }
    }

    public void rewind(long milliseconds)
    {
        if (clip != null && clip.isRunning())
        {
            long newPosition = clip.getMicrosecondPosition() - (milliseconds * 1000);
            if (newPosition < 0) newPosition = 0;
            clip.setMicrosecondPosition(newPosition);
        }
    }
    public static void controlPlayback (AudioPlayerServices audioPlayer, Scanner scanner)
    {
        while (true)
        {
            System.out.println("Select an option: ");
            System.out.println("1. Pause");
            System.out.println("2. Resume");
            System.out.println("3. Stop");
            System.out.println("4. Forward");
            System.out.println("5. Rewind");
            System.out.println("6. Next song");

            int choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                    audioPlayer.pause();
                    break;
                case 2:
                    audioPlayer.resume();
                    break;
                case 3:
                    audioPlayer.stop();
                    break;
                case 4:
                    System.out.println("Enter milliseconds to forward:");
                    long forwardMillis = scanner.nextLong();
                    audioPlayer.forward(forwardMillis);
                    break;
                case 5:
                    System.out.println("Enter milliseconds to rewind:");
                    long rewindMillis = scanner.nextLong();
                    audioPlayer.rewind(rewindMillis);
                    break;
                case 6:
                    audioPlayer.stop();
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}

