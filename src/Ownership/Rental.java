package Ownership;

import Ownership.Ownership;

public class Rental extends Ownership {

    int maximum;

    public Rental(int maximum){
        this.maximum = maximum;
    }

    public boolean allowStart() {
        return joue < maximum;
    }

    public void incrementJoue(){
        this.joue++;
    }

    public void decrementJoue(){
        this.joue--;
    }

    public void displayInfo(){
        System.out.println("joue : " + getJoue());
        System.out.println("maximum : " + getMaximum());
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
