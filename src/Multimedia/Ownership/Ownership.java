package Multimedia.Ownership;

public abstract class Ownership {

    protected int joue = 0;
    protected String owner;

    public Ownership() {

    }

    public Ownership(int joue, String owner) {
        this.joue = joue;
        this.owner = owner;
    }

    public abstract void incrementJoue();

    public abstract void decrementJoue();

    public abstract boolean allowStart();

    public int getJoue() {
        return joue;
    }

    public void setJoue(int joue) {
        this.joue = joue;
    }

    public abstract void displayInfo();

    public String getOwner() {
        return owner;
    }
}
