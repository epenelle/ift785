package MediaPlayer;

public class MacOSXMediaPlayerFactory extends MediaPlayerFactory {

    public PlayerVideo createPlayerVideo() {
        return new QuickTime();
    }

    public PlayerMusique createPlayerMusique() {
        return new iTunes();
    }
}
