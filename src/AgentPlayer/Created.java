package AgentPlayer;

public class Created implements AgentPlayerState {

    @Override
    public void start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        if (!agentPlayerMultimedia.ownership.allowStart()){
            System.out.println("La location est echue.");
            return;
        }

        agentPlayerMultimedia.state = agentPlayerMultimedia.started;
        agentPlayerMultimedia.start();
    }

    @Override
    public void pause(AgentPlayerMultiMedia agentPlayerMultimedia) {
        agentPlayerMultimedia.pause();
        agentPlayerMultimedia.ownership.incrementJoue();
        agentPlayerMultimedia.state = agentPlayerMultimedia.paused;
    }

    @Override
    public void resume(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Resume ne fait rien si le media n'est pas en pause.");
        return;
    }

    @Override
    public void stop(AgentPlayerMultiMedia agentPlayerMultimedia) {
        agentPlayerMultimedia.stop();
        agentPlayerMultimedia.state = agentPlayerMultimedia.stopped;
    }
}
