package Recherche.VetoObserver;

public interface ShareSubject {

    void addShareObserver(VetoableShareObserver observer);
    void removeShareObserver(VetoableShareObserver observer);
    void notifyShareObservers(ShareRequestEvent event) throws VetoException;
}
