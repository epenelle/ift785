package Multimedia.Ownership;

public class Bought extends Ownership {

    public Bought() {
        super();
    }

    public Bought(int joue, String owner) {
        super(joue, owner);
    }

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
