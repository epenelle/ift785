package AgentPlayer.Commands;

import AgentPlayer.Agents.AgentPlayer3Dmov;

public class EnableGlobalScene implements Command{

    // NOTE : On permet seulement AgentPlayer3Dmov, puisque seul celui-ci peut effectuer cette action.
    // Je crois que ca serait mieux de specifier une interface qui serait implemente par AgentPlayer3Dmov.
    // De cette facon, on s'assure de ne pas limiter cette commande a une classe
    AgentPlayer3Dmov agentPlayer3Dmov;

    public EnableGlobalScene(AgentPlayer3Dmov agentPlayer3Dmov) {
        this.agentPlayer3Dmov = agentPlayer3Dmov;
    }

    @Override
    public void execute() {
        agentPlayer3Dmov.getState().enableGlobalScene(agentPlayer3Dmov);
    }
}
