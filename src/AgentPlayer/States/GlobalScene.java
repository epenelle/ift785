package AgentPlayer.States;

import AgentPlayer.Agents.AgentPlayer3Dmov;
import AgentPlayer.Agents.AgentPlayerMultiMedia;

public class GlobalScene implements State {


    @Override
    public void start(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Start ne fait rien à l'état GlobalScene");
        return;
    }

    @Override
    public void pause(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Pause ne fait rien à l'état GlobalScene");
        return;
    }

    @Override
    public void resume(AgentPlayerMultiMedia agentPlayerMultimedia) {
        /* NOTE : Le comportement de resume en mode global scene n'est pas defini dans l'enonce.
         * On fait donc seulement passe a l'etat resume, sans rien faire d'autre.
         */
        agentPlayerMultimedia.setState(agentPlayerMultimedia.resumed);
    }

    @Override
    public void stop(AgentPlayerMultiMedia agentPlayerMultimedia) {
        System.out.println("Stop ne fait rien à l'état GlobalScene");
        return;
    }

    @Override
    public void enableGlobalScene(AgentPlayer3Dmov agentPlayer3Dmov) {
        System.out.println("Déja à l'état GlobalScene");
        return;
    }

    @Override
    public String toString() {
        return "global scene";
    }
}
