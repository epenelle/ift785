package Recherche.VetoObserver;

public class VetoException extends Exception{

    public ShareRequestEvent shareRequestEvent;

    public VetoException(String message, ShareRequestEvent shareRequestEvent) {
        super(message);
        this.shareRequestEvent = shareRequestEvent;
    }
}
