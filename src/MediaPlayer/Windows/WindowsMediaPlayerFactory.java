package MediaPlayer.Windows;

import MediaPlayer.Players.MediaPlayerFactory;
import MediaPlayer.Players.PlayerMusique;
import MediaPlayer.Players.PlayerVideo;

public class WindowsMediaPlayerFactory extends MediaPlayerFactory {

    static private WindowsMediaPlayerFactory factory;

    // On implemente un singleton pour s'assurer qu'il n'y ait qu'une seule concrete factory
    synchronized public static WindowsMediaPlayerFactory getInstance() {
        if (factory == null) {
            factory = new WindowsMediaPlayerFactory();
        }
        return factory;
    }

    private WindowsMediaPlayerFactory() {
        super();
    }

    public PlayerVideo createPlayerVideo() {
        return new WindowsMediaPlayer();
    }

    public PlayerMusique createPlayerMusique() {
        return new WindowsMediaPlayer();
    }


}
