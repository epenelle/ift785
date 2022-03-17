package AgentRepertoire.Observer;

import Multimedia.Multimedia;
import Multimedia.States.State;

import java.sql.Timestamp;

public class MultimediaStateChangedEvent {

    public Multimedia multimedia;
    public State newState;
    public Timestamp timeOfChange;

    public MultimediaStateChangedEvent(Multimedia multimedia, State newState, Timestamp timeOfChange) {
        this.multimedia = multimedia;
        this.newState = newState;
        this.timeOfChange = timeOfChange;
    }

}
