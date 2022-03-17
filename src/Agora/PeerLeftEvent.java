package Agora;

public class PeerLeftEvent extends PeerParticipationEvent {


    public PeerLeftEvent(PeerObserver peer) {
        super();
        this.notificationTitle = "New peer has left!";
        this.notificationBody = peer.getUsername()  + " left the agora. You can no longer share multimedia " +
                "content with it.";
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public String getNotificationBody() {
        return notificationBody;
    }

}
