package AgentPlayer.MediaPlayer.MacOS;

import AgentPlayer.MediaPlayer.Players.PlayerMusique;
import Multimedia.Multimedia;

public class iTunes implements PlayerMusique {

    Multimedia musique;

    @Override
    public void pause() {
        System.out.println("Paused media on iTunes.");
    }

    @Override
    public void close() {
        System.out.println("Closed media on iTunes.");
    }

    @Override
    public void play() {
        System.out.println("Playing media on iTunes.");
    }

    @Override
    public void setMedia(Multimedia multimedia) {
        this.musique = multimedia;
    }

    @Override
    public Multimedia getMultimedia() {
        return musique;
    }
}