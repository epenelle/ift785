package Agora;

public interface PeerObserver {

    void peerListChangedNotify(PeerParticipationEvent event);
    String getUsername(); // Necessaire pour les notifications.
}
