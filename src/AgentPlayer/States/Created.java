package AgentPlayer.States;

import AgentPlayer.Media.AgentPlayer3Dmov;
import AgentPlayer.Media.AgentPlayerMultiMedia;

public class Created implements State {

    @Override
    public void start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        if (!agentPlayerMultimedia.ownership.allowStart()){
            System.out.println("La location est echue.");
            return;
        }
        agentPlayerMultimedia.start();
        agentPlayerMultimedia.setState(agentPlayerMultimedia.started);
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
        System.out.println("Cette option n'est pas disponible a l'état " + this + ".");
        return;
    }

    @Override
    public String toString() {
        return "created";
    }
}
