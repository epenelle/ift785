package MediaPlayer;

import AgentPlayer.AgentPlayerMultiMedia;

public class TotemMoviePlayer implements PlayerVideo {

    public void pause(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Paused media on Totem Movie Player.");
    }

    public void close(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Closed media on Totem Movie Player.");
    }

    public void play(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Playing media on Totem Movie Player.");
    }
}