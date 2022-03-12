package Affichage;

public class AfficheurObserverAdapter implements StateChangedObserver {

    Afficheur afficheur = Afficheur.getAfficheur();

    @Override
    public void update(StateChangedEvent event) {
        afficheur.display(event.getTitle(), event.getNewState().toString());
        afficheur.displayLog(event);
    }
}
