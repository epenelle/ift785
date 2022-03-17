package Recherche.VetoObserver;

public interface VetoableShareObserver {

    void shareEventNotify(ShareRequestEvent event) throws VetoException;
}
