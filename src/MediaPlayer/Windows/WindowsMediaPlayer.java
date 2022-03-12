package MediaPlayer.Windows;

import AgentPlayer.Media.AgentPlayerMultiMedia;
import MediaPlayer.Players.PlayerMusique;
import MediaPlayer.Players.PlayerVideo;

public class WindowsMediaPlayer implements PlayerMusique, PlayerVideo {

    public void pause(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Paused media on WindowsMediaPlayer.");
    }

    public void close(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Closed media on WindowsMediaPlayer.");
    }

    public void play(AgentPlayerMultiMedia agentPlayerMultiMedia) {
        System.out.println("Playing media on WindowsMediaPlayer.");
    }
}