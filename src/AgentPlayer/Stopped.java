package AgentPlayer;

public class Stopped implements AgentPlayerState {

    @Override
    public void start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        if (!agentPlayerMultimedia.ownership.allowStart()) {
            System.out.println("La location est echue.");
            return;
        }
        agentPlayerMultimedia.start();
        agentPlayerMultimedia.setState(agentPlayerMultimedia.started);
        return;
    }

    @Override
    public void pause(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("pause n'engendre aucune action lorsque le media est a l'etat stop.");
        return;
    }

    @Override
    public void resume(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Resume ne fait rien si le media n'est pas en pause.");
        return;
    }

    @Override
    public void stop(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Le media est deja a l'etat stop.");
        return;
    }

    @Override
    public String toString() {
        return "stopped";
    }
}
