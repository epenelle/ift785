package AgentPlayer.Strategy;

import AgentPlayer.MediaPlayer.Players.MediaPlayerFactory;
import AgentPlayer.MediaPlayer.Players.PlayerMusique;

public class MusiqueStrategy implements Strategy {

    private MediaPlayerFactory mediaPlayerFactory;

    public MusiqueStrategy(MediaPlayerFactory mediaPlayerFactory) {
        this.mediaPlayerFactory = mediaPlayerFactory;
    }

    @Override
    public PlayerMusique execute() {
        return mediaPlayerFactory.createPlayerMusique();
    }
}
