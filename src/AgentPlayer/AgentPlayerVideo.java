package AgentPlayer;

import Ownership.*;

public class AgentPlayerVideo extends AgentPlayerMultiMedia {


    public AgentPlayerVideo(String titre, Object contents, Ownership ownership) {
        super(titre, contents, ownership);
    }

    protected void start() {
        player = playerFactory.createPlayerVideo();
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
