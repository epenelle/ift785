package Affichage;

import AgentPlayer.AbstractObserver;
import AgentPlayer.PropertyChangedEvent;

public class AfficheurObserverAdapteur implements AbstractObserver {

    Afficheur afficheur = Afficheur.getAfficheur();

    @Override
    public void update(PropertyChangedEvent event) {
        afficheur.display(event.getTitle(), event.getNewState().toString());
        afficheur.displayLog(event);
    }
}
