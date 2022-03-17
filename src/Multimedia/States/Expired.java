package Multimedia.States;

// Not playing or paused and max number of play reached
public class Expired implements State{

    @Override
    public boolean isAvailableForTrade() {
        return false;
    }
}
