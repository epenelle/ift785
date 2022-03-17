package Agora;

import Recherche.VetoObserver.ShareRequestEvent;
import Recherche.VetoObserver.ShareSubject;
import Recherche.VetoObserver.VetoException;
import Recherche.VetoObserver.VetoableShareObserver;

import java.util.ArrayList;

public class Agora implements PeerSubject, ShareSubject {

    private ArrayList<MultimediaP2P> peers = new ArrayList();
    private ArrayList<PeerObserver> subscribers = new ArrayList();
    private ArrayList<VetoableShareObserver> vetoableShareObservers = new ArrayList();

    // Singleton
    static private Agora agora;

    private Agora(){

    }

    synchronized public static Agora getInstance() {
        if (agora == null) {
            agora = new Agora();
        }
        return agora;
    }

    /* NOTE : On separe le comportement de rejoindre/quitter du comportement de s'abonner/se desabonner,
     * car on suppose qu'un peet pourrait vouloir se desabonner des notifications sans toutefois quitter la
     * plateforme d'echange.
     */

    public void addPeer(MultimediaP2P multimediaP2P) {
        peers.add(multimediaP2P);
        subscribe(multimediaP2P);
    }


    public void removePeer(MultimediaP2P multimediaP2P) {
        peers.remove(multimediaP2P);

        unsubscribe(multimediaP2P);
    }

    public void displayPeers() {
        System.out.println(this);
        System.out.println("List of Agora.MultimediaP2P");

        for (MultimediaP2P peer : peers) {
            System.out.println(peer);
        }
    }

    // Fournir la liste de tous les participants sur demande
    public ArrayList<MultimediaP2P> getPeers() {
        return peers;
    }

    public void setPeers(ArrayList<MultimediaP2P> peers) {
        this.peers = peers;
    }

    @Override
    public synchronized void subscribe(PeerObserver observer) {
        // NOTE : On appel notifyObservers avant l'ajout pour ne pas notifier inutilement le participant qui vient
        // de rejoindre l'agora.

        /* NOTE : Voir si on peut eviter le cast. Dans notre cas actuel, cela fonctionne.
         * mais si on ajoute un autre type de peer, cela va causer des problemes.
         */
        notifyObservers(new PeerJoinedEvent(observer));

        subscribers.add(observer);
    }

    @Override
    public synchronized void unsubscribe(PeerObserver observer) {
        // NOTE : On appel notifyObservers apres la suppression pour envoyer la liste a jour.
        subscribers.remove(observer);

        /* NOTE : Voir si on peut eviter le cast. Dans notre cas actuel, cela fonctionne.
         * mais si on ajoute un autre type de peer, cela va causer des problemes.
         */
        notifyObservers(new PeerLeftEvent(observer));
    }

    @Override
    public synchronized void notifyObservers(PeerParticipationEvent event) {

        for (PeerObserver observer: subscribers ) {
            observer.peerListChangedNotify(event);
        }
    }

    @Override
    public void addShareObserver(VetoableShareObserver observer) {
        vetoableShareObservers.add(observer);
    }

    @Override
    public void removeShareObserver(VetoableShareObserver observer) {
        vetoableShareObservers.remove(observer);
    }

    @Override
    public void notifyShareObservers(ShareRequestEvent event) throws VetoException {
        for (VetoableShareObserver observer : vetoableShareObservers) {
            observer.shareEventNotify(event);
        }
    }

}
