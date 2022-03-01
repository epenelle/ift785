package AgentPlayer;

import MediaPlayer.MediaPlayer;
import Ownership.*;

import java.lang.reflect.Method;

public abstract class AgentPlayerMultiMedia {

    public String titre;
    public Object contents;
    public MediaPlayer player;

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