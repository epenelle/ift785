package AgentPlayer.Commands;

import AgentPlayer.Media.AgentPlayerMultiMedia;

public class Start implements Command{

    AgentPlayerMultiMedia agentPlayerMultiMedia;

    public Start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        this.agentPlayerMultiMedia = agentPlayerMultimedia;
    }

    @Override
    public void execute() {
        agentPlayerMultiMedia.getState().start(agentPlayerMultiMedia);
    }
}
