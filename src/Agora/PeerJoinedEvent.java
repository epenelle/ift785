package Agora;

public class PeerJoinedEvent extends PeerParticipationEvent{


    public PeerJoinedEvent(PeerObserver peer) {
        super();
        this.notificationTitle = "New peer has joined!";
        this.notificationBody = peer.getUsername()  + " joined the agora. You can now share multimedia " +
                "content with it.";
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public String getNotificationBody() {
        return notificationBody;
    }

}
