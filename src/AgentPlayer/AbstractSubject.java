package AgentPlayer;

public interface AbstractSubject {

    public void subscribe(AbstractObserver observer);

    public void unsubscribe(AbstractObserver observer);

    public void notifyObservers();

}
