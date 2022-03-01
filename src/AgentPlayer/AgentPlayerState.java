package AgentPlayer;

import AgentPlayer.AgentPlayerMultiMedia;

public interface AgentPlayerState {

    void start(AgentPlayerMultiMedia agentPlayerMultimedia);
    void pause(AgentPlayerMultiMedia agentPlayerMultimedia);
    void resume(AgentPlayerMultiMedia agentPlayerMultimedia);
    void stop(AgentPlayerMultiMedia agentPlayerMultimedia);

}
