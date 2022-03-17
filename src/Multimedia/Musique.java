package Multimedia;

import AgentPlayer.Agents.AgentPlayerMultiMedia;
import Multimedia.Ownership.Ownership;

public class Musique extends AbstractMultimedia{

    public Musique(String title, String author, boolean isFree, Ownership ownership) {
        super(title, author, isFree, ownership);
    }

    @Override
    public Multimedia clone() {
        return this.clone();
    }

    @Override
    public void choosePlayerStrategy(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        agentPlayerMultiMedia.setStrategy(agentPlayerMultiMedia.musiqueStrategy);
    }

    @Override
    public Ownership getOwnership() {
        return ownership;
    }

}
