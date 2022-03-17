package AgentPlayer.MediaPlayer.Windows;

import AgentPlayer.MediaPlayer.Players.PlayerMusique;
import AgentPlayer.MediaPlayer.Players.PlayerVideo;
import Multimedia.Multimedia;

public class WindowsMediaPlayer implements PlayerMusique, PlayerVideo {

    Multimedia multimedia;

    public void pause() {
        System.out.println("Paused media on WindowsMediaPlayer.");
    }

    public void close() {
        System.out.println("Closed media on WindowsMediaPlayer.");
    }

    public void play() {
        System.out.println("Playing media on WindowsMediaPlayer.");
    }

    @Override
    public void setMedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

    @Override
    public Multimedia getMultimedia() {
        return multimedia;
    }

}