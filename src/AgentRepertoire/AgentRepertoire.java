package AgentRepertoire;

import AgentRepertoire.Observer.MultimediaStateChangedEvent;
import AgentRepertoire.Observer.Observer;
import Agora.MultimediaP2P;
import Multimedia.AbstractMultimedia;
import Multimedia.Multimedia;
import Recherche.Requests.Requete;

import java.util.Set;
import java.util.TreeSet;

public class AgentRepertoire implements Observer {

    private Set<Multimedia> listeMultimedias = new TreeSet<>();

    public AgentRepertoire() {

    }

    public void addMedia(Multimedia multimedia) {
        listeMultimedias.add(multimedia);
    }

    public void removeMedia(Multimedia multimedia) {
        listeMultimedias.remove(multimedia);
    }

    public Set<Multimedia> getListeMultimedias() {
        return listeMultimedias;
    }

    public boolean hasMultimedia(Multimedia multimedia) {
        return listeMultimedias.contains(multimedia);
    }

    public void setListeMultimedias(Set<Multimedia> listeMultimedias) {
        this.listeMultimedias = listeMultimedias;
    }

    public Multimedia shareMultimedia(MultimediaP2P multimediaP2P, Multimedia multimedia) {

        // Si le multimedia n'est pas dans la liste, retourne null
        if (!listeMultimedias.contains(multimedia)) {
            return null;
        }

        // Si le multimedia n'est pas echangeable, on retourne
        if (!multimedia.isAvailableForTrade()) {
            return null;
        }

        // Si gratuit, on retourne une copie
        if (multimedia.isGratuit()) {
            return sendMultimediaCopy(multimedia);
        }
        // Si location ou achat, on retire de la liste et on retourne
        else if (multimedia.isLocation() || multimedia.isAchat()) {
            return transferMultimedia(multimedia);
        }
        // NOTE : Les autres comportements ne sont pas definis dans l'enonce ?
        else {
            return null;
        }

    }

//    public Set<Multimedia> answerRequest(Requete request) {
//
//    }

    private Multimedia sendMultimediaCopy(Multimedia multimedia) {
        return multimedia.clone();
    }

    private Multimedia transferMultimedia(Multimedia multimedia) {
        listeMultimedias.remove(multimedia);
        return multimedia;
    }

    @Override
    public void update(MultimediaStateChangedEvent event) {
        AbstractMultimedia abstractMultimedia = (AbstractMultimedia) event.multimedia;
        if (event.newState == abstractMultimedia.expiredState)
            removeMedia(event.multimedia);
    }

    public Set<Multimedia> answerRequest(Requete request) {
        return null;
    }
}
