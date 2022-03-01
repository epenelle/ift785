package Ownership;

public class Bought extends Ownership {

    public boolean allowStart() {
        return true;
    }

    public void incrementJoue(){
        return;
    }

    public void decrementJoue(){
        return;
    }

    public void displayInfo(){
        System.out.println("joue : " + getJoue());
    }
}
