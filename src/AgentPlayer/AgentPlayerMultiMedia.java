package AgentPlayer;

import Affichage.Afficheur;
import Affichage.AfficheurObserverAdapteur;
import MediaPlayer.MediaPlayer;
import MediaPlayer.MediaPlayerFactory;
import MediaPlayer.MacOSMediaPlayerFactory;
import MediaPlayer.WindowsMediaPlayerFactory;
import MediaPlayer.LinuxMediaPlayerFactory;
import Ownership.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public abstract class AgentPlayerMultiMedia implements AbstractSubject {

    public String title;
    public Object contents;
    public MediaPlayer player;
    protected MediaPlayerFactory playerFactory;

    private ArrayList<AbstractObserver> observers = new ArrayList<AbstractObserver>();

    private static final Map<String,Class> factoryMap = Map.of(
            "win", WindowsMediaPlayerFactory.class,
            "mac", MacOSMediaPlayerFactory.class,
            "lin", LinuxMediaPlayerFactory.class
    );

    public AgentPlayerState created = new Created();
    public AgentPlayerState started = new Started();
    public AgentPlayerState paused = new Paused();
    public AgentPlayerState resumed = new Resumed();
    public AgentPlayerState stopped = new Stopped();

    public AgentPlayerState state;
    public Ownership ownership;

    public AgentPlayerMultiMedia(String title, Object contents, Ownership ownership) {
        this.ownership = ownership;
        this.title = title;
        this.contents = contents;
        setState(created);
        String osName = System.getProperty("os.name").toLowerCase().substring(0,3);
        setFactory(osName);
        subscribe(new AfficheurObserverAdapteur());
    }

    // Ce constructeur permet de simuler le comportement sur un autre systeme d'exploitation
    public AgentPlayerMultiMedia(String title, Object contents, Ownership ownership, String osName) {
        this.ownership = ownership;
        this.title = title;
        this.contents = contents;
        setState(created);
        setFactory(osName);
        subscribe(new AfficheurObserverAdapteur());
    }

    private void setFactory(String osName){
        try {
            Class factoryClass =  factoryMap.get(osName);
            Method getInstance = factoryMap.get(osName).getMethod("getInstance");
            playerFactory = (MediaPlayerFactory) getInstance.invoke(factoryClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyObservers(PropertyChangedEvent event) {
        for (AbstractObserver observer: observers) {
            observer.update(event);
        }
    }

    @Override
    public synchronized void subscribe(AbstractObserver observer) {
        // Code tire ds notes de cours (PPT DP_Observer slide #28)
        if (observer == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public synchronized void unsubscribe(AbstractObserver observer) {
        observers.remove(observer);
    }

    public void clickStart() {
        this.state.start(this);
    }

    public void clickPause() {
        this.state.pause(this);
    }

    public void clickResume() {
        this.state.resume(this);
    }

    public void clickStop() {
        this.state.stop(this);
    }

    protected abstract void start();

    protected abstract void pause();

    protected abstract void resume();

    protected abstract void stop();

    public void displayOptions() {

        Method[] methods = AgentPlayerMultiMedia.class.getMethods();
        System.out.println("-------- Media options --------");
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().startsWith("click")) {
                System.out.println(methods[i].getName());
            }
        }
        System.out.println("quit");
    }

    public void displayInfo(boolean withOptions){
        System.out.println("-------- Media infos --------");
        System.out.println("State : " + getState());
        System.out.println("Player : " + getPlayer());
        System.out.println("Ownership : " + getOwnership());
        ownership.displayInfo();

        if (withOptions)
            displayOptions();
    }

    public void executeOption(String option){
        try {
            Method method = AgentPlayerMultiMedia.class.getMethod(option);
            method.invoke(this);
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.print("Erreur, veuillez reessayer.");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getContents() {
        return contents;
    }

    public void setContents(Object contents) {
        this.contents = contents;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    public AgentPlayerState getState() {
        return state;
    }

    public void setState(AgentPlayerState state) {
        PropertyChangedEvent event = new PropertyChangedEvent(title, this.state, state);
        this.state = state;
        this.notifyObservers(event);
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
    }
}