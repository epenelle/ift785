package Multimedia;

import AgentPlayer.Agents.AgentPlayerMultiMedia;
import Multimedia.Ownership.Ownership;

public class Video extends AbstractMultimedia {

    public int resolution;

    public Video(String title, String author, boolean isFree, Ownership ownership, int resolution) {
        super(title, author, isFree, ownership);
        this.resolution = resolution;
    }

    @Override
    public Multimedia clone() {
        return this.clone();
    }

    @Override
    public void choosePlayerStrategy(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        agentPlayerMultiMedia.setStrategy(agentPlayerMultiMedia.videoStrategy);
    }

    @Override
    public Ownership getOwnership() {
        return ownership;
    }

}
