package MediaPlayer;

public class MacOSMediaPlayerFactory extends MediaPlayerFactory {

    public PlayerVideo createPlayerVideo() {
        return new QuickTime();
    }

    public PlayerMusique createPlayerMusique() {
        return new iTunes();
    }
}
