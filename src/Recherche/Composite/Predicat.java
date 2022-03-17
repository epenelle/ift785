package Recherche.Composite;

import Multimedia.Multimedia;

import java.util.Set;

public interface Predicat {
    Multimedia searchTitre(String unTitre);
    Set searchAuteur(String unAuteur);
    Set searchGratuit();
    Set searchLocation();
    Set searchAchat();
//    ArrayList searchProprietaire(Vector owners);
}