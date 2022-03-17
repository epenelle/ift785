package AgentPlayer.Buttons;

import AgentPlayer.Commands.Command;

public class Button {

    // NOTE : On pourra ajouter un nom et une icone au bouton
    private Command command;

    public Button(Command command) {
        this.command = command;
    }

    public void press() {
        command.execute();
    }
}
