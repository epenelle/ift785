package Ownership;

public abstract class Ownership {

    protected int joue = 0;

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
}
