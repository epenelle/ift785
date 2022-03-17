package AgentPlayer.Agents;

import Affichage.AfficheurObserverAdapter;
import Affichage.StateChangedEvent;
import Affichage.StateChangedObserver;
import Affichage.StateSubject;
import AgentPlayer.Buttons.Button;
import AgentPlayer.Commands.Pause;
import AgentPlayer.Commands.Resume;
import AgentPlayer.Commands.Start;
import AgentPlayer.Commands.Stop;
import AgentPlayer.MediaPlayer.MacOS.MacOSMediaPlayerFactory;
import AgentPlayer.MediaPlayer.Players.MediaPlayer;
import AgentPlayer.MediaPlayer.Players.MediaPlayerFactory;
import AgentPlayer.MediaPlayer.Windows.WindowsMediaPlayerFactory;
import AgentPlayer.States.*;
import AgentPlayer.Strategy.MusiqueStrategy;
import AgentPlayer.Strategy.Strategy;
import AgentPlayer.Strategy.VideoStrategy;
import Multimedia.Multimedia;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class AgentPlayerMultiMedia implements StateSubject {

//    public String title;
//  public Object contents;
    protected MediaPlayer player;
    protected MediaPlayerFactory playerFactory;

    private ArrayList<StateChangedObserver> observers = new ArrayList<>();

    private static final Map<String,Class> osPlayerFactoryMap = Map.of(
            "win", WindowsMediaPlayerFactory.class,
            "mac", MacOSMediaPlayerFactory.class
    );

    public State state;
    public final Created created = new Created();
    public final Started started = new Started();
    public final Paused paused = new Paused();
    public final Resumed resumed = new Resumed();
    public final Stopped stopped = new Stopped();

    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    private Button resumeButton;

    private Strategy strategy;
    public MusiqueStrategy musiqueStrategy;
    public VideoStrategy videoStrategy;

    public AgentPlayerMultiMedia() {
        setState(created);
        String osName = System.getProperty("os.name").toLowerCase().substring(0,3);
        setPlayerFactory(osName);
        instantiateStrategies();
        setButtons();
        subscribe(new AfficheurObserverAdapter());
    }

    // Ce constructeur permet de simuler le comportement sur un autre systeme d'exploitation
    public AgentPlayerMultiMedia(String osName) {
        setState(created);
        setPlayerFactory(osName);
        instantiateStrategies();
        setButtons();
        subscribe(new AfficheurObserverAdapter());
    }

    private void setButtons() {
        this.startButton = new Button(new Start(this));
        this.pauseButton = new Button(new Pause(this));
        this.stopButton = new Button(new Stop(this));
        this.resumeButton = new Button(new Resume(this));
    }

    private void setPlayerFactory(String osName){
        try {
            Class factoryClass =  osPlayerFactoryMap.get(osName);
            Method getInstance = osPlayerFactoryMap.get(osName).getMethod("getInstance");
            playerFactory = (MediaPlayerFactory) getInstance.invoke(factoryClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void instantiateStrategies() {
        this.musiqueStrategy = new MusiqueStrategy(playerFactory);
        this.videoStrategy = new VideoStrategy(playerFactory);
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
     * sur les boutons. Si l'application grossit, on pourra donc reutiliser les commandes (Command DP) appelees par
     * button.press(), par exemple, avec des raccourcis clavier.
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

    public void start() {
        player.play();
    }

    public void pause() {
        player.pause();
    }

    public void resume() {
        player.play();
    }

    public void stop() {
        player.close();
        player.getMultimedia().getOwnership().incrementJoue();
    }

    public void selectMultimedia(Multimedia multimedia) {
        // On choisit la strategy, on cree le player approprie et on lui passe le multimedia
        multimedia.choosePlayerStrategy(this);
        System.out.println(1);
        player = strategy.execute();
        System.out.println(2);
        player.setMedia(multimedia);
        System.out.println(3);
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

        if (withOptions)
            displayOptions();
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
        StateChangedEvent event = new StateChangedEvent( "Title" , this.state, state);
        this.state = state;
        this.notifyObservers(event);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
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

}