package AgentPlayer.States;

import AgentPlayer.Agents.AgentPlayer3Dmov;
import AgentPlayer.Agents.AgentPlayerMultiMedia;
import Multimedia.AbstractMultimedia;

public class Stopped implements State {

    @Override
    public void start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        AbstractMultimedia multimedia = (AbstractMultimedia) agentPlayerMultimedia.getPlayer().getMultimedia();
        if (!agentPlayerMultimedia.getPlayer().getMultimedia().getOwnership().allowStart()) {
            System.out.println("La location est echue.");
            return;
        }
        agentPlayerMultimedia.start();
        agentPlayerMultimedia.setState(agentPlayerMultimedia.started);
        multimedia.setState(multimedia.playingState);
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
    public void enableGlobalScene(AgentPlayer3Dmov agentPlayer3Dmov) {

    }

    @Override
    public String toString() {
        return "stopped";
    }
}
