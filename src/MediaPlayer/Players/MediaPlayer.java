package MediaPlayer.Players;

import AgentPlayer.Media.AgentPlayerMultiMedia;

public interface MediaPlayer {
    void pause(AgentPlayerMultiMedia agentPlayerMultiMedia);
    void close(AgentPlayerMultiMedia agentPlayerMultiMedia);
    void play(AgentPlayerMultiMedia agentPlayerMultiMedia);
}