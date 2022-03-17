package Recherche.Requests;

import Recherche.Composite.Component;

import java.util.ArrayList;

public abstract class Requete implements Component {

    protected ArrayList<Component> childComponents = new ArrayList<>();

    public Requete() {
    }

    public synchronized void addComponent(Component component) {
        childComponents.add(component);
    }

    public ArrayList<Component> getPredicates() {
        return childComponents;
    }

    public synchronized void removeComponent(Component component) {
        childComponents.remove(component);
    }
}
