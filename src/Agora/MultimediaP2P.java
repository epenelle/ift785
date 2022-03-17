package Agora;

import AgentPlayer.Agents.AgentPlayerMultiMedia;
import AgentRepertoire.AgentRepertoire;
import Multimedia.Multimedia;
import Recherche.AgentRecherche;
import Recherche.Requests.Requete;
import Recherche.VetoObserver.ShareRequestEvent;
import Recherche.VetoObserver.VetoException;
import Recherche.VetoObserver.VetoableShareObserver;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

public class MultimediaP2P implements PeerObserver, VetoableShareObserver {

    private String username;
    private Agora agora = Agora.getInstance();
    private AgentPlayerMultiMedia agentPlayerMultiMedia = new AgentPlayerMultiMedia();
    private AgentRepertoire agentRepertoire = new AgentRepertoire();
    private AgentRecherche agentRecherche = new AgentRecherche();
    private Set<MultimediaP2P> blackList = new TreeSet<>();

    public MultimediaP2P(String username) {
        this.username = username;

        // Le peer se connecte au reseau d'echange.
        agora.addPeer(this);
    }

    public synchronized Set<Multimedia> sharingRequest(Requete request) throws VetoException, InvocationTargetException, IllegalAccessException {
        // Lorsqu'un MultimediaP2P effectue une requete de partage, il declenche la methode dans l'agora

        agora.notifyShareObservers(new ShareRequestEvent(this, new Timestamp(System.currentTimeMillis())));
        return agentRecherche.executeRequest(agora, request);

    }

    public AgentPlayerMultiMedia getAgentPlayerMultiMedia() {
        return agentPlayerMultiMedia;
    }

    public AgentRepertoire getAgentRepertoire() {
        return agentRepertoire;
    }

    public AgentRecherche getAgentRecherche() {
        return agentRecherche;
    }

    public void addToBlacklist(MultimediaP2P peer) {
        blackList.add(peer);
    }

    public void removeFromBlackList(MultimediaP2P peer) {
        blackList.remove(peer);
    }

    public Set<MultimediaP2P> getBlackList() {
        return blackList;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void peerListChangedNotify(PeerParticipationEvent event) {
        // NOTE : On simule une notification a l'ecran du peer.
        System.out.println(event);
    }

    @Override
    public void shareEventNotify(ShareRequestEvent event) throws VetoException {
        if (blackList.contains(event.getRequirer())) {
            vetoTrade(event);
        }
    }

    private void vetoTrade(ShareRequestEvent event) throws VetoException {
        throw new VetoException("Request blocked by " +
                this + " because " + event.getRequirer() + " is blacklisted", event);
    }

    public void sendMultimedia(MultimediaP2P multimediaP2P, Multimedia multimedia) {
        // redirection vers agent repertoire, car c'est sa responsabilite
        getAgentRepertoire().shareMultimedia(multimediaP2P, multimedia);
    }

    public void selectMultimedia(Multimedia multimedia) {

        if (getAgentRepertoire().hasMultimedia(multimedia)) {
            System.out.println("TEST1");
            getAgentPlayerMultiMedia().selectMultimedia(multimedia);
        }
    }

    public Set<Multimedia> answerRequest(Requete request) {
        return agentRepertoire.answerRequest(request);
    }

    /* NOTE : Java ne permet pas de detruire un objet en memoire, c'est le garbage collector qui
     * prend cette responsabilite et on ne peut predire avec certitude qu'un objet sera detruit en memoire.
     * Ainsi, on simule cela avec l'appel d'une methode qui fait comme si l'objet n'existe plus.
     */
    public synchronized void destroy() {
        // On quitte l'agora
        Agora.getInstance().removePeer(this);

        // On met tous les attributs a null
        this.username = null;
        this.agentPlayerMultiMedia = null;
        this.agentRecherche = null;
        this.agentRepertoire = null;
        return;
    }

    @Override
    public String toString() {
        return "Agora.MultimediaP2P{" +
                "agentPlayerMultiMedia=" + agentPlayerMultiMedia +
                ", agentRepertoire=" + agentRepertoire +
                ", agentRecherche=" + agentRecherche +
                '}';
    }
}
