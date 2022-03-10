package AgentPlayer;

import Ownership.*;

public class AgentPlayerMusique extends AgentPlayerMultiMedia {

    public AgentPlayerMusique(String titre, Object contents, Ownership ownership) {
        super(titre, contents, ownership);
    }

    public AgentPlayerMusique(String titre, Object contents, Ownership ownership, String osName) {
        super(titre, contents, ownership, osName);
    }

    protected void start() {
        player = playerFactory.createPlayerMusique();
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
