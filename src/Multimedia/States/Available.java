package Multimedia.States;

// Not playing and can be available to play
public class Available implements State {

    @Override
    public boolean isAvailableForTrade() {
        return true;
    }
}
