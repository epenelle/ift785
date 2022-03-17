package Recherche;

import Agora.Agora;
import Agora.MultimediaP2P;
import Multimedia.Multimedia;
import Recherche.Composite.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class AgentRecherche {

    public AgentRecherche(){

    };

    public Set<Multimedia> executeRequest(Agora agora, Component requete) throws InvocationTargetException, IllegalAccessException {
        Set<Multimedia> multimedias = fetchMultimedia(agora);

        // filtrage
        multimedias = requete.execute(multimedias);


        return multimedias;
    }

    private Set<Multimedia> fetchMultimedia(Agora agora) {

        ArrayList<MultimediaP2P> peers = agora.getPeers();

        /* On recupere les medias de tout le monde
         * NOTE : Pas super efficace, ca serait mieux de deleguer la tache a chaque MultimediaP2P et de retourner
         * le resultat de la recherche seulement.
         */
        Set<Multimedia> multimedias = new TreeSet<>();
        for (MultimediaP2P peer: peers) {
             multimedias.addAll(peer.getAgentRepertoire().getListeMultimedias());
        }

        return multimedias;

    }

}
