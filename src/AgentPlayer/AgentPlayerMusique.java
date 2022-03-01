package AgentPlayer;
import MediaPlayer.iTunes;
import MediaPlayer.*;
import Ownership.*;

public class AgentPlayerMusique extends AgentPlayerMultiMedia {

    public AgentPlayerMusique(String titre, Object contents, Ownership ownership) {
        super(titre, contents, ownership);
    }

    protected void start() {
        player = new iTunes();
        player.play(this);
    }

    protected void pause() {
        player.pause(this);
    }

    protected void resume() {
        player.play(this);
    }

    protected void stop() {
        player.close(this);
        ownership.incrementJoue();
    }

}
