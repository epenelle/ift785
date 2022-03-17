package AgentPlayer.States;

import AgentPlayer.Agents.AgentPlayer3Dmov;
import AgentPlayer.Agents.AgentPlayerMultiMedia;

public interface State {

    void start(AgentPlayerMultiMedia agentPlayerMultimedia);
    void pause(AgentPlayerMultiMedia agentPlayerMultimedia);
    void resume(AgentPlayerMultiMedia agentPlayerMultimedia);
    void stop(AgentPlayerMultiMedia agentPlayerMultimedia);

    // NOTE : A verifier
    void enableGlobalScene(AgentPlayer3Dmov agentPlayer3Dmov);

}
