import AgentPlayer.Media.AgentPlayerMultiMedia;
import AgentPlayer.Media.AgentPlayerMusique;
import AgentPlayer.Media.AgentPlayerVideo;
import Ownership.Bought;
import Ownership.Rental;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

class TP2 {

    private static  ArrayList<AgentPlayerMultiMedia> medias = new ArrayList<AgentPlayerMultiMedia>();
    private static final String[] osList = {"defaut", "win", "mac", "lin"};
    private static final Map<String,String> osLongDefinitionMap = Map.of(
            "win", "Windows",
            "mac", "MacOS",
            "lin", "Linux"
    );

    public static void main(String args[]){

        displayOsList();
        String osName = chooseOS();

        if (osName == "defaut"){
            System.out.println("Votre systeme d'exploitation est " + System.getProperty("os.name") + ".");
            // AgentPlayer par defaut : determine l'os avec System.getProperty("os.name")
            medias.add(new AgentPlayerMusique("Billie Jean", "Billie Jean is not my lover!", new Bought()));
            medias.add(new AgentPlayerMusique("Thriller", "Cause this is thriller, thriller night!", new Rental(2)));
            medias.add(new AgentPlayerVideo("Rick Roll", "https://www.youtube.com/watch?v=dQw4w9WgXcQ", new Bought()));
            medias.add(new AgentPlayerVideo("Numa Numa", "https://www.youtube.com/watch?v=KmtzQCSh6xk", new Rental(2)));
        }
        else {
            System.out.println("Le systeme d'exploitation simulé est " + osLongDefinitionMap.get(osName) + ".");
            medias.add(new AgentPlayerMusique("Billie Jean", "Billie Jean is not my lover!", new Bought(), osName));
            medias.add(new AgentPlayerMusique("Thriller", "Cause this is thriller, thriller night!", new Rental(2), osName));
            medias.add(new AgentPlayerVideo("Rick Roll", "https://www.youtube.com/watch?v=dQw4w9WgXcQ", new Bought(), osName));
            medias.add(new AgentPlayerVideo("Numa Numa", "https://www.youtube.com/watch?v=KmtzQCSh6xk", new Rental(2), osName));
        }

        displayMediasList();
        AgentPlayerMultiMedia media = getUserMediaChoice();

        boolean quitFlag = false;
        while (!quitFlag) {
            media.displayInfo(true);
            String optionString = chooseOption();
            quitFlag = optionString.equals("quit");
            if(!quitFlag)
                media.executeOption(optionString);
        }
    }

    private static String chooseOS(){
        System.out.println("Choisissez le systeme d'exploitation que vous voulez utiliser en inscrivant l'index.\n");

        Scanner scanner = new Scanner(System.in);
        int index = Integer.parseInt(scanner.nextLine());
        try {
            return osList[index];
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Erreur, veuillez reessayer.");
            return chooseOS();
        }
    }

    private static void displayOsList(){
        System.out.println("0 - Os par défaut (sera déterminé selon votre système).");
        System.out.println("1 - Windows");
        System.out.println("2 - Mac OS");
        System.out.println("3 - Linux");

    }

    private static String chooseOption(){
        System.out.println("Choisissez une des options en inscrivant l'option au complet.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    private static void displayMediasList(){
        System.out.println("   titre   |   contenu   |   classe   |   Ownership (bought/rental)");

        for (int i = 0; i < medias.size(); i++) {
            AgentPlayerMultiMedia media = medias.get(i);
            System.out.println(i + " - "
                    + media.getTitle() + " | "
                    + media.getContents() + " | "
                    + media.getClass() + " | "
                    + media.getOwnership().getClass());
        }
    }

    private static AgentPlayerMultiMedia getUserMediaChoice() {

        System.out.println("Bonjour, choisissez le type de Media que vous voulez utiliser en inscrivant l'index : \n");

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
