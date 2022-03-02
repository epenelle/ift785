package MediaPlayer;

public class LinuxMediaPlayerFactory extends MediaPlayerFactory {

    public PlayerVideo createPlayerVideo() {
        return new TotemMoviePlayer();
    }

    public PlayerMusique createPlayerMusique() {
        return new Rythmbox();
    }
}
