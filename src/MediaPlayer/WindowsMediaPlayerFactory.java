package MediaPlayer;

public class WindowsMediaPlayerFactory extends MediaPlayerFactory{

    public PlayerVideo createPlayerVideo() {
        return new WindowsMediaPlayer();
    }

    public PlayerMusique createPlayerMusique() {
        return new WindowsMediaPlayer();
    }


}
