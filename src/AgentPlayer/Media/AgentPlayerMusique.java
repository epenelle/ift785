package AgentPlayer.Media;

import Ownership.*;

public class AgentPlayerMusique extends AgentPlayerMultiMedia {

    public AgentPlayerMusique(String titre, Object contents, Ownership ownership) {
        super(titre, contents, ownership);
    }

    public AgentPlayerMusique(String titre, Object contents, Ownership ownership, String osName) {
        super(titre, contents, ownership, osName);
    }

    @Override
    public void start() {
        player = playerFactory.createPlayerMusique();
        player.play(this);
    }

}
