package MediaPlayer;

import AgentPlayer.AgentPlayerMultiMedia;

public class Rythmbox implements PlayerMusique {

    public void pause(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Paused media on Rythmbox.");
    }

    public void close(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Closed media on Rythmbox.");
    }

    public void play(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Playing media on Rythmbox.");
    }
}
