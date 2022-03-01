package MediaPlayer;

import MediaPlayer.MediaPlayer;
import AgentPlayer.*;

public class iTunes implements MediaPlayer {

    public void pause(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Paused media on iTunes.");
    }

    public void close(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Closed media on iTunes.");
    }

    public void play(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Playing media on iTunes.");

    }

}