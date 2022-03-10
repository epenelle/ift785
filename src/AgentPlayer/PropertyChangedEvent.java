package AgentPlayer;

import java.sql.Timestamp;

public class PropertyChangedEvent {

    private String title;
    private AgentPlayerState newState;
    private AgentPlayerState previousState;
    private Timestamp timeOfChange;

    public PropertyChangedEvent(String newTitle, AgentPlayerState previousState, AgentPlayerState newState) {
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

    public AgentPlayerState getNewState() {
        return newState;
    }

    public void setNewState(AgentPlayerState newState) {
        this.newState = newState;
    }

    public AgentPlayerState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(AgentPlayerState previousState) {
        this.previousState = previousState;
    }

    public Timestamp getTimeOfChange() {
        return timeOfChange;
    }

    public void setTimeOfChange(Timestamp timeOfChange) {
        this.timeOfChange = timeOfChange;
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
