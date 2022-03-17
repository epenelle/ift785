package AgentPlayer.MediaPlayer.Players;

import Multimedia.Multimedia;

public interface MediaPlayer {
    void pause();
    void close();
    void play();
    void setMedia(Multimedia multimedia);
    Multimedia getMultimedia();
}