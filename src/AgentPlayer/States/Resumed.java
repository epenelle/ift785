package AgentPlayer.States;

import AgentPlayer.Media.AgentPlayer3Dmov;
import AgentPlayer.Media.AgentPlayerMultiMedia;

public class Resumed implements State {

    @Override
    public void start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Start ne fait rien si le media n'est pas a l'etat created ou stopped.");
        return;
    }

    @Override
    public void pause(AgentPlayerMultiMedia agentPlayerMultimedia) {
        agentPlayerMultimedia.pause();
        agentPlayerMultimedia.ownership.incrementJoue();
        agentPlayerMultimedia.setState(agentPlayerMultimedia.paused);
    }

    @Override
    public void resume(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Resume ne fait rien si le media n'est pas en pause.");
        return;
    }

    @Override
    public void stop(AgentPlayerMultiMedia agentPlayerMultimedia) {
        agentPlayerMultimedia.stop();
        agentPlayerMultimedia.setState(agentPlayerMultimedia.stopped);
    }

    @Override
    public void enableGlobalScene(AgentPlayer3Dmov agentPlayer3Dmov) {
        agentPlayer3Dmov.setState(agentPlayer3Dmov.globalScene);
    }

    @Override
    public String toString() {
        return "resumed";
    }
}
