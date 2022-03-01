package AgentPlayer;

public class Started implements AgentPlayerState {

    @Override
    public void start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Start ne fait rien si le media n'est pas a l'etat created ou stopped.");
        return;
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
