package Recherche.Predicats;

import AgentRepertoire.AgentRepertoire;
import Multimedia.Multimedia;
import Recherche.Composite.Predicat;

import java.util.Set;
import java.util.TreeSet;

public class ConcretePredicate implements Predicat {

    private AgentRepertoire agentRepertoire;

    public ConcretePredicate(AgentRepertoire agentRepertoire) {
        this.agentRepertoire = agentRepertoire;
    }

    @Override
    public Multimedia searchTitre(String unTitre) {
        Set<Multimedia> multimedias = fetchMultimediasRepertoire();

        Multimedia requestedMultimedia = null;
        for (Multimedia multimedia : multimedias) {
            if (multimedia.getTitre() == unTitre) {
                requestedMultimedia = multimedia;
            }
        }
        return requestedMultimedia;
    }

    @Override
    public Set<Multimedia> searchAuteur(String unAuteur) {
        Set<Multimedia> multimedias = fetchMultimediasRepertoire();

        Set<Multimedia> requestedMultimedias = new TreeSet<>();
        for (Multimedia multimedia : multimedias) {
            if (multimedia.getAuteur() == unAuteur) {
                requestedMultimedias.add(multimedia);
            }
        }
        return requestedMultimedias;
    }

    @Override
    public Set searchGratuit() {
        return null;
    }

    @Override
    public Set searchLocation() {
        return null;
    }

    @Override
    public Set searchAchat() {
        return null;
    }

    private Set<Multimedia> fetchMultimediasRepertoire() {
        return agentRepertoire.getListeMultimedias();
    }
}
