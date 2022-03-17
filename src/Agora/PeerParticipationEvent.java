package Agora;

public abstract class PeerParticipationEvent {

    protected String notificationTitle;
    protected String notificationBody;

    public PeerParticipationEvent() {

    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public String getNotificationBody() {
        return notificationBody;
    }

    public String toString() {
        return notificationTitle + '\n'
                + "-----------------------" + '\n'
                + notificationBody;
    }

}
