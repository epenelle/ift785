package MediaPlayer;

public class LinuxMediaPlayerFactory extends MediaPlayerFactory {

    static private LinuxMediaPlayerFactory factory;

    // On implemente un singleton pour s'assurer qu'il n'y ait qu'une seule concrete factory
    synchronized public static LinuxMediaPlayerFactory getInstance() {
        if (factory == null) {
            factory = new LinuxMediaPlayerFactory();
        }
        return factory;
    }

    private LinuxMediaPlayerFactory() {
        super();
    }

    public PlayerVideo createPlayerVideo() {
        return new TotemMoviePlayer();
    }

    public PlayerMusique createPlayerMusique() {
        return new Rythmbox();
    }
}
