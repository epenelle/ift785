package AgentPlayer.Media;

import Ownership.*;

public class AgentPlayerVideo extends AgentPlayerMultiMedia {


    public AgentPlayerVideo(String titre, Object contents, Ownership ownership) {
        super(titre, contents, ownership);
    }

    public AgentPlayerVideo(String titre, Object contents, Ownership ownership, String osName) {
        super(titre, contents, ownership, osName);
    }

    @Override
    public void start() {
        player = playerFactory.createPlayerVideo();
        player.play(this);
    }

}
