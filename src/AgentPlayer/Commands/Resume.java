package AgentPlayer.Commands;

import AgentPlayer.Agents.AgentPlayerMultiMedia;

public class Resume implements Command{

    AgentPlayerMultiMedia agentPlayerMultiMedia;

    public Resume(AgentPlayerMultiMedia agentPlayerMultimedia) {
        this.agentPlayerMultiMedia = agentPlayerMultimedia;
    }

    @Override
    public void execute() {
        agentPlayerMultiMedia.getState().resume(agentPlayerMultiMedia);
    }
}