package AgentPlayer.Commands;

import AgentPlayer.Agents.AgentPlayerMultiMedia;

public class Pause implements Command{

    AgentPlayerMultiMedia agentPlayerMultiMedia;

    public Pause(AgentPlayerMultiMedia agentPlayerMultimedia) {
        this.agentPlayerMultiMedia = agentPlayerMultimedia;
    }

    @Override
    public void execute() {
        agentPlayerMultiMedia.getState().pause(agentPlayerMultiMedia);
    }
}
