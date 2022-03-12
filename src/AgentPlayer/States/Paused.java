package AgentPlayer.States;

import AgentPlayer.Media.AgentPlayer3Dmov;
import AgentPlayer.Media.AgentPlayerMultiMedia;

public class Paused implements State {

    @Override
    public void start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Start ne fait rien si le media n'est pas a l'etat created ou stopped.");
        return;
    }

    @Override
    public void pause(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Le media est deja en pause.");
        return;
    }

    @Override
    public void resume(AgentPlayerMultiMedia agentPlayerMultimedia) {
        agentPlayerMultimedia.resume();
        agentPlayerMultimedia.ownership.decrementJoue();
        agentPlayerMultimedia.setState(agentPlayerMultimedia.resumed);
    }

    @Override
    public void stop(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Stop n'engendre aucune action lorsque le media est en pause.");
        return;
    }

    @Override
    public void enableGlobalScene(AgentPlayer3Dmov agentPlayer3Dmov) {
        System.out.println("Cette option n'est pas disponible a l'état " + this + ".");
        return;
    }

    @Override
    public String toString() {
        return "paused";
    }
}
