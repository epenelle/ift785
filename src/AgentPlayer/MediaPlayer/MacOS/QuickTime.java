package AgentPlayer.MediaPlayer.MacOS;

import AgentPlayer.MediaPlayer.Players.PlayerVideo;
import Multimedia.Multimedia;

public class QuickTime implements PlayerVideo {

    Multimedia video;

    public void pause() {
        System.out.println("Paused " + video + " on QuickTime.");
    }

    public void close() {
        System.out.println("Closed " + video + "media on QuickTime.");
    }

    public void play() {
        System.out.println("Playing " + video + "media on QuickTime.");
    }

    @Override
    public void setMedia(Multimedia multimedia) {
        this.video = multimedia;
    }

    @Override
    public Multimedia getMultimedia() {
        return video;
    }

}