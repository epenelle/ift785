import AgentPlayer.*;
import Ownership.Bought;
import Ownership.Rental;

import java.util.ArrayList;
import java.util.Scanner;

class TP3 {

    private static  ArrayList<AgentPlayerMultiMedia> medias = new ArrayList<AgentPlayerMultiMedia>();

    public static void main(String args[]){

        medias.add(new AgentPlayerMusique("Billie Jean", "Billie Jean is not my lover!", new Bought()));
        medias.add(new AgentPlayerMusique("Thriller", "Cause this is thriller, thriller night!", new Rental(2)));
        medias.add(new AgentPlayerVideo("Rick Roll", "https://www.youtube.com/watch?v=dQw4w9WgXcQ", new Bought()));
        medias.add(new AgentPlayerVideo("Numa Numa", "https://www.youtube.com/watch?v=KmtzQCSh6xk", new Rental(2)));

        displayMediasList();
        AgentPlayerMultiMedia media = getUserMediaChoice();


        boolean quitFlag = false;
        while (!quitFlag) {
            media.displayInfo(true);
            String optionString = chooseOption(media);
            quitFlag = optionString.equals("quit");
            if(!quitFlag)
                media.executeOption(optionString);
        }

    }

    private static String chooseOption(AgentPlayerMultiMedia media){
        System.out.println("Choisissez une des options en inscrivant l'option au complet.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    private static void displayMediasList(){
        System.out.println("   titre   |   contenu   |   classe   |   Ownership (bought/rental)");

        for (int i = 0; i < medias.size(); i++) {
            AgentPlayerMultiMedia media = medias.get(i);
            System.out.println(i + " - "
                    + media.getTitre() + " | "
                    + media.getContents() + " | "
                    + media.getClass() + " | "
                    + media.getOwnership().getClass());
        }
    }

    private static AgentPlayerMultiMedia getUserMediaChoice() {

        System.out.println("Bonjour, choisissez le type de Media que vous voulez utiliser en tapant l'index : \n");

        Scanner scanner = new Scanner(System.in);
        int index = Integer.parseInt(scanner.nextLine());
        try {
            AgentPlayerMultiMedia media = medias.get(index);
            return media;
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Erreur, veuillez reessayer.");
            return getUserMediaChoice();
        }
    }

}
