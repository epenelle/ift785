package Affichage;

public class Afficheur {


    //Singleton
    static private Afficheur afficheur;

    private Afficheur() {
        
    }

    synchronized public static Afficheur getAfficheur() {
        if (afficheur == null) {
            afficheur = new Afficheur();
        }
        return afficheur;
    }

    public void display(String titre, String etat) {
        System.out.println(titre + " >>> " + etat);
    }
}