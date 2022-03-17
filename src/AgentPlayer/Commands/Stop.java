package AgentPlayer.Commands;

import AgentPlayer.Agents.AgentPlayerMultiMedia;

public class Stop implements Command{

    AgentPlayerMultiMedia agentPlayerMultiMedia;

    public Stop(AgentPlayerMultiMedia agentPlayerMultimedia) {
        this.agentPlayerMultiMedia = agentPlayerMultimedia;
    }

    @Override
    public void execute() {
        agentPlayerMultiMedia.getState().stop(agentPlayerMultiMedia);
    }
}