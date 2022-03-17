package AgentPlayer.Commands;

import AgentPlayer.Agents.AgentPlayerMultiMedia;

public class Start implements Command{

    private AgentPlayerMultiMedia agentPlayerMultiMedia;

    public Start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        this.agentPlayerMultiMedia = agentPlayerMultimedia;
    }

    @Override
    public void execute() {
        agentPlayerMultiMedia.getState().start(agentPlayerMultiMedia);
    }
}
