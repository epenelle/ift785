package Agora;

public interface PeerSubject {

    void subscribe(PeerObserver observer);
    void unsubscribe(PeerObserver observer);
    void notifyObservers(PeerParticipationEvent event);

}
