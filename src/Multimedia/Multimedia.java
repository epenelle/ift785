package Multimedia;

import AgentPlayer.Agents.AgentPlayerMultiMedia;
import Multimedia.Ownership.Ownership;
import Multimedia.States.State;

public interface Multimedia {

    // NOTE : j'ai l'impression que cette interface ne sert a rien et que c'est mieux de la remplacer entierement
    // par la classe AbstractMultimedia.
    String getTitre();
    String getAuteur();
    boolean isGratuit();
    boolean isLocation();
    boolean isAchat();
    String getProprietaire();
    Multimedia clone();
    void choosePlayerStrategy(AgentPlayerMultiMedia agentPlayerMultiMedia);
    Ownership getOwnership();
    void setState(State state);
    boolean isAvailableForTrade();

}