package Affichage;
import java.io.FileWriter;
import java.io.IOException;

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

    public void displayLog(StateChangedEvent event){
        try {
            FileWriter writer = new FileWriter("logFile.txt", true);
            writer.write(event.toString() + "\n");
            writer.close();
        }
        catch(IOException exception) {
            System.out.println("Erreur lors du log" + exception);
            exception.printStackTrace();
        }
    }

}