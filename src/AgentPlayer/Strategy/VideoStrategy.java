package AgentPlayer.Strategy;

import AgentPlayer.MediaPlayer.Players.MediaPlayerFactory;
import AgentPlayer.MediaPlayer.Players.PlayerVideo;

public class VideoStrategy implements Strategy {

    private MediaPlayerFactory mediaPlayerFactory;

    public VideoStrategy(MediaPlayerFactory mediaPlayerFactory) {
        this.mediaPlayerFactory = mediaPlayerFactory;
    }

    @Override
    public PlayerVideo execute() {
        return mediaPlayerFactory.createPlayerVideo();
    }
}
