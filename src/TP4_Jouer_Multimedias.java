import Agora.MultimediaP2P;
import Multimedia.Multimedia;
import Multimedia.Musique;
import Multimedia.Ownership.Bought;
import Multimedia.Ownership.Rental;
import Multimedia.Video;

import java.util.ArrayList;
import java.util.Scanner;

class TP4_Jouer_Multimedias {

    static final int NB_MAX_DEFAUT = 10;
    static final int RESOLUTION_DEFAUT = 1080;

    static final MultimediaP2P myself = new MultimediaP2P("Etienne_5");
    static final ArrayList<Multimedia> multimediaRepository = new ArrayList();

    static Musique musique1 =  new Musique("Billie Jean", "Micheal Jackson", true, new Bought());
    static Musique musique2 = new Musique("Thriller", "Micheal Jackson", true, new Rental(NB_MAX_DEFAUT));
    static Video video1 = new Video("Never gonna give you up", "Rick Astley", true, new Bought(), RESOLUTION_DEFAUT);
    static Video video2 = new Video("Numa numa", "famous youtuber", true, new Rental(NB_MAX_DEFAUT), RESOLUTION_DEFAUT);

    public static void main(String args[]){
        fillRepository();
        showRepository();
        acquireMultimedia();

    }

    public static void fillRepository() {
        multimediaRepository.add(musique1);
        multimediaRepository.add(musique2);
        multimediaRepository.add(video1);
        multimediaRepository.add(video2);
    }

    public static void showRepository() {
        System.out.println("List of multimedias in open repository");
        System.out.println("--------------------------------------");
        int count = 0;
        for (Multimedia multimedia:
             multimediaRepository) {
            System.out.println(count + " " + multimedia.toString());
            count++;
        }
    }

    public static void acquireMultimedia() {
        System.out.println("Bonjour, choisissez le Multimedia que vous voulez acquerir : \n");

        Scanner scanner = new Scanner(System.in);
        int index = Integer.parseInt(scanner.nextLine());
        try {
            Multimedia mediaChoice = multimediaRepository.get(index);
            myself.selectMultimedia(mediaChoice);
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Erreur, veuillez reessayer.");
            acquireMultimedia();
        }
    }




}
