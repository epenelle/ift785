package MediaPlayer;

public class MacOSMediaPlayerFactory extends MediaPlayerFactory {

    static private MacOSMediaPlayerFactory factory;

    // On implemente un singleton pour s'assurer qu'il n'y ait qu'une seule concrete factory
    synchronized public static MacOSMediaPlayerFactory getInstance() {
        if (factory == null) {
            factory = new MacOSMediaPlayerFactory();
        }
        return factory;
    }

    private MacOSMediaPlayerFactory() {
        super();
    }

    public PlayerVideo createPlayerVideo() {
        return new QuickTime();
    }

    public PlayerMusique createPlayerMusique() {
        return new iTunes();
    }
}
