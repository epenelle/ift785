package Recherche.VetoObserver;

import Agora.MultimediaP2P;

import java.sql.Timestamp;

public class ShareRequestEvent {

    private MultimediaP2P requirer;
    private Timestamp timeOfRequest;

    public ShareRequestEvent(MultimediaP2P requirer, Timestamp timeOfRequest) {
        this.requirer = requirer;
        this.timeOfRequest = timeOfRequest;
    }

    public MultimediaP2P getRequirer() {
        return requirer;
    }

    public Timestamp getTimeOfRequest() {
        return timeOfRequest;
    }
}
