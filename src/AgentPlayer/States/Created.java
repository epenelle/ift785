package AgentPlayer.States;

import AgentPlayer.Agents.AgentPlayer3Dmov;
import AgentPlayer.Agents.AgentPlayerMultiMedia;
import Multimedia.AbstractMultimedia;

public class Created implements State {

    @Override
    public void start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        AbstractMultimedia multimedia = (AbstractMultimedia) agentPlayerMultimedia.getPlayer().getMultimedia();

        if (!agentPlayerMultimedia.getPlayer().getMultimedia().getOwnership().allowStart()){
            System.out.println("La location est echue.");
            multimedia.setState(multimedia.expiredState);
            return;
        }
        agentPlayerMultimedia.start();
        agentPlayerMultimedia.setState(agentPlayerMultimedia.started);
        multimedia.setState(multimedia.playingState);
    }

    @Override
    public void pause(AgentPlayerMultiMedia agentPlayerMultimedia) {
        AbstractMultimedia multimedia = (AbstractMultimedia) agentPlayerMultimedia.getPlayer().getMultimedia();

        agentPlayerMultimedia.pause();
        agentPlayerMultimedia.getPlayer().getMultimedia().getOwnership().incrementJoue();
        agentPlayerMultimedia.setState(agentPlayerMultimedia.paused);
        multimedia.setState(multimedia.playingState);
    }

    @Override
    public void resume(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Resume ne fait rien si le media n'est pas en pause.");
        return;
    }

    @Override
    public void stop(AgentPlayerMultiMedia agentPlayerMultimedia) {
        AbstractMultimedia multimedia = (AbstractMultimedia) agentPlayerMultimedia.getPlayer().getMultimedia();
        agentPlayerMultimedia.stop();
        agentPlayerMultimedia.setState(agentPlayerMultimedia.stopped);

        if (!multimedia.getOwnership().allowStart()){
            multimedia.setState(multimedia.expiredState);
            return;
        }
        multimedia.setState(multimedia.availableState);
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
