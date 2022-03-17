package Multimedia.States;

// Either playing or paused
public class Playing implements State{

    @Override
    public boolean isAvailableForTrade() {
        return false;
    }
}
