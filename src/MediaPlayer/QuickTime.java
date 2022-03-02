package MediaPlayer;

import AgentPlayer.AgentPlayerMultiMedia;

public class QuickTime implements PlayerVideo {

    public void pause(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Paused media on QuickTime.");
    }

    public void close(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Closed media on QuickTime.");
    }

    public void play(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Playing media on QuickTime.");
    }

}