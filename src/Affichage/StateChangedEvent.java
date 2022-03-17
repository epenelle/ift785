package Affichage;

import AgentPlayer.States.State;

import java.sql.Timestamp;

public class StateChangedEvent {

    private String title;
    private State newState;
    private State previousState;
    private Timestamp timeOfChange;

    public StateChangedEvent(String newTitle, State previousState, State newState) {
        this.title = newTitle;
        this.previousState = previousState;
        this.newState = newState;
        this.timeOfChange = new Timestamp(System.currentTimeMillis());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public State getNewState() {
        return newState;
    }

    @Override
    public String toString() {
        return "PropertyChangedEvent{" +
                "title='" + title + '\'' +
                ", newState=" + newState +
                ", previousState=" + previousState +
                ", timeOfChange=" + timeOfChange +
                '}';
    }
}
