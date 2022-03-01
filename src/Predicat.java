import java.util.Vector;

public interface Predicat {
    Multimedia searchTitre(String unTitre);
    Vector searchAuteur(String unTitre);
    Vector searchGratuit();
    Vector searchLocation();
    Vector searchAchat();
    Vector searchProprietaire(Vector owners);
}