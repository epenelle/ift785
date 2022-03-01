package MediaPlayer;

import AgentPlayer.AgentPlayerMultiMedia;
import MediaPlayer.MediaPlayer;

public class QuickTime implements MediaPlayer {

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