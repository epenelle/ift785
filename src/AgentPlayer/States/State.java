package AgentPlayer.States;

import AgentPlayer.Media.AgentPlayer3Dmov;
import AgentPlayer.Media.AgentPlayerMultiMedia;

public interface State {

    void start(AgentPlayerMultiMedia agentPlayerMultimedia);
    void pause(AgentPlayerMultiMedia agentPlayerMultimedia);
    void resume(AgentPlayerMultiMedia agentPlayerMultimedia);
    void stop(AgentPlayerMultiMedia agentPlayerMultimedia);

    // NOTE : A verifier
    void enableGlobalScene(AgentPlayer3Dmov agentPlayer3Dmov);

}
