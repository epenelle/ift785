package AgentPlayer.Media;

import AgentPlayer.Buttons.Button;
import AgentPlayer.Commands.EnableGlobalScene;
import AgentPlayer.States.GlobalScene;
import AgentPlayer.States.State;
import Ownership.Ownership;

public class AgentPlayer3Dmov extends AgentPlayerMultiMedia{

    public State globalScene = new GlobalScene();
    public Button enableGlobaSceneButton = new Button(new EnableGlobalScene(this));

    public AgentPlayer3Dmov(String title, Object contents, Ownership ownership) {
        super(title, contents, ownership);
    }

    public AgentPlayer3Dmov(String title, Object contents, Ownership ownership, String osName) {
        super(title, contents, ownership, osName);
    }

    public void clickEnableGlobalScene() {
        enableGlobaSceneButton.press();
    }

    @Override
    public void start() {
        // NOTE : On suppose que le AgentPlayer3Dmov utilise le meme player que le AgentPlayerVideo
        player = playerFactory.createPlayerVideo();
        player.play(this);
    }
}
