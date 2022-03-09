package AgentPlayer;

import Affichage.Afficheur;
import MediaPlayer.MediaPlayer;
import MediaPlayer.MediaPlayerFactory;
import MediaPlayer.MacOSMediaPlayerFactory;
import MediaPlayer.WindowsMediaPlayerFactory;
import MediaPlayer.LinuxMediaPlayerFactory;
import Ownership.*;

import javax.print.attribute.standard.Media;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class AgentPlayerMultiMedia implements AbstractSubject {

    public String titre;
    public Object contents;
    public MediaPlayer player;
    protected MediaPlayerFactory playerFactory;
    /*
     * NOTE : Comme il est explique qu'il y a qu'un seul afficheur et qu'aucun autre objet ne semble jouer le role de
     * subscriber selon le Observer DP, il est preferable de seulement creer une variable d'instance afficheur ici.
     * Si jamais il y avait d'autres subscriber, il serait mieux de creer une liste de subscribers et d'y inclure l'afficheur.
     */
    public Afficheur afficheur = Afficheur.getAfficheur();

    /*
     On cree un dictionnaire de classe en utilisant les capacites reflexives.
     Cela evite les conditionnelles pour determiner le type de factory a utiliser.
    */

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

    public AgentPlayerMultiMedia(String titre, Object contents, Ownership ownership) {
        this.ownership = ownership;
        this.titre = titre;
        this.contents = contents;
        state = created;
        String osName = System.getProperty("os.name").toLowerCase().substring(0,3);
        setFactory(osName);
    }

    // Ce constructeur permet de simuler le comportement sur un autre systeme d'exploitation
    public AgentPlayerMultiMedia(String titre, Object contents, Ownership ownership, String osName) {
        this.ownership = ownership;
        this.titre = titre;
        this.contents = contents;
        state = created;
        setFactory(osName);
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

    /*
     * NOTE : Une autre option pour determiner le type de factory serait de proceder par conditionnelles en utilisant
     * les 3 methodes suivantes.
     */
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().startsWith("windows");
    }

    public static boolean isMac() {
        return System.getProperty("os.name").toLowerCase().startsWith("mac");
    }

    public static boolean isUnix() {
        return System.getProperty("os.name").toLowerCase().startsWith("linux");
    }

    /*
     * NOTE :
     */

    @Override
    public void notifyObservers() {
        /*
         * NOTE : Normallement, il faudrait iterer sur une liste de souscripteurs, mais l'application n'en mentionne qu'un seul.
         * NOTE : Normalement, il devrait y avoir une methode update, mais on ne peut pas changer la Classe Afficheur
         */
        afficheur.display(this.titre, this.state.toString());
    }

    @Override
    public void subscribe(AbstractObserver observer) {
        // NOTE : Normalement, cette methode ajouterait un souscripteur a la liste
    }

    @Override
    public void unsubscribe(AbstractObserver observer) {
        // NOTE : Normalement, cette methode supprimerait un souscripteur de la liste
    }

    public void clickStart() {
        this.state.start(this);
        this.notifyObservers();
    }

    public void clickPause() {
        this.state.pause(this);
        this.notifyObservers();
    }

    public void clickResume() {
        this.state.resume(this);
        this.notifyObservers();
    }

    public void clickStop() {
        this.state.stop(this);
        this.notifyObservers();
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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
        this.state = state;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
    }
}