package AgentPlayer.Media;

import Affichage.StateChangedObserver;
import Affichage.StateSubject;
import Affichage.AfficheurObserverAdapter;
import Affichage.StateChangedEvent;
import AgentPlayer.Buttons.Button;
import AgentPlayer.Commands.*;
import AgentPlayer.States.*;
import MediaPlayer.Players.MediaPlayer;
import MediaPlayer.Players.MediaPlayerFactory;
import MediaPlayer.MacOS.MacOSMediaPlayerFactory;
import MediaPlayer.Windows.WindowsMediaPlayerFactory;
import MediaPlayer.Linux.LinuxMediaPlayerFactory;
import Ownership.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public abstract class AgentPlayerMultiMedia implements StateSubject {

    public String title;
    public Object contents;
    public MediaPlayer player;
    protected MediaPlayerFactory playerFactory;

    private ArrayList<StateChangedObserver> observers = new ArrayList<StateChangedObserver>();

    private static final Map<String,Class> factoryMap = Map.of(
            "win", WindowsMediaPlayerFactory.class,
            "mac", MacOSMediaPlayerFactory.class,
            "lin", LinuxMediaPlayerFactory.class
    );

    public State state;
    public State created = new Created();
    public State started = new Started();
    public State paused = new Paused();
    public State resumed = new Resumed();
    public State stopped = new Stopped();

    public Button startButton;
    public Button pauseButton;
    public Button stopButton;
    public Button resumeButton;

    public Ownership ownership;

    public AgentPlayerMultiMedia(String title, Object contents, Ownership ownership) {
        this.ownership = ownership;
        this.title = title;
        this.contents = contents;
        setState(created);
        String osName = System.getProperty("os.name").toLowerCase().substring(0,3);
        setFactory(osName);
        setButtons();
        subscribe(new AfficheurObserverAdapter());
    }

    // Ce constructeur permet de simuler le comportement sur un autre systeme d'exploitation
    public AgentPlayerMultiMedia(String title, Object contents, Ownership ownership, String osName) {
        this.ownership = ownership;
        this.title = title;
        this.contents = contents;
        setState(created);
        setFactory(osName);
        setButtons();
        subscribe(new AfficheurObserverAdapter());
    }

    private void setButtons() {
        this.startButton = new Button(new Start(this));
        this.pauseButton = new Button(new Pause(this));
        this.stopButton = new Button(new Stop(this));
        this.resumeButton = new Button(new Resume(this));
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
    public void notifyObservers(StateChangedEvent event) {
        for (StateChangedObserver observer: observers) {
            observer.update(event);
        }
    }

    @Override
    public synchronized void subscribe(StateChangedObserver observer) {
        // Code tire ds notes de cours (PPT DP_Observer slide #28)
        if (observer == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public synchronized void unsubscribe(StateChangedObserver observer) {
        observers.remove(observer);
    }

    /*
     * NOTE : Les methodes "click****" ci-dessous peuvent paraitre inutiles, mais c'est pour remplacer le fait
     * que nous n'avons pas d'interface utilisateur. Normalement, c'est l'UI qui appellera les methodes
     * sur les boutons.
     */
    public void clickStart() {
        startButton.press();
    }

    public void clickPause() {
        pauseButton.press();
    }

    public void clickResume() {
        resumeButton.press();
    }

    public void clickStop() {
        stopButton.press();
    }

    public abstract void start();

    public void pause() {
        player.pause(this);
    }

    public void resume() {
        player.play(this);
    }

    public void stop() {
        player.close(this);
        ownership.incrementJoue();
    }

    private void displayOptions() {

        Method[] methods = this.getClass().getMethods();
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
            Method method = this.getClass().getMethod(option);
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        StateChangedEvent event = new StateChangedEvent(title, this.state, state);
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