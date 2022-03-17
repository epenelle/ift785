package AgentPlayer.Agents;

import AgentPlayer.Buttons.Button;
import AgentPlayer.Commands.EnableGlobalScene;
import AgentPlayer.States.GlobalScene;
import AgentPlayer.States.State;

public class AgentPlayer3Dmov extends AgentPlayerMultiMedia{

    public State globalScene = new GlobalScene();
    public Button enableGlobaSceneButton = new Button(new EnableGlobalScene(this));

    public AgentPlayer3Dmov() {
        super();
    }

    public AgentPlayer3Dmov(String osName) {
        super(osName);
    }

    public void clickEnableGlobalScene() {
        enableGlobaSceneButton.press();
    }

    @Override
    public void start() {
        // NOTE : On suppose que le AgentPlayer3Dmov utilise le meme player que le AgentPlayerVideo
        player = playerFactory.createPlayerVideo();
        player.play();
    }
}
