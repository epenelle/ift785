package Multimedia;

import AgentRepertoire.Observer.MultimediaStateChangedEvent;
import AgentRepertoire.Observer.Observer;
import AgentRepertoire.Observer.Subject;
import Multimedia.Ownership.Bought;
import Multimedia.Ownership.Ownership;
import Multimedia.Ownership.Rental;
import Multimedia.States.Available;
import Multimedia.States.Expired;
import Multimedia.States.Playing;
import Multimedia.States.State;

public abstract class AbstractMultimedia implements Multimedia, Subject {

    private String title;
    private String author;
    private boolean isFree;
    public Ownership ownership;

    private State state;
    public Available availableState = new Available();
    public Expired expiredState = new Expired();
    public Playing playingState = new Playing();

    private Observer observer;

    public AbstractMultimedia(String title, String author, boolean isFree, Ownership ownership) {
        this.title = title;
        this.author = author;
        this.isFree = isFree;
        this.ownership = ownership;
        setState(availableState);
    }

    public AbstractMultimedia() {
        // default state must be available
        setState(availableState);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String getTitre() {
        return title;
    }

    @Override
    public String getAuteur() {
        return author;
    }

    @Override
    public boolean isGratuit() {
        return isFree;
    }

    @Override
    public boolean isLocation() {
        return ownership instanceof Rental;
    }

    @Override
    public boolean isAchat() {
        return ownership instanceof Bought;
    }

    @Override
    public String getProprietaire() {
        return ownership.getOwner();
    }

    @Override
    public Multimedia clone() {
        return this.clone();
    }

    @Override
    public Ownership getOwnership() {
        return ownership;
    }

    @Override
    public boolean isAvailableForTrade() {
        return this.state.isAvailableForTrade();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void removeObserver(Observer observer) {
        this.removeObserver(observer);
    }

    @Override
    public void notifyObserver(MultimediaStateChangedEvent event) {
        this.observer.update(event);
    }

    @Override
    public String toString() {
        return "AbstractMultimedia{" + "\n" +
                "title='" + title + '\'' + "\n" +
                ", author='" + author + '\'' + "\n" +
                ", isFree=" + isFree + "\n" +
                ", ownership=" + ownership + "\n" +
                ", state=" + state + "\n" +
                ", observer=" + observer + "\n" +
                '}';
    }
}
