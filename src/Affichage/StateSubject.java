package Affichage;

public interface StateSubject {

    public void subscribe(StateChangedObserver observer);

    public void unsubscribe(StateChangedObserver observer);

    public void notifyObservers(StateChangedEvent event);

}
